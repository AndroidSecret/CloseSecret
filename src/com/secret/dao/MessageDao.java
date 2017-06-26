package com.secret.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secret.model.Message;
import com.secret.util.DBUtil;

//数据访问接口
public class MessageDao {	//message表数据访问对象
	//增加一条消息数据
	public boolean addMessage(Message message){
		boolean result = false;
		Message msg = message;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "insert into Message(msg,phone_md5,createdAt,updatedAt,isDeleted) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, msg.getMsg());
			ps.setString(2, msg.getPhone_md5());
			ps.setString(3,  msg.getCreatedAt());
			ps.setString(4,  msg.getUpdatedAt());
			ps.setBoolean(5, msg.isDeleted());
			rs = ps.executeUpdate();
			System.out.println("rs:"+rs);
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

	//修改某个消息信息
	public boolean updateMessage(Message message){
		boolean result = false;
		Message msg = message;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update Message set msg=?,createdAt=?,updatedAt=?,isDeleted=?,phone_md5=? where msgId=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, msg.getMsg());
			ps.setString(2, msg.getCreatedAt());
			ps.setString(3, msg.getUpdatedAt());
			ps.setBoolean(4, msg.isDeleted());
			ps.setString(5, msg.getPhone_md5());
			ps.setShort(6, msg.getMsgId());
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
	
	//查询所有消息数据
	public List<Message> queryAllMessage(){
		List<Message> list = new ArrayList<Message>();
		Message msg = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select msgId,msg,phone_md5,createdAt,updatedAt from Message where ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				msg = new Message();
				msg.setMsgId(rs.getShort(1));
				msg.setMsg(rs.getString(2));
				msg.setPhone_md5(rs.getString(3));
				msg.setCreatedAt(rs.getString(4));
				msg.setUpdatedAt(rs.getString(5));
				list.add(msg);
				System.out.println(msg.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return list;
	}
	//查询所有消息数据
	public List<Message> queryAllMessage(String phone_md5){
		List<Message> list = new ArrayList<Message>();
		Message msg = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select msgId,msg,phone_md5,createdAt,updatedAt from Message where phone_md5 in(select contact_phone_md5 from Contact where user_phone_md5=? UNION select user_phone_md5 from Contact where user_phone_md5=?)";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, phone_md5);
			ps.setString(2, phone_md5);
			rs = ps.executeQuery();
			while(rs.next()){
				msg = new Message();
				msg.setMsgId(rs.getShort(1));
				msg.setMsg(rs.getString(2));
				msg.setPhone_md5(rs.getString(3));
				msg.setCreatedAt(rs.getString(4));
				msg.setUpdatedAt(rs.getString(5));
				list.add(msg);
				System.out.println(msg.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return list;
	}
	
	//查询当前用户的消息数据
	public List<Message> queryMyMessage(String phone_md5){
		List<Message> list = new ArrayList<Message>();
		Message msg = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select msgId,msg,phone_md5,createdAt,updatedAt from Message where phone_md5=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, phone_md5);
			rs = ps.executeQuery();
			while(rs.next()){
				msg = new Message();
				msg.setMsgId(rs.getShort(1));
				msg.setMsg(rs.getString(2));
				msg.setPhone_md5(rs.getString(3));
				msg.setCreatedAt(rs.getString(4));
				msg.setUpdatedAt(rs.getString(5));
				list.add(msg);
				System.out.println(msg.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return list;
	}
	
	
	//根据消息ID查询一条消息数据
	public Message queryMessage(short msgId){
		Message msg = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select msgId,msg,phone_md5,createdAt,updatedAt from Message where msgId=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setShort(1, msgId);
			rs = ps.executeQuery();
			while(rs.next()){
				msg = new Message();
				msg.setMsgId(rs.getShort(1));
				msg.setMsg(rs.getString(2));
				msg.setPhone_md5(rs.getString(3));
				msg.setCreatedAt(rs.getString(4));
				msg.setUpdatedAt(rs.getString(5));
				System.out.println(msg.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return msg;
	}
	
	//根据消息ID删除一条消息数据
	public boolean deleteMessage(short msgId){
		boolean result = false;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from Message where msgId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setShort(1, msgId);
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
