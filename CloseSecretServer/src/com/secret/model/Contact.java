package com.secret.model;

public class Contact {//联系人实体对象
	private String contact_phone_md5;
	private String user_phone_md5;
	private short userId;

	public String getContact_phone_md5() {
		return contact_phone_md5;
	}
	public void setContact_phone_md5(String contact_phone_md5) {
		this.contact_phone_md5 = contact_phone_md5;
	}
	public String getUser_phone_md5() {
		return user_phone_md5;
	}
	public void setUser_phone_md5(String user_phone_md5) {
		this.user_phone_md5 = user_phone_md5;
	}
	public short getUserId() {
		return userId;
	}
	public void setUserId(short userId) {
		this.userId = userId;
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("userId:").append(this.getUserId())
		.append("\tuser_phone_md5:").append(this.getUser_phone_md5())
		.append("\tphone_md5:").append(this.getContact_phone_md5());
		return str.toString();
	}
}
