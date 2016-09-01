package com.quikr.parser;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dev.parser.JobListingParser;

public class QuikrParser {
	public static void main(String args[]) throws IOException
	{
		String quikrURL="http://www.quikr.com/jobs/";
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		Document doc =  Jsoup.connect(quikrURL).userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
		 //get all cities offered by the company
		Elements roles = doc.select(".js_all_jobs");
		for (Element element : roles) {
			String role = element.text().trim();
			String url = element.attr("href");
			Runnable parser = new QuikrJobRoleList(role,url ,executor);
			executor.execute(parser);
			break;
		}
		
	}

}
