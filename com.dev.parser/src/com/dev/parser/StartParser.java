package com.dev.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.avaje.ebean.Ebean;
import com.dev.bean.LocationBean;
import com.dev.bean.RoleBean;


public class StartParser {
	public static void main(String args[]) throws IOException{
		List<String> cityNames = new ArrayList<String>();
		List<String> jobType = new ArrayList<String>();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		String babaJobURL="http://www.babajob.com/";
		Document doc = Jsoup.connect(babaJobURL).userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
		 //get all cities offered by the company
		 Elements city = doc.select("#cities-dropdown");
		 cityNames = Arrays.asList(city.text().split(" "));
		 //get all the job types
		 Elements genericJobType = doc.select("#JobCategoriesGeneric");	
		 jobType = Arrays.asList(genericJobType.text().split(" "));
		for (String cityName : cityNames) {
			saveLocation(cityName.trim());
			for (String jobRole : jobType) {
				saveJobRole(jobRole.trim());
				String jobUrl = "Jobs-"+jobRole.trim()+"-in-"+cityName.trim();
				Runnable parser = new JobListingParser(babaJobURL, jobUrl,jobRole.trim(),cityName.trim(),executor);
				executor.execute(parser);
			}
		}
		 while (!executor.isTerminated()) {

		}
		 executor.shutdown();
	}

	private static synchronized void saveJobRole(String jobRole) {
		RoleBean rb = new RoleBean();
		rb.setRole(jobRole);
		Ebean.beginTransaction();
		Ebean.save(rb);
		Ebean.commitTransaction();
	}

	private static synchronized void saveLocation(String cityName) {
		LocationBean lb = new LocationBean();
		lb.setLocation(cityName);
		Ebean.beginTransaction();
		Ebean.save(lb);
		Ebean.commitTransaction();
	}
}
