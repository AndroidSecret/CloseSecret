package com.secret.service;

import java.util.List;

import com.secret.model.Contact;
import com.secret.model.Message;
import com.secret.model.Reply;
import com.secret.model.User;

//Action是一个控制器 
//Dao主要做数据库的交互工作
//Modle 是模型 存放你的实体类
//service 做相应的业务逻辑处理  
public interface UserService {	//user实体类的业务接口
	
	//登录
	public boolean login(String phone_md5,String code);
	public boolean login(String token);
	
	//添加用户
	public boolean addUser(String phone_md5);
	public boolean addUser(String phone_md5,String phone);//插入手机号
	
	//退出
	public void logout();
	
	//返回当前用户对象
	public User getUser(String phone_md5);
	public User getUser(short userId);
	public User getUserByPhone(String phone);
	
	//发布消息，返回消息msgID
	public boolean publishMsg(Message msg);

	//获取消息列表
	public List<Message> getTimeline();
	
	//获取评论列表
	public List<Reply> getComment(String msgId);
	
	//发表评论
	public boolean publishComment(short msgId,String content,String phone_md5);

	//上传联系人
	public boolean uploadContact(List<Contact> contacts);
	
	//发送验证码
	public boolean sendCode(String phone);

	//更新token
	public boolean updateToken(String token,String phone_md5);
	
}
