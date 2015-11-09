package com.amazonaws.mws.entity.yanwen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ExpressType")
public class ExpressType {

	@XmlElement(name = "Epcode")
	private String epcode;
	@XmlElement(name = "Userid")
	private String userid;
	@XmlElement(name = "Channel")
	private String channel;
	@XmlElement(name = "UserOrderNumber")
	private String userOrderNumber;
	@XmlElement(name = "SendDate")
	private String sendDate;
	@XmlElement(name = "Memo")
	private String memo;
	@XmlElement(name = "Quantity")
	private String quantity;
	@XmlElement(name = "Receiver")
	private Receiver receiver;
	@XmlElement(name = "GoodsName")
	private GoodsName goodsName;
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
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	public GoodsName getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(GoodsName goodsName) {
		this.goodsName = goodsName;
	}
}
