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
public class ConnectionManager2 {
	// member variables
	BufferedReader in;
	PrintWriter out;
	SSLSocket socket;
	String host;
	int port;
	
	
	
	/** Constructor - initiates https connection with host: port number
	 * @param host - string hostname
	 * @param port - int port number
	 */
	public ConnectionManager2(String host, int port) {
		this.host = host;
		this.port = port;
		
		try {
	    	// create socket connection and manually begin handshake
	        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	        socket = (SSLSocket) factory.createSocket(host, port);
	        socket.startHandshake();
	        
	        /*
	        out = new PrintWriter(
                     new BufferedWriter(
                     new OutputStreamWriter(
                     socket.getOutputStream())));

			in = new BufferedReader(
			                       new InputStreamReader(
			                       socket.getInputStream()));
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * tear down method - closes all resources (reader, writer, socket)
	 */
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// enum - defines possible http request types for method arguments
	enum HttpRequestMethod{get, post, head};
	
	/**
	 * sends https request to specified host and port
	 * @param host - String domain name of requested server
	 * @param port - int port number to connect to
	 * @param requestMethod -- type of request to send: get, post, head
	 * @return html String
	 */
	public String httpsRequest(String resourcePath, HttpRequestMethod requestMethod) {
		
		StringBuilder html = new StringBuilder();
        try {
        	/*
        	// create socket connection and manually begin handshake
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.startHandshake();
            */
        	
            // write request to socket connection
            PrintWriter out = new PrintWriter(
                                  new BufferedWriter(
                                  new OutputStreamWriter(
                                  socket.getOutputStream())));
            

            out.print(requestMethod.toString().toUpperCase()+" /"+resourcePath+" HTTP/1.1\r\n");
            out.print("Host: "+this.host+"\r\n");
            out.print("\r\n");
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
            //socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return html.toString();
	}
	
	/**
	 * overloaded - sends https request to specified host and port, passes cookies from cookieList w/ request
	 * @param host - String domain name of requested server
	 * @param port - int port number to connect to
	 * @param requestMethod - type of request to send: get, post, head
	 * @return html String
	 */
	public String httpsRequest(String resourcePath, HttpRequestMethod requestMethod, String headers) {
		
		StringBuilder html = new StringBuilder();
        try {
        	/*
        	// create socket connection and manually begin handshake
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.startHandshake();
            */
        	
            // write request to socket connection
            PrintWriter out = new PrintWriter(
                                  new BufferedWriter(
                                  new OutputStreamWriter(
                                  socket.getOutputStream())));
			
			
            out.print(requestMethod.toString().toUpperCase()+" /"+resourcePath+" HTTP/1.1\r\n");
            out.print("Host: "+host+"\r\n");
            out.print(headers+"\r\n");
            out.print("\r\n");
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
            //socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return html.toString();
	}
	
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

}
