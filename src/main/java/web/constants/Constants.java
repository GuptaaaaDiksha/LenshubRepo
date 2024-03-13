package web.constants;

import utils.GenericMethods;

public abstract class Constants {

	public static final String CONFIG_WEB_FILE_PATH = GenericMethods.getProjectRootDirectory()
			+ "/WebConfig.properties";
	public static final String USER_ADMIN = "admin";
	public static final String USER_CUSTOMER = "customer";
}
