package com.quikr.parser;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseError;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector.SelectorParseException;

public class QuikrJobRoleList implements Runnable{
	String role;
	String url;
	Integer pageCount=1;
	ExecutorService executor;
	public QuikrJobRoleList(String role,String url, ExecutorService executor) {
		this.role=role;
		this.url=url;
		this.executor=executor;
	}
	

	@Override
	public void run() {
		while(checkForEmptyPage(url,pageCount))
		{
			try {
				Document doc =  Jsoup.connect(url+"?page="+pageCount+"&nextOffset=0&aj=1").userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
				Elements jobTitels =	doc.select(".job-title");
				for (Element element : jobTitels) {
					String url = element.attr("href");
					System.out.println(url);
					Runnable parser = new JobDescriptionParser(url,role);
					executor.execute(parser);
					pageCount++;
				}
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		
	}


	private boolean checkForEmptyPage(String url2, Integer pageCount2) {
		Boolean flag = Boolean.TRUE;
		try {
		    String uurl = url2+"?page="+pageCount2+"&nextOffset=0&aj=1";
			Document doc =  Jsoup.connect(url2+"?page="+pageCount2+"&nextOffset=0&aj=1").userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
			Element error = doc.select(".error=content").first();
			if(error!=null)
			{
				flag = Boolean.FALSE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (SelectorParseException e) {
			
		}
		

		
		return flag;
	}

}
