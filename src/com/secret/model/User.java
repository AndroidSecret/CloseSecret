package com.secret.model;

import java.util.Date;

public class User {//用户实体对象
	private short userId;
	private String phone;
	private String phone_md5;
	private String code;
	private String token;
	
	public short getUserId() {
		return userId;
	}
	public void setUserId(short userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone_md5() {
		return phone_md5;
	}
	public void setPhone_md5(String phone_md5) {
		this.phone_md5 = phone_md5;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("userId:").append(this.getUserId())
		.append("\tphone:").append(this.getPhone())
		.append("\tphone_md5:").append(this.getPhone_md5())
		.append("\tcode:").append(this.getCode())
		.append("\ttoken:").append(this.getToken());
		return str.toString();
	}

}
