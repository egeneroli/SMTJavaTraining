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
public class ConnectionManager {
	enum HttpRequestMethod{get, post, head};
	
	/**
	 * sends https request to specified host and port
	 * @param host - String domain name of requested server
	 * @param port - int port number to connect to
	 * @param requestMethod -- type of request to send: get, post, head
	 * @return html String
	 */
	public String httpsRequest(String host, int port, HttpRequestMethod requestMethod) {
		
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

            out.print(requestMethod.toString().toUpperCase()+" / HTTP/1.1\r\n");
            out.print("Host: "+host+"\r\n");
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
            socket.close();

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
	public String httpsRequest(String host, int port, HttpRequestMethod requestMethod, ArrayList<String> cookieList, String resourcePath) {
		
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

            out.print(requestMethod.toString().toUpperCase()+" /"+resourcePath+" HTTP/1.1\r\n");
            out.print("Host: "+host+"\r\n");
            for (String cookie: cookieList) {
            	out.print(cookie+"\r\n");
            }
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
            socket.close();

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
				cookieList.add(line.split("[-;]")[1]);
			}
		}

		return cookieList;
	}

}
