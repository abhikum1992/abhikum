package com.dev.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="j_location")
public class LocationBean {
@Id
@Column(name="location_id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
Integer locationId;

@Column(name="location_name",unique=true)
String Location;

@OneToOne(optional = false, mappedBy = "location", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
List<JobRequirementBean> job;
public List<JobRequirementBean> getJob() {
	return job;
}
public void setJob(List<JobRequirementBean> job) {
	this.job = job;
}
public Integer getLocationId() {
	return locationId;
}
public void setLocationId(Integer locationId) {
	this.locationId = locationId;
}
public String getLocation() {
	return Location;
}
public void setLocation(String location) {
	Location = location;
}


}
