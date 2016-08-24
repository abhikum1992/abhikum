package com.dev.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="j_role")
public class RoleBean {
@Id
@Column(name="role_id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
Integer roleId;
@Column(name="role_name",unique=true)
String role;

@OneToOne( mappedBy = "role", fetch = FetchType.LAZY)
JobRequirementBean jobRequirement;

public JobRequirementBean getJobRequirement() {
	return jobRequirement;
}
public void setJobRequirement(JobRequirementBean jobRequirement) {
	this.jobRequirement = jobRequirement;
}
public Integer getRoleId() {
	return roleId;
}
public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
}
