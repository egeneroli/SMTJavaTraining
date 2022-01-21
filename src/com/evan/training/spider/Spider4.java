/**
 * 
 */
package com.evan.training.spider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
	 * initializes program, instantiates Spider class and calls run (workflow) method 
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
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		//String homePage = cm.httpsRequest(request);
		//System.out.println(homePage + "\n");
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory rel. to SMTJavaTraining
		//io.writeTextFile(homePage, "home");			// Save homepage html
		
		//String homePage = io.readTextFile("homePage");
		//System.out.println(homePage);
		
		// Extract and store cookies
		//ArrayList<String> cookieList = cm.extractCookies(homePage);
	
		// Parse homepage html, extract links from homepage html, store in set
		HtmlParser parser = new HtmlParser();
		//HashSet<String> linkSet = parser.extractLinks(homePage);
		//HashSet<String> linkSet = new HashSet<>();
		//HashSet<String> visited = new HashSet<>();
		//linkSet.add("/");
		//Queue<String> linkQueue = new LinkedList<>();
		//linkQueue.add("/");
		
		
		ArrayList<String> links = new ArrayList<>();
		links.add("/");
		
		// Repeat while more links
		//Iterator<String> itr = linkSet.iterator();
		//while (itr.hasNext()) {
		//while (!linkQueue.isEmpty()) {
		for (int i=0; i<links.size(); i++) {
			// get next link from linkSet iterator
			//String link = itr.next();
			//String link = linkQueue.poll();
			String link = links.get(i);
			
			// Get page for link
			request.setPath(link);
			String page = cm.httpsRequest(request);
			//visited.add(link);
			
			// Save html from page, substring removes beginning "/" for filename
			//io.writeTextFile(page, link.substring(1)+"Page");
			if (link.equals("/")) {
				io.writeTextFile(page, "home");
			} else {
				io.writeTextFile(page, link.substring(1));
			}
			
			// Parse html, extract any more links, add to link set
			//HashSet<String> additionalLinks = parser.extractLinks(page);
			//linkSet.addAll(additionalLinks);
			HashSet<String> additionalLinks = parser.extractLinks(page);
			for (String path: additionalLinks) {
				if (!links.contains(path)) {
					links.add(path);
				}
			}
		}
		
		
		// Get admin tool page
		request.setPath("/admintool");
		//String adminPage = cm.httpsRequest(request);
		//io.writeTextFile(adminPage, "admintool");
		//System.out.println(adminPage);
		
		// Log-in to admintool page
		request.setMethod(HttpRequestMethod.post);
		request.setBody("emailAddress=evan.generoli@silconmtn.com&password=SMTRules~!1");
		request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setHeader("Content-Length", request.getBody().length()+"");
		String post = cm.httpsRequest(request);
		io.writeTextFile(post, "postResponse");
		//System.out.println(post);
		
		//getting 302 moved response (to /admintool) - likely due to no cookies in get request after login
		// Get "schedule job instances" page
		ArrayList<String> cookies = cm.extractCookies(post);
		//String cookieHeaderString = String.join("; ", cookies);
		//System.out.println(cookieHeaderString+" ");
		request.setHeader("Cookie", String.join("; ", cookies));
		request.removeHeader("Content-Type");
		request.removeHeader("Content-Length");
		request.setBody("");
		request.setPath("/admintool?actionId=SCHEDULE_JOB_INSTANCE");
		request.setMethod(HttpRequestMethod.get);
		request.setHeader("Connection", "keep-alive");
		request.setHeader("Accept-Language","en-us");
		request.setHeader("Content-Type", "text/html;charset=UTF-8");
		request.setHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:96.0) Gecko/20100101 Firefox/96.0");
		request.setHeader("Accept-Encoding", "gzip, deflate, br");
		String scheduleJobInstances = cm.httpsRequest(request);
		// Save html from page
		io.writeTextFile(scheduleJobInstances, "admintoolScheduleJobInstances");
		//System.out.println(scheduleJobInstances);
		
	}
}
