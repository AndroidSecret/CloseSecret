package com.secret.service;

import java.util.List;

import com.secret.dao.MessageDao;
import com.secret.model.Message;
  
public interface MessageService {	//message实体类的业务接口
	
	//增加一条匿名消息
	public boolean addTopic(Message msg);
	
	//删除一条匿名消息
	public boolean removeTopic(Message msg);
	
	//返回消息列表
	public List<Message> getTimeline(String phone_md5);
	
	//获取当前用户的消息列表
	public List<Message> getMyMessage(String phone_md5);
	
}
