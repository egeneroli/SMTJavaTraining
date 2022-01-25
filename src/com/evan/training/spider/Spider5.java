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
public class Spider5 {
	// member variables?
		// cookies? -- or make member variable in ConnectionManager
	
	/**
	 * initializes program, instantiates Spider class and calls run (workflow) method 
	 * @param args - command line arguments, none necessary
	 */
	public static void main(String[] args) {
		Spider5 s = new Spider5();
		s.run();
	}
	
	/**
	 * workflow method - lays out primary program logic, calls other classes/methods
	 */
	public void run() {

		getHomePageAndAllLinkedPages();
		
		getAdminToolPage();
		
		String postResponse = postAdminToolLogin();
		
		getScheduleJobInstancesPage(postResponse);
	
	}
	
	public void getHomePageAndAllLinkedPages() {
		
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory rel. to SMTJavaTraining
		// Extract and store cookies
		//ArrayList<String> cookieList = cm.extractCookies(homePage);
	
		// Set up for loop, start with root directory "/" in list, more will be added from parsing homepage
		HtmlParser parser = new HtmlParser();
		ArrayList<String> links = new ArrayList<>();
		links.add("/");
		
		// Repeat while more links
		for (int i=0; i<links.size(); i++) {
			// Get page for link
			String link = links.get(i);
			request.setPath(link);
			String page = cm.httpsRequest(request);

			// Save html from page, substring removes beginning "/" for filename
			io.writeTextFile(page, link.equals("/")?"home":link.substring(1));
			
			// Get any links present in page, add to link list if not already there
			for (String path: parser.extractLinks(page)) {
				if (!links.contains(path)) links.add(path);
			}
		}
	}

	public void getAdminToolPage() {
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory rel. to SMTJavaTraining
		
		// Get admin tool page
		request.setPath("/admintool");
		String adminPage = cm.httpsRequest(request);
		io.writeTextFile(adminPage, "admintool");
	}
	
	public String postAdminToolLogin() {
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory rel. to SMTJavaTraining
		
		// Log-in to admintool page
		request.setMethod(HttpRequestMethod.post);
		request.setPath("/admintool");
		request.setBody("emailAddress=evan.generoli@silconmtn.com&password=SMTRules~!1");
		request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setHeader("Content-Length", request.getBody().length()+"");
		String post = cm.httpsRequest(request);
		io.writeTextFile(post, "postResponse");
		
		return post;
	}
	
	public void getScheduleJobInstancesPage(String loginPostResponse) {
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		//String homePage = cm.httpsRequest(request);
		//System.out.println(homePage + "\n");
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager, specify directory rel. to SMTJavaTraining
		
		// Get "schedule job instances" page
		ArrayList<String> cookies = cm.extractCookies(loginPostResponse);
		cookies.add("smt.admin.loginComplete=1");
		request.setHeader("Cookie", String.join("; ", cookies));
		request.removeHeader("Content-Type");
		request.removeHeader("Content-Length");
		request.setBody("");
		request.setPath("/admintool?actionId=SCHEDULE_JOB_INSTANCE");
		request.setMethod(HttpRequestMethod.get);
		request.setHeader("Connection", "keep-alive");
		request.setHeader("Accept-Language","en-US,en;q=0.5");
		request.setHeader("Content-Type", "text/html;charset=UTF-8");
		request.setHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:96.0) Gecko/20100101 Firefox/96.0");
		request.setHeader("Accept-Encoding", "gzip, deflate, br");
		request.setHeader("Cache-Control", "max-age=0");
		request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
		request.setHeader("Referer", "https://smt-stage.qa.siliconmtn.com/sb/admintool");
		String scheduleJobInstances = cm.httpsRequest(request);
		// Save html from page
		io.writeTextFile(scheduleJobInstances, "admintoolScheduleJobInstances");
	}
}
