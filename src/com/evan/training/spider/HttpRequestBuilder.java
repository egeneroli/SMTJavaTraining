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
public class HttpRequestBuilder {
	private String body;
	private LinkedHashMap<String, String> headers;
	private HttpRequestMethod method;
	private String host;
	private String path = "/";
	
	/** constructor - instantiates request builder class w/ specified host, method, path defaults to root "/"
	 * @param method - type of http request to be executed ie. get, post, head
	 * @param host - host name for request
	 */
	public HttpRequestBuilder(HttpRequestMethod method, String host) {
		this.method = method;
		this.host = host;
		headers = new LinkedHashMap<>();
		setHeader("Host", host);
	}
	
	/** overloaded constructor - instantiates request builder class w/ specified host, method, path
	 * @param method - type of http request to be executed ie. get, post, head
	 * @param host - host name for request
	 * @param path - resource path to be requested ie /about
	 */
	public HttpRequestBuilder(HttpRequestMethod method, String host, String path) {
		this.method = method;
		this.host = host;
		this.path = path;
		headers = new LinkedHashMap<>();
		setHeader("Host", host);
	}
	
	/**
	 * add header with specified name and value
	 * @param name - name of header to add ie. "Connection"
	 * @param value - value of header to add ie. "keep-alive"
	 */
	public void setHeader(String name, String value) {
		headers.put(name, value);
	}
	
	/**
	 * removes header with specified name
	 * @param name - name of header to remove ie. "Cookie", "User-Agent"
	 */
	public void removeHeader(String name) {
		headers.remove(name);
	}

	/**
	 * sets request body text
	 * @param body - text to set as request body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Overridden toString method - creates http request string
	 */
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
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "www.siliconmtn.com","/about");
		request.setHeader("Connection", "keep-alive");
		request.setBody("username and password to log in.");
		System.out.println(request.toString());
	}
}
