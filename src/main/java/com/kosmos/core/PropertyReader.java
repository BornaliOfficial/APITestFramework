package com.kosmos.core;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.kosmos.core.FileHandler;

public class PropertyReader extends FileHandler{
		
		private String propPath = "";	
		
		public PropertyReader(String path) {
			this.propPath = path;
		}
		
		public String getPropPath() {
			return this.propPath;
		}

		public String getPropertyValue(String propertyName) {
			FileReader reader = readStream(this.propPath);
			Properties p = new Properties();
			try {
				p.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String property = p.getProperty(propertyName);
			return property;
		}
}
