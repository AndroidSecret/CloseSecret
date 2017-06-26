package com.secret.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secret.model.Reply;
import com.secret.util.DBUtil;

//数据访问接口
public class ReplyDao {	//reply表数据访问对象
	//增加一条评论数据
	public boolean addReply(Reply reply){
		boolean result = false;
		Reply re = reply;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "insert into Reply(userId,msgId,replyContent,createdAt) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setShort(1, re.getUserId());
			ps.setShort(2, re.getMsgId());
			ps.setString(3,  re.getReplyContent());
			ps.setString(4,  re.getCreatedAt());
			rs = ps.executeUpdate();
			if(rs > 0){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps, conn);	
		}
		return result;
	}

	//修改某个评论信息
	public boolean updateReply(Reply reply){
		boolean result = false;
		Reply re = reply;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update Reply set replyContent=?,createdAt=? where userId=? and msgId=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, re.getReplyContent());
			ps.setString(2, re.getCreatedAt());
			ps.setShort(3, re.getUserId());
			ps.setShort(4, re.getMsgId());
			rs = ps.executeUpdate();
			if(rs > 0){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps, conn);
		}
		return result;
	}
	
	//查询所有评论数据
	public List<Reply> queryAllReply(){
		List<Reply> list = new ArrayList<Reply>();
		Reply re = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,msgId,replyContent,createdAt from Reply";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				re = new Reply();
				re.setUserId(rs.getShort(1));
				re.setMsgId(rs.getShort(2));
				re.setReplyContent(rs.getString(3));
				re.setCreatedAt(rs.getString(4));
				list.add(re);
				System.out.println(re.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return list;
	}
	
	//根据某条消息的所有评论数据
	public List<Reply> queryReplyByMsgId(short msgId){
		List<Reply> repList = new ArrayList<Reply>();
		Reply re = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,msgId,replyContent,createdAt from Reply where msgId=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setShort(1, msgId);
			rs = ps.executeQuery();
			while(rs.next()){
				re = new Reply();
				re.setUserId(rs.getShort(1));
				re.setMsgId(rs.getShort(2));
				re.setReplyContent(rs.getString(3));
				re.setCreatedAt(rs.getString(4));
				repList.add(re);
				System.out.println(re.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return repList;
	}
	
	//根据评论ID删除一条评论数据
	public boolean deleteReply(short userId,short msgId){
		boolean result = false;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from Reply where userId=? and msgId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setShort(1, userId);
			ps.setShort(2, msgId);
			rs = ps.executeUpdate();
			if(rs > 0){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps, conn);	
		}
		return result;
	}
}
