package com.secret.serviceImpl;

import java.util.List;

import com.secret.dao.MessageDao;
import com.secret.model.Message;
import com.secret.service.MessageService;

public class MessageServiceImpl implements MessageService{	//message类的业务接口的具体实现类

	private static MessageDao msgDao = null;

	private static MessageServiceImpl msi = null;
	
	public static MessageServiceImpl getInstance() {
		if (msgDao == null) {
			msgDao = new MessageDao();
		}
		if (msi == null) {
			msi = new MessageServiceImpl();
		}
		return msi;
	}
	
	@Override
	public boolean addTopic(Message msg) {
		boolean result = msgDao.addMessage(msg);
		System.out.println("addTopic : "+result);
		return result;
	}

	@Override
	public boolean removeTopic(Message msg) {
		boolean result = msgDao.deleteMessage(msg.getMsgId());
		return result;
	}

	@Override
	public List<Message> getTimeline(String phone_md5) {
		List<Message> msgList = msgDao.queryAllMessage(phone_md5);
		return msgList;
	}
	
	@Override
	public List<Message> getMyMessage(String phone_md5) {
		List<Message> msgList = msgDao.queryMyMessage(phone_md5);
		return msgList;
	}


}
