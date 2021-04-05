package com.assessment.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.testng.Reporter;

import com.assessment.core.CommonApi;
import com.assessment.core.TestBase;


public class FileUtility  {

	public static String currentDir = System.getProperty("user.dir");
	public static String fileSeparator =System.getProperty("file.separator");
	
	/**
	 * Method used to write to file
	 * @param fileName name of file
	 * @param dataArray array list of 
	 */
	public static void writeToFile(String fileName,List<String> dataArray) {
		  String filePath = currentDir+fileSeparator+"output"+fileSeparator+fileName;
		  File fileObject = new File(filePath);
		  try {
			  
			 if(!fileObject.exists()) {
				 fileObject.createNewFile();
			 }else {
				 fileObject.delete();
				 fileObject.createNewFile();
				 System.out.println(fileObject.getName()+" File Created\n");
			 }
			
			  FileWriter fileWriter = new FileWriter(filePath);
			  BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			  
			  for(int i=0;i<dataArray.size()-1;i++) {
				  bufferWriter.append(dataArray.get(i).toString());
				  bufferWriter.append("\n");
			  }
			  
			  bufferWriter.close();
			  
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void ReadFromFile(String fileName) {
		 String filePath = currentDir+fileSeparator+"output"+fileSeparator+fileName;
		  FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  BufferedReader bufferReader = new BufferedReader(fileReader);
		  String Content = "";
		  
		  System.out.println("*******Displaying From File*******:"+fileName);
		  Reporter.log("*******Displaying From File*******:"+fileName);
		  try {
			while((Content = bufferReader.readLine())!= null){
			   System.out.println(Content);
			   Reporter.log(Content);
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
