/**
 * 
 */
package com.evan.training.spider;

import java.util.Map;

import com.evan.training.spider.ConnectionManager.HttpRequestMethod;

/****************************************************************************
* <b>Title</b>: Sandbox.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.spider
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Feb 7, 2022
* @updates:
****************************************************************************/
public class Sandbox {
	public static void main(String[] args) {
		ConnectionManager2 cm = new ConnectionManager2("smt-stage.qa.siliconmtn.com", 443);
		IOManager io = new IOManager("sandbox/"); 
		//HtmlParser parser = new HtmlParser();
		
		HttpRequestVO request = new HttpRequestVO(HttpRequestMethod.get, cm.getHost(), "/");
		System.out.println(request.toString() + "\n");
		String page1 = cm.httpsRequest(request);
		//String page = io.readTextFile("home");
		io.writeTextFile(page1, "home");
		
		Map<String, String> cookieMap = cm.extractCookies(page1);
		
		/*
		for (var key: cookieMap.keySet()) {
			System.out.println(key + ": " + cookieMap.get(key));
		}
		*/
		
		request.setHeader("JSESSIONID", cookieMap.get("JSESSIONID"));
		
		System.out.println(request.toString());
		
		String page2 = cm.httpsRequest(request);
		io.writeTextFile(page1, "home2");
	}
}
