package com.amazonaws.mws.entity.yanwen.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CreateExpressResponseType")
public class CreateExpressResponseType {
	@XmlElement(name = "CallSuccess")
	private boolean callSuccess;
	
	@XmlElement(name = "CreatedExpress")
	private CreatedExpress createdExpress;
	
	@XmlElement(name = "Response")
	private Response resp;

	public CreatedExpress getCreatedExpress() {
		return createdExpress;
	}

	public void setCreatedExpress(CreatedExpress createdExpress) {
		this.createdExpress = createdExpress;
	}

	public Response getResp() {
		return resp;
	}

	public void setResp(Response resp) {
		this.resp = resp;
	}

	public boolean isCallSuccess() {
		return callSuccess;
	}

	public void setCallSuccess(boolean callSuccess) {
		this.callSuccess = callSuccess;
	}
	
}
