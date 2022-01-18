/**
 * 
 */
package com.evan.training.spider;

import java.util.LinkedHashMap;

import com.evan.training.spider.ConnectionManager.HttpRequestMethod;

/****************************************************************************
* <b>Title</b>: httpRequestBuilder.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.spider
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Jan 17, 2022
* @updates:
****************************************************************************/
public class httpRequestBuilder {
	private String body;
	private LinkedHashMap<String, String> headers;
	private HttpRequestMethod method;
	private String host;
	private String path = "/";
	
	public httpRequestBuilder(HttpRequestMethod method, String host) {
		this.method = method;
		this.host = host;
		headers = new LinkedHashMap<>();
		setHeader("Host", host);
	}
	
	public httpRequestBuilder(HttpRequestMethod method, String host, String path) {
		this.method = method;
		this.host = host;
		this.path = path;
		headers = new LinkedHashMap<>();
		setHeader("Host", host);
	}
	
	public void setHeader(String name, String value) {
		headers.put(name, value);
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(method.toString().toUpperCase()+" "+path+" HTTP/1.1\r\n");
		//headers.put("Host", host);
		
		for (var key: headers.keySet()) {
			sb.append(key+": "+headers.get(key)+"\r\n");
		}
		
		sb.append("\r\n");
		
		if (body != null) {
			sb.append(body+"\r\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		httpRequestBuilder request = new httpRequestBuilder(HttpRequestMethod.get, "www.siliconmtn.com","/about");
		request.setHeader("Connection", "keep-alive");
		request.setBody("username and password to log in.");
		System.out.println(request.toString());
	}
}
