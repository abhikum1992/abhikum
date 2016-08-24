package com.dev.parser;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobListingParser implements Runnable {
	String siteName;
	String jobSearchURL;
	ExecutorService executor;
	String jobRole;
	String cityName;
	public JobListingParser(String siteName, String jobSearchURL, String jobRole, String cityName, ExecutorService executor)
	{
		
		this.siteName = siteName;
		this.jobSearchURL = jobSearchURL;
		this.executor = executor;
		this.jobRole=jobRole;
		this.cityName=cityName;
	}
	
		public void run() {
		 try {
			 Boolean flag = Boolean.TRUE;
			 //System.out.println(siteName+jobSearchURL);
			 Document jobListingHTML = Jsoup.connect(siteName+jobSearchURL).userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
			 extractPageJobs(siteName,jobSearchURL,jobListingHTML);
			 while(flag)
			 {
					Element e = jobListingHTML.select(".pagination").first();
					if(">".equalsIgnoreCase(e.getElementsContainingOwnText(">").text())){
						String nextPage = e.getElementsContainingOwnText(">").first().attr("href").replaceFirst("/", "");
						jobListingHTML = Jsoup.connect(siteName+nextPage).userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
						extractPageJobs(siteName,nextPage,jobListingHTML);
					}
					else{
						flag=Boolean.FALSE;
					}
			 }
			 
			
		 } catch (IOException e) {
			//e.printStackTrace();
		}
			
		
		
		}

		private void extractPageJobs(String siteName, String jobSearchURL, Document jobListingHTML) throws IOException {
			Elements listings = jobListingHTML.select(".s-card-title");
			for (Element element : listings) {
				Element childElement = element.select("a").first();
				String linkName = childElement.attr("href").trim();
				Runnable parser = new JobDescParser(siteName,linkName,jobRole,cityName);
				executor.execute(parser);
			}
		}
		

		


}
