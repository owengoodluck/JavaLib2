package com.amazonaws.mws.entity.yanwen.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsNameResp {
	@XmlElement(name = "Userid")
	private String userid;
	
	@XmlElement(name = "NameCh")
	private String nameCh;
	
	@XmlElement(name = "NameEn")
	private String nameEn;
	
	@XmlElement(name = "Weight")
	private int weight;
	
	@XmlElement(name = "DeclaredValue")
	private double declaredValue;
	
	@XmlElement(name = "DeclaredCurrency")
	private String declaredCurrency;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	
	
	
}
