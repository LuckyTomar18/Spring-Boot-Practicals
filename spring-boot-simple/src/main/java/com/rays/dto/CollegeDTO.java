package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_COLLEGE")
public class CollegeDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	private String name;
	
	@Column(name = "STATE", length = 50)
	private String state;
	
	@Column(name = "ADDRESS", length = 50)
	private String address;
	
	@Column(name = "CITY", length = 50)
	private String city;
	
	@Column(name = "PHONE_NO", length = 50)
	private String phoneNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String getValue() {
		return name;
	}

}
