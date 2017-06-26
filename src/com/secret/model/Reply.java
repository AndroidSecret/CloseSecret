package com.secret.model;

import java.util.Date;


import com.secret.util.MyDate;

public class Reply {//匿名评论实体对象
	private short userId;
	private short msgId;
	private String replyContent;
	private String createdAt;
	
	public Reply(){}
	
	public Reply(short userId, short msgId, String replyContent) {
		this.userId = userId;
		this.msgId = msgId;
		this.replyContent = replyContent;
		this.createdAt = MyDate.getDateCN();
	}
	
	public short getUserId() {
		return userId;
	}
	public void setUserId(short userId) {
		this.userId = userId;
	}
	public short getMsgId() {
		return msgId;
	}
	public void setMsgId(short msgId) {
		this.msgId = msgId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("userId:").append(this.getUserId())
		.append("\tmsgId:").append(this.getMsgId())
		.append("\treplyContent:").append(this.getReplyContent())
		.append("\tcreatedAt:").append(this.getCreatedAt());
		return str.toString();
	}
}
