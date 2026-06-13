package com.knowbefore.constants;

public final class AppConstants {

    private AppConstants() {}

    // Base URLs
    public static final String BASE_URL = "https://theknowbefore.com";

    // Page URLs
    public static final String HOME_URL = "/";
    public static final String COUNTRIES_URL = "/countries";
    public static final String TOPICS_URL = "/topics";
    public static final String REGIONS_EUROPE_URL = "/regions/europe";
    public static final String REGIONS_ASIA_URL = "/regions/asia";
    public static final String REGIONS_AMERICAS_URL = "/regions/americas";
    public static final String REGIONS_MIDDLE_EAST_URL = "/regions/middle-east";
    public static final String REGIONS_AFRICA_OCEANIA_URL = "/regions/africa-oceania";

    // Country URLs
    public static final String JAPAN_URL = "/japan";
    public static final String UAE_URL = "/uae";
    public static final String THAILAND_URL = "/thailand";
    public static final String GERMANY_URL = "/germany";
    public static final String UK_URL = "/uk";
    public static final String FRANCE_URL = "/france";
    public static final String AUSTRALIA_URL = "/australia";
    public static final String SINGAPORE_URL = "/singapore";
    public static final String ITALY_URL = "/italy";
    public static final String TURKEY_URL = "/turkey";
    public static final String US_URL = "/us";
    public static final String MEXICO_URL = "/mexico";

    // Topic URLs
    public static final String TIPPING_URL = "/tipping";
    public static final String PUBLIC_TRANSPORT_URL = "/public-transport";
    public static final String TOURIST_HEALTHCARE_URL = "/tourist-healthcare";
    public static final String LOCAL_LAWS_URL = "/local-laws";
    public static final String EMERGENCY_NUMBERS_URL = "/emergency-numbers";
    public static final String DRESS_CODE_URL = "/dress-code";
    public static final String ALCOHOL_RULES_URL = "/alcohol-rules";
    public static final String WATER_SAFETY_URL = "/water-safety";
    public static final String TAXI_RIDESHARE_URL = "/taxi-and-rideshare";
    public static final String SIM_INTERNET_URL = "/sim-card-and-internet";
    public static final String ELECTRICITY_PLUGS_URL = "/electricity-and-plugs";
    public static final String CRIME_SAFETY_URL = "/crime-and-safety";
    public static final String DRIVING_RULES_URL = "/driving-rules";
    public static final String WEATHER_URL = "/weather-and-best-time";
    public static final String MONEY_SAVING_URL = "/money-saving-tips";

    // Compare URL pattern
    public static final String COMPARE_URL = "/compare";

    // Page Titles
    public static final String HOME_PAGE_TITLE = "KnowBefore";
    public static final String COUNTRIES_PAGE_TITLE = "All Countries";
    public static final String TOPICS_PAGE_TITLE = "All Topics";

    // Timeout Values
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int SHORT_WAIT = 5;
    public static final int LONG_WAIT = 40;

    // Test Data
    public static final String VALID_COUNTRY_JAPAN = "Japan";
    public static final String VALID_COUNTRY_UAE = "UAE";
    public static final String VALID_COUNTRY_GERMANY = "Germany";
    public static final String VALID_COUNTRY_THAILAND = "Thailand";
    public static final String INVALID_COUNTRY = "Xyzxyzxyz123";
    public static final String SPECIAL_CHARS = "@#$%^&*";
    public static final String NUMERIC_SEARCH = "12345";
    public static final String PARTIAL_COUNTRY = "Jap";
    public static final String JAPAN_TIPPING_ANSWER = "not";
    public static final String UAE_ALCOHOL_WARNING = "licensed";
    public static final String GERMANY_PLUG_TYPE = "Type F";
    public static final String UK_DRIVING = "left";

    // Region Names
    public static final String REGION_EUROPE = "Europe";
    public static final String REGION_ASIA = "Asia";
    public static final String REGION_AMERICAS = "Americas";
    public static final String REGION_MIDDLE_EAST = "Middle East";
    public static final String REGION_AFRICA_OCEANIA = "Africa & Oceania";

    // Report
    public static final String REPORT_NAME = "KnowBefore_Automation_Report";
    public static final String REPORT_TITLE = "KnowBefore Test Execution Report";

    // Screenshot
    public static final String SCREENSHOT_FORMAT = "png";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd_HH-mm-ss";

    // Excel Headers
    public static final String SHEET_COUNTRIES = "Countries";
    public static final String SHEET_TOPICS = "Topics";
    public static final String SHEET_SEARCH = "SearchData";

    // Navigation Menu Items
    public static final String NAV_COUNTRIES = "Countries";
    public static final String NAV_TOPICS = "Topics";

    // Total counts
    public static final int TOTAL_COUNTRIES = 50;
    public static final int TOTAL_TOPICS = 25;
    public static final int TOTAL_REGIONS = 5;
    public static final int TOTAL_COMPARE_PAIRS = 20;
}
