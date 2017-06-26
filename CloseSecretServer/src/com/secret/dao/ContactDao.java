package com.secret.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secret.model.Contact;
import com.secret.util.DBUtil;

public class ContactDao {	//contact表数据访问对象
	//增加一条联系人数据
	public boolean addContact(Contact contact){
		boolean result = false;
		Contact cont = contact;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "insert into Contact(userId,contact_phone_md5,user_phone_md5) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setShort(1, cont.getUserId());
			ps.setString(2, cont.getContact_phone_md5());
			ps.setString(3, cont.getUser_phone_md5());
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

	//增加多条联系人数据
	public boolean addContact(List<Contact> contacts){
		boolean result = false;
		for(int i = 0 ;i < contacts.size(); i ++ ){
			Contact cont = contacts.get(i);
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = null;
			int rs = 0;
			String sql = "insert into Contact(userId,contact_phone_md5,user_phone_md5) values(?,?,?)";
			try {
				ps = conn.prepareStatement(sql);
				//设置占位符对应的值
				ps.setShort(1, cont.getUserId());
				ps.setString(2, cont.getContact_phone_md5());
				ps.setString(3, cont.getUser_phone_md5());
				rs = ps.executeUpdate();
				if(rs > 0){
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(ps, conn);	
			}
		}
		return result;
	}

	//修改某条联系人信息
	public boolean updateContact(Contact contact){
		boolean result = false;
		Contact cont = contact;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update Contact set userId=?,contact_phone_md5=? where user_phone_md5=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setShort(1, cont.getUserId());
			ps.setString(2, cont.getContact_phone_md5());
			ps.setString(3, cont.getUser_phone_md5());
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

	//查询所有联系人数据
	public List<Contact> queryAllContact(){
		List<Contact> list = new ArrayList<Contact>();
		Contact cont = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,contact_phone_md5,user_phone_md5 from Contact";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				cont = new Contact();
				cont.setUserId(rs.getShort(1));
				cont.setContact_phone_md5(rs.getString(2));
				cont.setUser_phone_md5(rs.getString(3));
				list.add(cont);
				System.out.println(cont.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return list;
	}

	//根据当前用户加密手机号查询一条联系人数据
	public Contact queryContact(String user_phone_md5){
		Contact cont = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,contact_phone_md5,user_phone_md5 from Contact where user_phone_md5=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, user_phone_md5);
			rs = ps.executeQuery();
			while(rs.next()){
				cont = new Contact();
				cont.setUserId(rs.getShort(1));
				cont.setContact_phone_md5(rs.getString(2));
				cont.setUser_phone_md5(rs.getString(3));
				System.out.println(cont.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return cont;
	}

	//根据当前用户加密手机号删除一条联系人数据
	public boolean deleteContact(String user_phone_md5){
		boolean result = false;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from Contact where user_phone_md5=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_phone_md5);
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

	//根据当前用户ID删除所有联系人数据
	public boolean deleteContacts(short userId){
		boolean result = false;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from Contact where userId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setShort(1, userId);
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
