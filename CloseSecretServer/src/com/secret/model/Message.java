package com.secret.model;

import java.util.Date;


import com.secret.util.MyDate;

public class Message {//匿名消息实体对象
	private short msgId;
	private String msg;
	private String phone_md5;
	private String createdAt;
	private String updatedAt;
	private boolean isDeleted;
	
	public Message(){}
	
	public Message(String msg, String phone_md5) {
		this.msg = msg;
		this.phone_md5 = phone_md5;
		this.createdAt = MyDate.getDateCN();
		this.updatedAt = MyDate.getDateCN();
		this.isDeleted = false;
	}

	public short getMsgId() {
		return msgId;
	}
	public void setMsgId(short msgId) {
		this.msgId = msgId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPhone_md5() {
		return phone_md5;
	}
	public void setPhone_md5(String phone_md5) {
		this.phone_md5 = phone_md5;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("msgId:").append(this.getMsgId())
		.append("\tmsg:").append(this.getMsg())
		.append("\tphone_md5:").append(this.getPhone_md5())
		.append("\tcreatedAt:").append(this.getCreatedAt())
		.append("\tupdatedAt:").append(this.getUpdatedAt())
		.append("\tisDeleted:").append(this.isDeleted());
		return str.toString();
	}
}
