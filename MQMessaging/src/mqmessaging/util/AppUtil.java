package mqmessaging.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtil {
	private Properties prop = new Properties();
	private InputStream input;
	
	public AppUtil(){
		try {
			input = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public String getProperty(String property){
		return prop.getProperty(property);		
	}

}
