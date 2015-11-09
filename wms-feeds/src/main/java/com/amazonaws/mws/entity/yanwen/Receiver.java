package com.amazonaws.mws.entity.yanwen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Receiver {

	@XmlElement(name = "Userid")
	private String userid;
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "Phone")
	private String phone;
	@XmlElement(name = "Mobile")
	private String mobile;
	@XmlElement(name = "Email")
	private String email;
	@XmlElement(name = "Company")
	private String company;
	@XmlElement(name = "Country")
	private String country;
	@XmlElement(name = "Postcode")
	private String postcode;
	@XmlElement(name = "State")
	private String state;
	@XmlElement(name = "City")
	private String city;
	@XmlElement(name = "Address1")
	private String address1;
	@XmlElement(name = "Address2")
	private String address2;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	

}
