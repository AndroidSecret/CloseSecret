package com.secret.serviceImpl;

import java.util.List;

import com.secret.dao.ReplyDao;
import com.secret.model.Reply;
import com.secret.service.ReplyService;

public class ReplyServiceImpl implements ReplyService{	//reply类的业务接口的具体实现类

	private static ReplyDao repDao = null;

	private static ReplyServiceImpl rsi = null;
	
	public static ReplyServiceImpl getInstance() {
		if (repDao == null) {
			repDao = new ReplyDao();
		}
		if (rsi == null) {
			rsi = new ReplyServiceImpl();
		}
		return rsi;
	}
	
	@Override
	public boolean addCommment(Reply reply) {
		boolean result = repDao.addReply(reply);
		return result;
	}

	@Override
	public boolean removeComment(Reply reply) {
		boolean result = repDao.deleteReply(reply.getUserId(), reply.getMsgId());
		return result;
	}

	@Override
	public List<Reply> queryComment(short msgId) {
		return repDao.queryReplyByMsgId(msgId);
	}

	
}
