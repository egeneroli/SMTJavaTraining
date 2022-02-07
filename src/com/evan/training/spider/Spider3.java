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
public class Spider3 {
	// member variables?
		// cookies? -- or make member variable in ConnectionManager
	
	/**
	 * initializes program, instantiates Spider class and calls workflow method 
	 * @param args - command line arguments, none necessary
	 */
	public static void main(String[] args) {
		Spider3 s = new Spider3();
		s.run();
	}
	
	/**
	 * workflow method - lays out primary program logic, calls other classes/methods
	 */
	public void run() {
		// Get siliconmtn.com homepage
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestVO request = new HttpRequestVO(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		String homePage = cm.httpsRequest(request);
		//System.out.println(homePage + "\n");
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory rel. to SMTJavaTraining
		io.writeTextFile(homePage, "home");			// Save homepage html
		
		//String homePage = io.readTextFile("homePage");
		//System.out.println(homePage);
		
		// Extract and store cookies
		//ArrayList<String> cookieList = cm.extractCookies(homePage);
	
		// Parse homepage html, extract links from homepage html, store in set
		HtmlParser parser = new HtmlParser();
		HashSet<String> linkSet = parser.extractLinks(homePage);
		//HashSet<String> linkSet = new HashSet<>();
		//linkSet.add("/");
		
		// Repeat while more links
		Iterator<String> itr = linkSet.iterator();
		while (itr.hasNext()) {
			// get next link from linkSet iterator
			String link = itr.next();
			
			// Get page for link
			request.setPath(link);
			String page = cm.httpsRequest(request);
			
			// Save html from page, substring removes beginning "/" for filename
			io.writeTextFile(page, link.substring(1)+"Page");
			
			// Parse html, extract any more links, add to link set
			HashSet<String> additionalLinks = parser.extractLinks(page);
			linkSet.addAll(additionalLinks);
		}
		
		
		// Get admin tool page
		request.setPath("/admintool");
		String adminPage = cm.httpsRequest(request);
		io.writeTextFile(adminPage, "admintool");
		//System.out.println(adminPage);
		
		// Log-in to admintool page
		request.setMethod(HttpRequestMethod.post);
		request.setBody("emailAddress=evan.generoli@silconmtn.com&password=SMTRules~!1");
		request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setHeader("content-length", request.getBody().length()+"");
		String post = cm.httpsRequest(request);
		io.writeTextFile(post, "postResponse");
		//System.out.println(post);
		
		//getting 302 moved response (to /admintool) - likely due to no cookies in request after login
		/*
		// Get "schedule job instances" page
		request.setPath("/admintool?actionId=SCHEDULE_JOB_INSTANCE");
		request.setMethod(HttpRequestMethod.get);
		String scheduleJobInstances = cm.httpsRequest(request);
		// Save html from page
		io.writeTextFile(scheduleJobInstances, "admintoolScheduleJobInstances");
		//System.out.println(scheduleJobInstances);
		*/
	}
}
