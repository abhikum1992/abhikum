package com.quikr.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobDescriptionParser implements Runnable {
	static ArrayList<String> desc = new ArrayList<String>();
	String url;
	String role;
	public JobDescriptionParser(String url, String role) {
	this.url=url;
	this.role=role;
	}
	@Override
	public void run() {
		Document doc;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
			Element roles = doc.select(".table-responsive").first();
			Elements tables = roles.select("tr");
			for (Element element : tables) {
				synchronized (element) {
					desc.add(element.text());
					System.out.println(desc.toString());
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //get all cities offered by the company
		
	}

}
