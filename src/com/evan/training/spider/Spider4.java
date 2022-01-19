/**
 * 
 */
package com.evan.training.spider;

import java.util.ArrayList;
import java.util.LinkedHashSet;

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
public class Spider4 {
	// member variables?
		// cookies? -- or make member variable in ConnectionManager
	
	/**
	 * initializes program, instantiates Spider class and calls workflow method 
	 * @param args - command line arguments, none necessary
	 */
	public static void main(String[] args) {
		Spider4 s = new Spider4();
		s.run();
	}
	
	/**
	 * workflow method - lays out primary program logic, calls other classes/methods
	 */
	public void run() {
		// Get siliconmtn.com homepage
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "www.siliconmtn.com", "/", 443);
		String homePage = cm.httpsRequest(request);
		//System.out.println(homePage + "\n");
		
		// Save homepage html
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory to read/write from
		io.writeTextFile(homePage, "home");
		//String homePage = io.readTextFile("homePage");
		//System.out.println(homePage);
		
		// Extract and store cookies
		/* ArrayList<String> cookieList = cm.extractCookies(homePage);
		for (String s: cookieList) {
			System.out.println(s);
		} */
		
		
		// Parse homepage html, extract links from homepage html, store in set
		//HtmlParser parser = new HtmlParser();
		//LinkedHashSet<String> linkSet = (LinkedHashSet<String>) parser.extractLinks(homePage);
		
		HtmlParser2 parser = new HtmlParser2();
		LinkedHashSet<String> linkSet = parser.extractLinks(homePage);
		//ArrayList<String> linkList = new ArrayList<String>(linkSet); // convert to ArrayList for iterating
		
		// Repeat while more links
		//Iterator<String> itr = linkSet.iterator();
		//while (itr.hasNext()) {
		for (String link: linkSet) {
			// get next link from linkSet iterator
			//String link = itr.next();
			
			// Get page for link
			request.setPath(link);
			String page = cm.httpsRequest(request);
			
			// Save html from page, substring removes beginning "/" for filename
			io.writeTextFile(page, link.substring(1));
			
			// Parse html, extract any more links, add to link set
			LinkedHashSet<String> additionalLinks = (LinkedHashSet<String>) parser.extractLinks(page);
			linkSet.addAll(additionalLinks);
		}
		
		// Get admin tool page
		request.setPath("/admintool");
		String adminPage = cm.httpsRequest(request);
		io.writeTextFile(adminPage, "admintool");
		//System.out.println(adminPage);
		
		// Log-in to admintool page
		
		// Get "schedule job instances" page
		
		// Save html from page
	}
}
