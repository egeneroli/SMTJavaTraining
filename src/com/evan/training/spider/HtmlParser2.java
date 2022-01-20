/**
 * 
 */
package com.evan.training.spider;

import java.util.LinkedHashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class HtmlParser2 {
	
	public LinkedHashSet<String> extractLinks(String html){
		Document doc = Jsoup.parse(html);
		//System.out.print(doc);
		
		// Extract links from homepage html, store in set
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
}