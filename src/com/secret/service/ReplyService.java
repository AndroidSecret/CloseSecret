package com.secret.service;

import java.util.List;

import com.secret.model.Reply;

public interface ReplyService {	//reply实体类的业务接口

	//增加一条匿名评论
	public boolean addCommment(Reply reply);
	
	//删除一条匿名评论
	public boolean removeComment(Reply reply);
	
	//查询消息的评论
	public List<Reply> queryComment(short msgId);
	
}
