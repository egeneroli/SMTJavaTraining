/**
 * 
 */
package com.evan.training.spider;

import java.util.HashSet;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
		String homePage = cm.httpsRequest("www.siliconmtn.com", 443, HttpRequestMethod.get, "/");
		//System.out.println(homePage + "\n");
		
		// Save homepage html
		IOManager io = new IOManager("spiderHtml/");
		io.writeTextFile(homePage, "home");
		//String homePage = io.readTextFile("homePage");
		//System.out.println(homePage);
		
		// Extract and store cookies
		/* ArrayList<String> cookieList = cm.extractCookies(homePage);
		for (String s: cookieList) {
			System.out.println(s);
		} */
		
		
		// Parse homepage html, extract links from homepage html, store in set
		HtmlParser parser = new HtmlParser();
		HashSet<String> linkSet = parser.extractLinks(homePage);

		// Repeat while more links
		Iterator<String> itr = linkSet.iterator();
		while (itr.hasNext()) {
			// get next link from linkSet iterator
			String link = itr.next();
			
			// Get page for link
			String pageHtml = cm.httpsRequest("www.siliconmtn.com", 443, HttpRequestMethod.get, link);
			
			// Save html from page
			io.writeTextFile(pageHtml, link.substring(1));
			
			// Parse html, extract any more links, add to link set
			HashSet<String> additionalLinks = parser.extractLinks(pageHtml);
			linkSet.addAll(additionalLinks);
		}

		for (String link: linkSet) {
			System.out.println(link);
		}
		
		// Get admin tool page
		
		// Log-in to admintool page
		
		// Get "schedule job instances" page
		
		// Save html from page
	}
}
