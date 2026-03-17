package base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import utils.Config;
import utils.Excelutil;

public class ConfigProperty {

	
	
	public static String getProperty(String key) throws FileNotFoundException, IOException {
		
		return Config.getInstance().getProperty(key);
	}
	
	
		
	
	
	
}
