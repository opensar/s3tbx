package org.esa.s3tbx.olci.radiometry;

import org.esa.snap.dataio.envisat.EnvisatConstants;

/**
 * Constants for supported sensors
 *
 * @author olafd
 */
public class SensorConstants {

    public static final int MERIS_NUM_BANDS = EnvisatConstants.MERIS_L1B_NUM_SPECTRAL_BANDS;
    public static final String MERIS_SZA_NAME = "sun_zenith";
    public static final String MERIS_VZA_NAME = "view_zenith";
    public static final String MERIS_SAA_NAME = "sun_azimuth";
    public static final String MERIS_VAA_NAME = "view_azimuth";
    public static final String MERIS_OZONE_NAME = "ozone";
    public static final String MERIS_LAT_NAME = "latitude";
    public static final String MERIS_LON_NAME = "longitude";
    public static final String MERIS_ALT_NAME = "dem_alt";
    public static final String MERIS_SLP_NAME = "atm_press";
    public static final String MERIS_L1B_FLAGS_NAME = "l1_flags";
    public static final int MERIS_INVALID_BIT = 7;
    public static final int[] MERIS_BOUNDS = {13, 14};
    public static final String MERIS_NAME_FORMAT = "radiance_%d";
    public static final String MERIS_NAME_PATTERN = "radiance_\\d+";
    public static final String MERIS_BAND_INFO_FILE_NAME = "band_info_meris.txt";

    public static final int MERIS_4TH_NUM_BANDS = MERIS_NUM_BANDS;
    public static final String MERIS_4TH_SZA_NAME = "SZA";
    public static final String MERIS_4TH_VZA_NAME = "OZA";
    public static final String MERIS_4TH_SAA_NAME = "SAA";
    public static final String MERIS_4TH_VAA_NAME = "OAA";
    public static final String MERIS_4TH_OZONE_NAME = "total_ozone";
    public static final String MERIS_4TH_LAT_NAME = MERIS_LAT_NAME;
    public static final String MERIS_4TH_LON_NAME = MERIS_LON_NAME;
    public static final String MERIS_4TH_ALT_NAME = "altitude";
    public static final String MERIS_4TH_SLP_NAME = "sea_level_pressure";
    public static final String MERIS_4TH_L1B_FLAGS_NAME = "quality_flags";
    public static final int MERIS_4TH_INVALID_BIT = 25;
    public static final int[] MERIS_4TH_BOUNDS = MERIS_BOUNDS;
    public static final String MERIS_4TH_NAME_FORMAT = "M%02d_radiance";
    public static final String MERIS_4TH_NAME_PATTERN = "M\\d+_radiance";
    public static final String MERIS_4TH_BAND_INFO_FILE_NAME = MERIS_BAND_INFO_FILE_NAME;

    public static final int OLCI_NUM_BANDS = 21;
    public static final String OLCI_SZA_NAME = "SZA";
    public static final String OLCI_VZA_NAME = "OZA";
    public static final String OLCI_SAA_NAME = "SAA";
    public static final String OLCI_VAA_NAME = "OAA";
    public static final String OLCI_OZONE_NAME = "total_ozone";
    public static final String OLCI_LAT_NAME = MERIS_LAT_NAME;
    public static final String OLCI_LON_NAME = MERIS_LON_NAME;
    public static final String OLCI_ALT_NAME = "altitude";
    public static final String OLCI_SLP_NAME = "sea_level_pressure";
    public static final String OLCI_L1B_FLAGS_NAME = "quality_flags";
    public static final int OLCI_INVALID_BIT = 25;
    public static final int[] OLCI_BOUNDS = {17, 18};
    public static final String OLCI_NAME_FORMAT = "Oa%02d_radiance";
    public static final String OLCI_NAME_PATTERN = "Oa\\d+_radiance";
    public static final String OLCI_BAND_INFO_FILE_NAME = "band_info_olci.txt";

    public static final int S2_MSI_NUM_BANDS = 9; // B1-B9, we skip B10-B12 for the moment
    public static final String S2_MSI_SZA_NAME = "sun_zenith";
    public static final String S2_MSI_VZA_NAME = "view_zenith_mean";
    public static final String S2_MSI_SAA_NAME = "sun_azimuth";
    public static final String S2_MSI_VAA_NAME = "view_azimuth_mean";
    public static final String S2_MSI_OZONE_NAME = null;
    public static final String S2_MSI_LAT_NAME = "lat";
    public static final String S2_MSI_LON_NAME = "lon";
    public static final String S2_MSI_ALT_NAME = null;
    public static final String S2_MSI_SLP_NAME = null;
    public static final String S2_MSI_L1B_FLAGS_NAME = null;
    public static final int S2_MSI_INVALID_BIT = -1;
    public static final int[] S2_MSI_BOUNDS = {5, 6};
    public static final String S2_MSI_NAME_FORMAT = "B%d";
    public static final String S2_MSI_NAME_PATTERN = "B\\d+";
    public static final String S2_MSI_BAND_INFO_FILE_NAME = "band_info_S2_msi.txt";

    public static final double[] S2_SOLAR_FLUXES =
            {1913.57, 1941.63, 1822.61, 1512.79, 1425.56, 1288.32, 1163.19, 1036.39, 955.19, 813.04};

    public static final String[] S2_GEOMETRY_BANDS =
            {S2_MSI_SZA_NAME, S2_MSI_VZA_NAME, S2_MSI_SAA_NAME, S2_MSI_VAA_NAME};

    public static final String[] S2_BANDS_TO_UPSCALE =
            {"B1", "B9", S2_MSI_SZA_NAME, S2_MSI_VZA_NAME, S2_MSI_SAA_NAME, S2_MSI_VAA_NAME};

    public static final String[] S2_BANDS_TO_DOWNSCALE = {"B2", "B3", "B4", "B8"};

    public static final String[] S2_BANDS_TO_COPY = {"B5", "B6", "B7", "B8A"};

}
