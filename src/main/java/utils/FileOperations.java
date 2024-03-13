package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class FileOperations {

	public static String getValueFromPropertyFile(String path, String key) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public static void updatePropertyFileValue(String path, String key, String value) {

		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration(path);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		config.setProperty(key, value);
		try {
			config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
