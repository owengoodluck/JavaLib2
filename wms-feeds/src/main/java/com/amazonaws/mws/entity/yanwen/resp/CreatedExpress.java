package com.amazonaws.mws.entity.yanwen.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CreatedExpress {
	@XmlElement(name = "Epcode")
	private String epcode;
	
	@XmlElement(name = "Userid")
	private String userid;
	
	@XmlElement(name = "ChannelType")
	private String channelType;
	
	@XmlElement(name = "Channel")
	private String channel;
	
	@XmlElement(name = "Package")
	private String packageStr;
	
	@XmlElement(name = "UserOrderNumber")
	private String userOrderNumber;
	
	@XmlElement(name = "SendDate")
	private String sendDate;
	
	@XmlElement(name = "Receiver")
	private ReceiverResp receiver;//ReceiverResp
	
	@XmlElement(name = "Quantity")
	private String quantity;
	
	@XmlElement(name = "GoodsName")
	private GoodsNameResp goodsName;//GoodsNameResp
	
	@XmlElement(name = "YanwenNumber")
	private String yanwenNumber;
	@XmlElement(name = "ReferenceNo")
	private String referenceNo;
	@XmlElement(name = "PackageNo")
	private String packageNo;
	@XmlElement(name = "Insure")
	private String insure;
	@XmlElement(name = "Memo")
	private String memo;
	@XmlElement(name = "TrackingStatus")
	private String trackingStatus;
	@XmlElement(name = "IsPrint")
	private String isPrint;
	@XmlElement(name = "CreateDate")
	private String createDate;
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
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPackageStr() {
		return packageStr;
	}
	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
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
	public ReceiverResp getReceiver() {
		return receiver;
	}
	public void setReceiver(ReceiverResp receiver) {
		this.receiver = receiver;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public GoodsNameResp getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(GoodsNameResp goodsName) {
		this.goodsName = goodsName;
	}
	public String getYanwenNumber() {
		return yanwenNumber;
	}
	public void setYanwenNumber(String yanwenNumber) {
		this.yanwenNumber = yanwenNumber;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getPackageNo() {
		return packageNo;
	}
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}
	public String getInsure() {
		return insure;
	}
	public void setInsure(String insure) {
		this.insure = insure;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTrackingStatus() {
		return trackingStatus;
	}
	public void setTrackingStatus(String trackingStatus) {
		this.trackingStatus = trackingStatus;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}
