/**
 * 
 */
package com.evan.training.spider;

import java.util.ArrayList;

import com.evan.training.spider.ConnectionManager2.HttpRequestMethod;


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
public class Spider2 {
	// member variables?
		// cookies? -- or make member variable in ConnectionManager
	
	/**
	 * initializes program, instantiates Spider class and calls workflow method 
	 * @param args - command line arguments, none necessary
	 */
	public static void main(String[] args) {
		Spider2 s = new Spider2();
		s.run();
	}
	
	/**
	 * workflow method - lays out primary program logic, calls other classes/methods
	 */
	public void run() {
		// Get siliconmtn.com homepage
		ConnectionManager2 cm = new ConnectionManager2("www.siliconmtn.com", 443);
		String homePage = cm.httpsRequest("", HttpRequestMethod.head);
		//System.out.println(homePage + "\n");
		
		// Save homepage html
		IOManager io = new IOManager();
		io.writeTextFile(homePage, "homePage");
		//String homePage = io.readTextFile("homePage");
		System.out.println(homePage);
		
		// Extract and store cookies
		ArrayList<String> cookieList = cm.extractCookies(homePage);
		for (String s: cookieList) {
			//System.out.println(s);
			continue;
		}
		
		// Parse homepage html
		
		// Extract links from homepage html, store in set
		
		// Repeat while more links
			// Get page for link
		//String headerString = "Cookie: " + String.join("; ", cookieList) + "\r\n";
		//headerString += "Connection: keep-alive\r\n";
		//System.out.println(headerString);
		String aboutPage = cm.httpsRequest("about", HttpRequestMethod.head);
		System.out.println(aboutPage);
			
			// Save html from page
			
			// Parse html
			
			// Extract any more links, add to link set
		
		// Get admin tool page
		
		// Log-in to admintool page
		
		// Get "schedule job instances" page
		
		// Save html from page
		
		
		
		cm.close();
	}
}
