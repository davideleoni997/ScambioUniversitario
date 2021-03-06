package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Property {
	//Class to write and read property to a file
	
	String resourceFilePath = "project.properties";
	Properties data;
	
	public Property() {
		//Create a properties object and open the property file
		data = new Properties();
		try {
		   //Stream associato al file di proprieta'
		   InputStream stream = getClass().getResourceAsStream(resourceFilePath);
		   
		   data.load(stream);
		   stream.close();
		} catch (IOException e){
		   Logger.getGlobal().log(Level.WARNING,"LoadProperties",e);
		}
	}
	
	public String loadProperty(String prop) {
		//return the property value corresponding to the key prop
		return data.getProperty(prop);
	}

	public void setProperty(String key, String value) {
		//write the property key with the value "value" to the file
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource("util/project.properties");
			File file = new File(url.toURI().getPath());
			FileOutputStream out = new FileOutputStream(file);
			data.setProperty(key, value);
			data.store(out,null);	
			out.close();
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"Saving properties",e);
		}
		}
	
}
