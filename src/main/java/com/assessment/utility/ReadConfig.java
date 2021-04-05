package com.assessment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties propertyRef;
	public String currentDir = System.getProperty("user.dir");
	public String fileSeparator =System.getProperty("file.separator");
	
	public ReadConfig()
	{
		File src = new File(currentDir+fileSeparator+"src"+fileSeparator+"test"+fileSeparator+"resources"+fileSeparator+"configuration"+fileSeparator+"config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			propertyRef = new Properties();
			propertyRef.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url=propertyRef.getProperty("baseURL");
		return url;
	}
	

	
}


