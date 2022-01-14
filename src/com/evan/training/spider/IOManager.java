/**
 * 
 */
package com.evan.training.spider;

import java.io.File;
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
	
	/**
	 * writes string to file
	 * @param text - string to be saved
	 * @param filepath - relative filepath where text will be saved
	 */
	public void writeToFile(String text, String filepath) {
		try {
			FileWriter fileOut = new FileWriter(new File(filepath));
			fileOut.write(text);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
