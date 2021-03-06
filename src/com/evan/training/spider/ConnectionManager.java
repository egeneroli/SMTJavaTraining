/**
 * 
 */
package com.evan.training.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/****************************************************************************
* <b>Title</b>: ConnectionManager.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.spider
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Jan 11, 2022
* @updates:
****************************************************************************/
public class ConnectionManager {
	// create enum for http request methods
	enum HttpRequestMethod{get, post, head};
	
	private String host;
	private int port;
	
	/** constructor - instantiates connection manager class, sets specified host and port number
	 * @param host - host name for request
	 * @param port - port number to request on
	 */
	public ConnectionManager(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * sends https request
	 * @param request HttpRequestVO
	 * @return html String
	 */
	public String httpsRequest(HttpRequestVO request) {
		
		StringBuilder html = new StringBuilder();
        try {
        	// create socket connection and manually begin handshake
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.startHandshake();
            
            // write request to socket connection
            PrintWriter out = new PrintWriter(
                                  new BufferedWriter(
                                  new OutputStreamWriter(
                                  socket.getOutputStream())));
            
            /*
            out.print(requestMethod.toString().toUpperCase()+" "+path+" HTTP/1.1\r\n");
            out.print("Host: "+host+"\r\n");
            out.print("\r\n");
            */
            out.print(request.toString());
            out.flush();

            // make sure there were no surprises
            if (out.checkError())
                System.out.println(
                    "httpsRequest:  java.io.PrintWriter error");

            // read response
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    socket.getInputStream()));

            String inData;
            while ((inData = in.readLine()) != null)
                //System.out.println(inData);
            	html.append(inData + "\n");

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return html.toString();
	}
	
	/**
	 * parses cookies from http headers
	 * @param html - string html w/ http headers
	 * @return ArrayList of "cookiename=cookievalue" (strings)
	 */
	public ArrayList<String> extractCookies(String html) {
		// create ArrayList to store cookies in
		ArrayList<String> cookieList = new ArrayList<>();
		
		// read each line of html, if line begins w/ "Set-Cookie": add cookie to list
		for (String line: html.split("\n")) {
			if (line.startsWith("Set-Cookie")) {
				String[] lst = line.split("[ ;]");
				//for (int i=0; i < lst.length; i++) {
				//	System.out.println(i + ": " + lst[i]);
				//}
				//System.out.println();
				String cookieString = lst[1];
				cookieList.add(cookieString);
				//System.out.println(cookieString);
			}
		}

		return cookieList;
	}
	
	
	/**
	 * parses cookies from http headers
	 * @param html - string html w/ http headers
	 * @return hashmap of "cookiename"="cookievalue"
	 */
	/*
	public HashMap<String,String> extractCookies(String html) {
		// create ArrayList to store cookies in
		HashMap<String,String> cookieMap = new HashMap<>();
		
		// read each line of html, if line begins w/ "Set-Cookie": add cookie to list
		for (String line: html.split("\n")) {
			if (line.startsWith("Set-Cookie")) {
				String[] lst = line.split("[ ;]");
				//for (int i=0; i < lst.length; i++) {
				//	System.out.println(i + ": " + lst[i]);
				//}
				//System.out.println();
				String cookieString = lst[1];
				String[] lst2 = cookieString.split("=");
				String cookieName = lst2[0];
				String cookieValue = lst2[1];
				cookieMap.put(cookieName, cookieValue);
				//cookieList.add(cookieString);
				//System.out.println(cookieString);
			}
		}

		return cookieMap;
	}
	*/
	
	
	/**
	 * sets host
	 * @param host - string dns hostname
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * returns host string
	 */
	public String getHost() {
		return host;
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
}
