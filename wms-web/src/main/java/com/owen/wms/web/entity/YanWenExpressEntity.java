package com.owen.wms.web.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="YanWen_Express")
public class YanWenExpressEntity {

	@Id
	private String epcode;
	private String userid;
	private String channel;
	private String userOrderNumber;
	private Date sendDate;
	private String memo;
	private int quantity;

	//private GoodsName goodsName;
	private String nameCh;
	private String nameEn;
	private int weight;
	private double declaredValue;
	private String declaredCurrency;
	
	//private Receiver receiver;
	private String name;
	private String phone;
	private String mobile;
	private String email;
	private String company;
	private String country;
	private String postcode;
	private String state;
	private String city;
	private String address1;
	private String address2;
	
	public String getEpcode() {
		return epcode;
	}
	public void setEpcode(String epcode) {
		this.epcode = epcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUserOrderNumber() {
		return userOrderNumber;
	}
	public void setUserOrderNumber(String userOrderNumber) {
		this.userOrderNumber = userOrderNumber;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getNameCh() {
		return nameCh;
	}
	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public double getDeclaredValue() {
		return declaredValue;
	}
	public void setDeclaredValue(double declaredValue) {
		this.declaredValue = declaredValue;
	}
	public String getDeclaredCurrency() {
		return declaredCurrency;
	}
	public void setDeclaredCurrency(String declaredCurrency) {
		this.declaredCurrency = declaredCurrency;
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
