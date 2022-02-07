/**
 * 
 */
package com.evan.training.spider;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/****************************************************************************
* <b>Title</b>: HtmlParser.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.spider
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Jan 11, 2022
* @updates:
****************************************************************************/
public class HtmlParser {
	
	/**
	 * extracts all urls from webpage html
	 * @param html String of html
	 * @return HashSet of links present in html
	 */
	public HashSet<String> extractLinks(String html){
		// parse w/ JSoup
		Document doc = Jsoup.parse(html);
		
		// Extract links from html, store in set
		HashSet<String> linkSet = new HashSet<>();
		Elements links = doc.select("a");
		for (var e: links) {
			String link = e.attr("href");
			if (link.startsWith("/") && !link.equals("/")) {
				linkSet.add(link);
			}
		}
		
		return linkSet;
	}
	
	/**
	 * extracts all urls from webpage html
	 * @param html String of html
	 * @return LinkedHashSet of links present in html
	 */	
	/*
	public LinkedHashSet<String> extractLinks(String html){
		Document doc = Jsoup.parse(html);
		//System.out.print(doc);
		
		// Extract links from html, store in ordered set
		LinkedHashSet<String> linkSet = new LinkedHashSet<>();
		Elements links = doc.select("a");
		for (var e: links) {
			var link = e.attr("href");
			if (link.startsWith("/") && !link.equals("/")) {
				linkSet.add(link);
				//System.out.println(link);
			}
		}
		
		return linkSet;
	}
	*/
	
}
