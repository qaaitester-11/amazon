package base;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.Config;

public class ConfigProperty {

	
	
	public static String getProperty(String key) throws FileNotFoundException, IOException {
		
		return Config.getInstance().getProperty(key);
	}
	
	
		
	
	
	
}
