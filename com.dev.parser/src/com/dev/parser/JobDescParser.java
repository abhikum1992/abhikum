package com.dev.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.avaje.ebean.Ebean;
import com.dev.bean.JobRequirementBean;
import com.dev.bean.LanguageBean;
import com.dev.bean.LocationBean;
import com.dev.bean.RoleBean;
import com.dev.bean.JobRequirementBean.Gender;

public class JobDescParser implements Runnable {
	
	String siteURL;
	String jobURL;
	String jobRole;
	String cityName;
	
	BabaJobEntity bje;
	public JobDescParser(String siteName, String linkName, String jobRole, String cityName) {
		this.siteURL = siteName;
		this.jobURL = linkName;
		this.jobRole = jobRole;
		this.cityName = cityName;
		
	}
	@Override
	public void run() {
		try {
			//Set<String> requiements = new HashSet<String>();
			//Map<String,String> label_text = new HashMap<String, String>();
			//System.out.println("test"+siteURL+jobURL);
			Document jobListingHTML = Jsoup.connect(siteURL+jobURL).userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
			Element jobDetails = jobListingHTML.select(".page-content").first();
			String jobTitelElement = jobDetails.select(".job-title").first().select("h1").first().text();
			
			JobRequirementBean jrb = new JobRequirementBean();
			
			jrb.setJobTitle(jobTitelElement);
			//jrb.setJobTitle(jobTitelElement);
			Elements elements  = jobDetails.select(".job-detail-row");
			for (Element element : elements) {
				String type =element.select(".job-label-text").first().text();
				System.out.println("type of element="+type);
			switch (type) {
			case "Employer": 
				String employer = element.select(".job-info-text").first().text();
				jrb.setEmployer(employer);
				System.out.println(employer);
				break;
			case "Salary":
				Scanner in = new Scanner(element.select(".job-info-text").first().text()).useDelimiter("[^0-9]+");
				Integer integer = in.nextInt();
				jrb.setMinSalary(integer);
				System.out.println(integer);
				break;
			
			case "Gender":
				String gender =  element.select(".job-info-text").first().text();
				if(gender.trim().equalsIgnoreCase("Male")){
					Gender genderEnum = Gender.Male;
					jrb.setGender(genderEnum);
				}else if(gender.trim().equalsIgnoreCase("Female")){
					Gender genderEnum = Gender.Female;
					jrb.setGender(genderEnum);
				}
				System.out.println("gender set");
				break;
			case "Languages":
				String genString = element.select(".job-info-text").first().text();
				List<LanguageBean> lbList =  new ArrayList<LanguageBean>();
				if(genString.contains(","))
				{
					String[] str = genString.split(",");
					for (String string : str) {
					System.out.println("="+string);
					LanguageBean lang =	persistLanguage(string.trim());
					lbList.add(lang);
					}
					jrb.setLanguages(lbList);
				}else{
					LanguageBean lang = persistLanguage(genString);
					lbList.add(lang);
					jrb.setLanguages(lbList);
				}
				System.out.println("lang"+lbList.toString());
				break;
			case "Experience":
				String exp = element.select(".job-info-text").first().text();
				jrb.setExp(exp);
				System.out.println(exp);
				break;
			case "Description":
				String desc = element.select(".job-info-text").first().text();
				jrb.setJobDescription(desc);
				System.out.println(desc);
				break;

			default:
				break;
			}
				
				//label_text.put(element.select(".job-label-text").first().text(), element.select(".job-info-text").first().text());
				//System.out.println(element.select(".job-label-text").first().text()+"="+ element.select(".job-info-text").first().text());
			}
			try{
			Ebean.beginTransaction();
			RoleBean rb = Ebean.find(RoleBean.class).where().eq("role", jobRole).findUnique();
			LocationBean lb = Ebean.find(LocationBean.class).where().eq("Location", cityName).findUnique();
			jrb.setLocation(lb);
			jrb.setRole(rb);
			Ebean.save(jrb);
			System.out.println("saved");
			Ebean.commitTransaction();
			}catch(PersistenceException p)
			{
				p.printStackTrace();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
		


	}
	private static synchronized LanguageBean persistLanguage(String string) {
		LanguageBean lb = new LanguageBean();
		lb = Ebean.find(LanguageBean.class).where().eq("language", string).findUnique();
		if(lb==null)
		{
			lb= new LanguageBean();
			lb.setLanguage(string);
			Ebean.save(lb);
			lb = Ebean.find(LanguageBean.class).where().eq("language", string).findUnique();
		}
		return lb;
	}

}
