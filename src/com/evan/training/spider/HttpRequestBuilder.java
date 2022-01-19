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
	private String body = "";
	private LinkedHashMap<String, String> headers;
	private HttpRequestMethod method;
	private String host;
	private String path = "/";
	private int port;
	
	/** constructor - instantiates request builder class w/ specified host, method, path defaults to root "/"
	 * @param method - type of http request to be executed ie. get, post, head
	 * @param host - host name for request
	 */
	/*
	public HttpRequestBuilder(HttpRequestMethod method, String host, int port) {
		this.port = port;
		this.method = method;
		this.host = host;
		headers = new LinkedHashMap<>();
		headers.put("Host", host);
	}
	*/
	
	/** overloaded constructor - instantiates request builder class w/ specified host, method, path
	 * @param method - type of http request to be executed ie. get, post, head
	 * @param host - host name for request
	 * @param path - resource path to be requested ie. /about
	 */
	public HttpRequestBuilder(HttpRequestMethod method, String host, String path, int port) {
		this.port = port;
		this.method = method;
		this.host = host;
		this.path = path;
		headers = new LinkedHashMap<>();
		headers.put("Host", host);
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
	 * returns header with specified name
	 * @param name - name of header to return ie. "Cookie", "User-Agent"
	 */
	public String getHeader(String name) {
		return headers.get(name);
	}
	
	/**
	 * sets request body text
	 * @param body - text to set as request body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * returns request body text
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * sets path
	 * @param path - filepath of resource
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * returns path string
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * sets port number to request on
	 * @param port - int port number
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * returns port number (int)
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * sets method of request
	 * @param method - http request method to use ie. get, post, head
	 */
	public void setMethod(HttpRequestMethod method) {
		this.method = method;
	}
	
	/**
	 * returns http request method
	 */
	public HttpRequestMethod getMethod() {
		return method;
	}
	
	/**
	 * sets host
	 * @param host - string dns hostname
	 */
	public void setHost(String host) {
		this.host = host;
		headers.put("Host", host);
	}
	
	/**
	 * returns host string
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * Overridden toString method - creates http request string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(method.toString().toUpperCase()+" "+path+" HTTP/1.1\r\n");
		
		for (var key: headers.keySet()) {
			sb.append(key+": "+headers.get(key)+"\r\n");
		}
		
		sb.append("\r\n");
	
		sb.append(body);

		return sb.toString();
	}
	
	/*
	public static void main(String[] args) {
		HttpRequestBuilder request = new HttpRequestBuilder(HttpRequestMethod.get, "www.siliconmtn.com","/about");
		request.setHeader("Connection", "keep-alive");
		request.setBody("username and password to log in.");
		System.out.println(request.toString());
	}
	*/
}
