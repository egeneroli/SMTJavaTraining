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
public class Spider6 {
	// member variables?
		// cookies? -- or make member variable in ConnectionManager
	
	/**
	 * initializes program, instantiates Spider class and calls run (workflow) method 
	 * @param args - command line arguments, none necessary
	 */
	public static void main(String[] args) {
		Spider6 s = new Spider6();
		s.run();
	}
	
	/**
	 * workflow method - lays out primary program logic, calls other classes/methods
	 */
	public void run() {
		// Get siliconmtn.com homepage
		ConnectionManager3 cm = new ConnectionManager3();
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "smt-stage.qa.siliconmtn.com", "/", 443);
		IOManager io = new IOManager("spiderHtml/"); // instantiate IOManager on directory relative to SMTJavaTraining
		HtmlParser parser = new HtmlParser();
		
		ArrayList<String> links = new ArrayList<>();
		links.add("/");
		
		// Repeat while more links
		for (int i = 0; i < links.size(); i++) {
			// get next link from linkSet iterator
			String link = links.get(i);
			
			// Get page for link
			request.setPath(link);
			String page = cm.httpsRequest(request);
			
			// Save html from page, substring removes beginning "/" for filename, name "/" home
			io.writeTextFile(page, link.equals("/")?"home":link.substring(1));
			
			// get any links from page, add to link list if not already present
			//HashSet<String> additionalLinks = parser.extractLinks(page);
			for (String path: parser.extractLinks(page)) {
				if (!links.contains(path)) links.add(path);
			}
		}
		
		
		// Get admin tool page
		request.setPath("/admintool");
		String adminPage = cm.httpsRequest(request);
		io.writeTextFile(adminPage, "admintool");

		
		// Log-in to admintool page
		request.setMethod(HttpRequestMethod.post);
		request.setBody("emailAddress=evan.generoli@silconmtn.com&password=SMTRules~!1");
		request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setHeader("Content-Length", request.getBody().length()+"");
		String post = cm.httpsRequest(request);
		io.writeTextFile(post, "postResponse");
		
	
		// Get "schedule job instances" page
		ArrayList<String> cookies = cm.extractCookies(post);
		//cookies.add("smt.admin.loginComplete=1");
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

	public String GetAdminToolScheduleJobInstancesPage(HttpRequestBuilder request, ConnectionManager3 cm, String postLoginResponse) {
		ArrayList<String> cookies = cm.extractCookies(postLoginResponse);
		//cookies.add("smt.admin.loginComplete=1");
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
		return scheduleJobInstances;
	}
}
