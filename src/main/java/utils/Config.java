package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	

	private Config() {
		// private constructor to prevent instantiation
	}
	
	private static Config instance;
	private static Properties properties = new Properties();
	
	
	public static synchronized Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}
	
	
	public  void loadProperties() throws FileNotFoundException, IOException {
		
		String value = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		properties.load(new FileInputStream(value));
	}

	// to craete a method to read the value from the config file based on the key

	public  String getProperty(String key) throws FileNotFoundException, IOException {


		return properties.getProperty(key, "Key not found: " + key);

	}



}
