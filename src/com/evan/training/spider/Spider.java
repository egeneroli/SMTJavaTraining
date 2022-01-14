/**
 * 
 */
package com.evan.training.spider;

import java.util.ArrayList;

import com.evan.training.spider.ConnectionManager.HttpRequestMethod;

/****************************************************************************
* <b>Title</b>: Spider.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.spider
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Jan 11, 2022
* @updates:
****************************************************************************/
public class Spider {
	// member variables?
		// cookies? -- or make member variable in ConnectionManager
	
	/**
	 * initializes program, instantiates Spider class and calls workflow method 
	 * @param args - command line arguments, none necessary
	 */
	public static void main(String[] args) {
		Spider s = new Spider();
		s.run();
	}
	
	/**
	 * workflow method - lays out primary program logic, calls other classes/methods
	 */
	public void run() {
		// Get siliconmtn.com homepage
		ConnectionManager cm = new ConnectionManager();
		String homePage = cm.httpsRequest("www.siliconmtn.com", 443, HttpRequestMethod.head);
		System.out.println(homePage + "\n");
		
		// Save homepage html
		IOManager io = new IOManager();
		io.writeToFile(homePage, "homePage.txt");
		
		// Extract and store cookies
		ArrayList<String> cookieList = cm.extractCookies(homePage);
		
		// Parse homepage html
		
		// Extract links from homepage html, store in set
		
		// Repeat while more links
			// Get page for link
		String aboutPage = cm.httpsRequest("www.siliconmtn.com", 443, HttpRequestMethod.head, cookieList, "about");
		System.out.println(aboutPage);
			
			// Save html from page
			
			// Parse html
			
			// Extract any more links, add to link set
		
		// Get admin tool page
		
		// Log-in to admintool page
		
		// Get "schedule job instances" page
		
		// Save html from page
	}
}
