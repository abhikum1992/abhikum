package com.dev.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="j_skill")
public class SkillBean {
@Id
@Column(name="skill_id")
@GeneratedValue(strategy=GenerationType.IDENTITY)

Integer skillId;
@Column(name="skill_name",unique=true)
String skill;
@ManyToMany(mappedBy="skills",fetch=FetchType.EAGER)
List<JobRequirementBean> jobs;

public Integer getSkillId() {
	return skillId;
}
public void setSkillId(Integer skillId) {
	this.skillId = skillId;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
public List<JobRequirementBean> getJobs() {
	return jobs;
}
public void setJobs(List<JobRequirementBean> jobs) {
	this.jobs = jobs;
}
}
