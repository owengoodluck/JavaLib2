package com.amazonaws.mws.entity.yanwen.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
	@XmlElement(name = "Userid")
	private String userid;
	@XmlElement(name = "Operation")
	private String operation;
	@XmlElement(name = "Success")
	private String success;
	@XmlElement(name = "Reason")
	private String reason;
	@XmlElement(name = "ReasonMessage")
	private String reasonMessage;
	@XmlElement(name = "Epcode")
	private String epcode;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReasonMessage() {
		return reasonMessage;
	}
	public void setReasonMessage(String reasonMessage) {
		this.reasonMessage = reasonMessage;
	}
	public String getEpcode() {
		return epcode;
	}
	public void setEpcode(String epcode) {
		this.epcode = epcode;
	}
}
