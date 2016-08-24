package com.dev.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;









import com.avaje.ebean.Ebean;
import com.avaje.ebeaninternal.server.deploy.BeanCascadeInfo;
import com.dev.bean.JobRequirementBean;
import com.dev.bean.JobRequirementBean.Gender;
import com.dev.bean.LanguageBean;
import com.dev.bean.LocationBean;
import com.dev.bean.RoleBean;


public class Tests {

	public static void main(String[] args) throws IOException {
		//Map<String,String> label_text = new HashMap<String, String>();
		Document jobListingHTML = Jsoup.connect("http://www.babajob.com/Job-Marketingexecutive-For-Yukta_Achievers-in-Bangalore-Near-Nagarbhavi(560072)-2146313603").userAgent("Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)").get();
		Element jobDetails = jobListingHTML.select(".page-content").first();
		String jobTitelElement = jobDetails.select(".job-title").first().select("h1").first().text();
		System.out.println("JobTtitel="+jobTitelElement);
		LocationBean lb = new LocationBean();
		lb.setLocation("Bangalore");
		RoleBean rb = new RoleBean();
		rb.setRole("Sales");
		Ebean.beginTransaction();
		Ebean.save(lb);
		Ebean.save(rb);
		System.out.println("saved beans");
		Ebean.commitTransaction();
		 rb = Ebean.find(RoleBean.class).where().eq("role", "Sales").findUnique();
		 
		 lb = Ebean.find(LocationBean.class).where().eq("Location", "Bangalore").findUnique();
		
		JobRequirementBean jrb = new JobRequirementBean();
		jrb.setLocation(lb);
		jrb.setRole(rb);
		
		jrb.setJobTitle(jobTitelElement);
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
			/*case "Languages":
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
				break;*/
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
				/*String reqType =element.select(".job-label-text").first().text().trim();
				System.out.println(reqType);
				requiements.add(reqType);*/
			
			/*label_text.put(element.select(".job-label-text").first().text(), element.select(".job-info-text").first().text());
			System.out.println(element.select(".job-label-text").first().text()+"="+ element.select(".job-info-text").first().text());
		*/}
		Ebean.beginTransaction();
		Ebean.save(jrb);
		Ebean.commitTransaction();
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
