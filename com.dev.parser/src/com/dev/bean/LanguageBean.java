package com.dev.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="j_language")
public class LanguageBean {
	@Id
	@Column(name="language_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer languageId;
	@Column(name="language_name",unique=true)
	String language;
	@ManyToMany(mappedBy="languages",fetch=FetchType.EAGER)
	List<JobRequirementBean> jobs;
	
	public List<JobRequirementBean> getJobs() {
		return jobs;
	}
	public void setJobs(List<JobRequirementBean> jobs) {
		this.jobs = jobs;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
