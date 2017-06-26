package com.secret.serviceImpl;

import java.util.List;

import com.secret.dao.ContactDao;
import com.secret.dao.MessageDao;
import com.secret.dao.ReplyDao;
import com.secret.dao.UserDao;
import com.secret.model.Contact;
import com.secret.model.Message;
import com.secret.model.Reply;
import com.secret.model.User;
import com.secret.service.UserService;

public class UserServiceImpl implements UserService{	//user类的业务接口的具体实现类

	private static UserDao userDao = null;
	private static MessageDao msgDao = null;
	private static ReplyDao repDao = null;
	private static ContactDao contDao = null;
	private static UserServiceImpl usi = null;

	public static UserServiceImpl  getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		if (msgDao == null) {
			msgDao = new MessageDao();
		}
		if (repDao == null) {
			repDao = new ReplyDao();
		}
		if (contDao == null) {
			contDao = new ContactDao();
		}
		if (usi == null) {
			usi = new UserServiceImpl();
		}
		return usi;
	}
	
	@Override
	public boolean login(String phone_md5, String code) {
		boolean result = false;
		if(userDao.queryUser(phone_md5,code) != null){
			result = true;
		}
		return result;
		
	}

	@Override
	public boolean login(String token) {
		boolean result = false;
		if(userDao.queryUserByToken(token) != null){
			result = true;
		}
		return result;
	}

	@Override
	public User getUser(String phone_md5) {
		User user = userDao.queryUserByPhoneMD5(phone_md5);
		return user;
	}
	
	@Override
	public User getUser(short userId) {
		User user = userDao.queryUser(userId);
		return null;
	}

	@Override
	public List<Message> getTimeline() {
		List<Message> msgList = msgDao.queryAllMessage();
		return msgList;
	}

	@Override
	public List<Reply> getComment(String msgId) {
		List<Reply> repList = repDao.queryAllReply();
		return repList;
	}

	@Override
	public boolean publishComment(short msgId,String content,String phone_md5) {
		boolean result = false;
		User user = userDao.queryUserByPhoneMD5(phone_md5);
		Message msg = msgDao.queryMessage(msgId);
		if( user != null && msg != null){
			Reply rep = new Reply(user.getUserId(), msgId, content);
			if(repDao.addReply(rep)){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean uploadContact(List<Contact> contacts) {
		boolean result = contDao.addContact(contacts);
		return result;
	}

	@Override
	public boolean sendCode(String phone) {
		boolean result = false;
		User us = userDao.queryUserByPhone(phone);
		if(us != null){
			String code = "1234";
			userDao.updateUser(code, us.getUserId());
			//发送验证码。。。
			
			result = true;
		}
		return result;
	}

	@Override
	public void logout() {
		
	}

	@Override
	public boolean publishMsg(Message msg) {
		boolean result = msgDao.addMessage(msg);//返回添加的消息ID
		return result;
	}

	@Override
	public boolean updateToken(String token,String phone_md5) {
		boolean result = userDao.updateUserToken(token, phone_md5);
		return result;
	}

	@Override
	public boolean addUser(String phone_md5) {
		boolean result = false;
		User user = new User();
		user.setPhone_md5(phone_md5);
		user.setCode("1111");
		result = userDao.addUser(user);
		System.out.println("result :"+result);
		return result;
		
	}

	@Override
	public boolean addUser(String phone_md5, String phone) {
		boolean result = false;
		User user = new User();
		user.setPhone_md5(phone_md5);
		user.setPhone(phone);
		user.setCode("1111");
		result = userDao.addUser(user);
		System.out.println("result:"+result);
		return result;
	}

	@Override
	public User getUserByPhone(String phone) {
		User user = userDao.queryUserByPhone(phone);
		return user;
	}
	
}
