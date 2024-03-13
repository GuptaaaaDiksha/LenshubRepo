package web.config;

import utils.FileOperations;
import web.constants.Constants;

public abstract class WebConfig {

	public static String ENV = getWebConfig("Env");
	public static String ADMIN_URL = getWebConfig("Base_Url").replace("env", ENV.toLowerCase());
	public static String BROWSER = getWebConfig("Browser");
	public static String HEADLESS = getWebConfig("Headless");
	public static String EMAIL = getWebConfig("Email");
	public static String PASSWORD = getWebConfig("Password");
	public static String MOBILE_NUMBER = getWebConfig("Mobile_Num");
	public static String OTP = getWebConfig("OTP");

	public static void updateWebConfig(String key, String value) {
		FileOperations.updatePropertyFileValue(Constants.CONFIG_WEB_FILE_PATH, key, value);
	}

	public static String getWebConfig(String key) {
		return FileOperations.getValueFromPropertyFile(Constants.CONFIG_WEB_FILE_PATH, key);
	}
}
