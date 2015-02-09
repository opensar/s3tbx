package org.esa.beam.dataio.s3.util;

import org.esa.beam.dataio.netcdf.util.Constants;
import org.esa.beam.jai.ResolutionLevel;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.ma2.Section;
import ucar.nc2.Attribute;
import ucar.nc2.Variable;
import ucar.nc2.VariableIF;

import javax.media.jai.PlanarImage;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.WritableRaster;
import java.io.IOException;

/**
 * @author Tonio Fincke
 */
public class S3ReferencingVariableOpImage extends S3VariableOpImage {

    private final Variable referencedIndexVariable;
    private final VariableIF variable;
    private float[] dimensionValues;

    //todo use this to display fires in SLSTR L2 LST products when data is available
    public S3ReferencingVariableOpImage(VariableIF variable, int dataBufferType, int sourceWidth, int sourceHeight,
                                        Dimension tileSize, ResolutionLevel level, int dimensionIndex,
                                        Variable referencedIndexVariable, String nameOfReferencingDimension,
                                        String nameOfDisplayedDimension) {
        super(variable, dataBufferType, sourceWidth, sourceHeight, tileSize, level, "", dimensionIndex, false);
        this.variable = variable;
        int displayedDimensionIndex = variable.findDimensionIndex(nameOfDisplayedDimension);
        int referencingDimensionIndex = variable.findDimensionIndex(nameOfReferencingDimension);
        this.referencedIndexVariable = referencedIndexVariable;
        final int numDetectors = variable.getDimension(referencingDimensionIndex).getLength();
        int[] variableOrigin = new int[2];
        variableOrigin[displayedDimensionIndex] = dimensionIndex;
        variableOrigin[referencingDimensionIndex] = 0;
        int[] variableShape = new int[2];
        variableShape[displayedDimensionIndex] = 1;
        variableShape[referencingDimensionIndex] = numDetectors;
        try {
            final Section detectorSection = new Section(variableOrigin, variableShape);
            final Array dimensionValuesArray = variable.read(detectorSection);
            dimensionValues = (float[])dimensionValuesArray.copyTo1DJavaArray();
        } catch (InvalidRangeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void computeRect(PlanarImage[] sourceImages, WritableRaster tile, Rectangle rectangle) {
        final int[] shape = new int[2];
        final int[] origin = new int[2];
        final int[] stride = new int[2];
        for (int i = 0; i < 2; i++) {
            shape[i] = 1;
            origin[i] = 0;
            stride[i] = 1;
        }
        final int indexX = getIndexX(2);
        final int indexY = getIndexY(2);

        shape[indexX] = getSourceWidth(rectangle.width);
        shape[indexY] = getSourceHeight(rectangle.height);

        origin[indexX] = getSourceX(rectangle.x) + getSourceOriginX();
        origin[indexY] = getSourceY(rectangle.y) + getSourceOriginY();

        final double scale = getScale();
        stride[indexX] = (int) scale;
        stride[indexY] = (int) scale;

        Array referencedValues;
        final Array variableValues = Array.factory(variable.getDataType(), shape);
        synchronized (referencedIndexVariable.getParentGroup().getNetcdfFile()) {
            try {
                final Section section = new Section(origin, shape, stride);
                referencedValues = referencedIndexVariable.read(section);
                final float noDataValue = getNoDataValue(referencedIndexVariable).floatValue();
                for(int i = 0; i < referencedValues.getSize(); i++) {
                    final int detectorIndex = referencedValues.getInt(i);
                    if(detectorIndex > - 1) {
                        variableValues.setFloat(i, dimensionValues[detectorIndex]);
                    } else {
                        variableValues.setFloat(i, noDataValue);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        tile.setDataElements(rectangle.x, rectangle.y, rectangle.width, rectangle.height, transformStorage(variableValues));
    }

    //copied from CfBandPart
    private static Number getNoDataValue(Variable variable) {
        Attribute attribute = variable.findAttribute(Constants.FILL_VALUE_ATT_NAME);
        if (attribute == null) {
            attribute = variable.findAttribute(Constants.MISSING_VALUE_ATT_NAME);
        }
        if (attribute != null) {
            return getAttributeValue(attribute);
        }
        return null;
    }

    //copied from CfBandPart
    private static Number getAttributeValue(Attribute attribute) {
        if (attribute.isString()) {
            String stringValue = attribute.getStringValue();
            if (stringValue.endsWith("b")) {
                return Byte.parseByte(stringValue.substring(0, stringValue.length() - 1));
            } else {
                return Double.parseDouble(stringValue);
            }
        } else {
            return attribute.getNumericValue();
        }
    }

}