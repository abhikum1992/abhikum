package com.quikr.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestQuikr {
	public static void main(String args[]) throws IOException
	{
		Document doc =  Jsoup.connect("http://www.quikr.com/jobs/accountant+zwqxj1466534506").userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
		 //get all cities offered by the company
		//Element roles = doc.select(".table-responsive").first();
		//Elements tables = roles.select("tr");
		//for (Element element : tables) {
			System.out.println(doc.html());
		//}
		
	}

}
