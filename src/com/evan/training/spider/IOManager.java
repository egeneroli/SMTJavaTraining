/**
 * 
 */
package com.evan.training.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/****************************************************************************
* <b>Title</b>: IOManager.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.spider
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Jan 11, 2022
* @updates:
****************************************************************************/
public class IOManager {

	String rootDirectory;
	
	/**
	 * constructor
	 * @param rootDirectory - filepath of root directory, absolute or relative to /eclipse-workspace/SMTJavaTraining/
	 */
	public IOManager(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	/**
	 * overloaded constructor - allows IOManager to be created without constructor arguments - will use base directory of /eclipse-workspace/SMTJavaTraining/
	 */
	public IOManager() {
		this.rootDirectory = "";
	}
	
	/**
	 * writes string to file
	 * @param text - string to be saved
	 * @param filepath - relative filepath where text will be saved
	 */
	public void writeTextFile(String text, String filepath) {
		try {
			//FileWriter fileOut = new FileWriter(new File(filepath));
			BufferedWriter fileOut = new BufferedWriter(new FileWriter(this.rootDirectory+filepath+".txt"));
			fileOut.write(text);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * writes string to file
	 * @param text - string to be saved
	 * @param filepath - relative filepath where text will be saved
	 */
	public String readTextFile(String filepath) {
		StringBuilder sb = new StringBuilder();
		try {
			//FileReader fileIn = new FileReader(new File(filepath));
			BufferedReader fileIn = new BufferedReader(new FileReader(this.rootDirectory+filepath));
			//int c;
			String line;
			while ((line = fileIn.readLine()) != null) {
				sb.append(line + "\n");
			}
			fileIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
