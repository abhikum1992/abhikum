package com.dev.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity
@Table(name="j_job_requirement")
public class JobRequirementBean {

	public enum Gender {
		Male,
		Female
		
	}
	@Id
	@Column(name="job_requirement_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer jobRequirementId;
	
	@Column(name="job_requirement_employer")
	public String employer;
	
	@Column(name="job_requirement_exp")
	String exp;
	
	@Column(name="job_requirement_title")
	String jobTitle;
	
	@Column(name="job_requirement_desciption")
	public String jobDescription;
	
	@Enumerated(value=EnumType.STRING)
	@Column(name="j_gender")
	public Gender gender;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="language_detail",
            joinColumns=@JoinColumn(name="job_requirement_id", referencedColumnName="job_requirement_id", insertable = false, updatable = false),
      inverseJoinColumns=
            @JoinColumn(name="language_id", referencedColumnName="language_id", insertable = false, updatable = false)
    )
	List<LanguageBean> languages;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@NotNull
	@JoinColumn(name="job_requirement_id", insertable = false, updatable = false,nullable=true)
	LocationBean location;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@NotNull
	@JoinColumn(name="job_requirement_id", insertable = false, updatable = false,nullable=true)
	RoleBean role;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="skill_detail",
            joinColumns=@JoinColumn(name="job_requirement_id", referencedColumnName="job_requirement_id", insertable = false, updatable = false),
      inverseJoinColumns=
            @JoinColumn(name="skill_id", referencedColumnName="skill_id", insertable = false, updatable = false)
    )
	List<SkillBean> skills;
	
	@Column(name="job_requirement_minSalary")
	Integer minSalary;
	
	@Column(name="job_requirement_maxSalary")
	Integer maxSalary;
	
	public Integer getJobRequirementId() {
		return jobRequirementId;
	}
	public void setJobRequirementId(Integer jobRequirementId) {
		this.jobRequirementId = jobRequirementId;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<LanguageBean> getLanguages() {
		return languages;
	}
	public void setLanguages(List<LanguageBean> languages) {
		this.languages = languages;
	}
	public Integer getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}
	public Integer getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}
	public LocationBean getLocation() {
		return location;
	}
	public void setLocation(LocationBean location) {
		this.location = location;
	}
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	public List<SkillBean> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillBean> skills) {
		this.skills = skills;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
}
