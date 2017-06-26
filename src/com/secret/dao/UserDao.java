package com.secret.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secret.model.User;
import com.secret.util.DBUtil;

public class UserDao {	//user表数据访问对象

	//增加一条用户数据
	public boolean addUser(User user){
		boolean result = false;
		User us = user;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "insert into User(phone,phone_md5,code,token) values(?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, us.getPhone());
			ps.setString(2, us.getPhone_md5());
			ps.setString(3, "1111");
			ps.setString(4, us.getToken());
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

	//修改某个用户验证码信息
	public boolean updateUser(String code,short userId){
		boolean result = false;
		if(queryUser(userId) == null){

		}else{
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = null;
			int rs = 0;
			String sql = "update User set code=? where userId=?";
			try {
				ps = conn.prepareStatement(sql);
				//设置占位符对应的值
				ps.setString(1, code);
				ps.setShort(2, userId);
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

	//修改某个用户token信息
		public boolean updateUserToken(String token,String phone_md5){
			boolean result = false;
			if(queryUserByPhoneMD5(phone_md5) == null){

			}else{
				Connection conn = DBUtil.getConnection();
				PreparedStatement ps = null;
				int rs = 0;
				String sql = "update User set token=? where phone_md5=?";
				try {
					ps = conn.prepareStatement(sql);
					//设置占位符对应的值
					ps.setString(1, token);
					ps.setString(2, phone_md5);
					rs = ps.executeUpdate();
					if(rs > 0){
						System.out.println("更新成功");
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
	
	//修改某个用户信息
	public boolean updateUser(User user){
		boolean result = false;
		User us = user;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update User set phone=?,phone_md5=?,code=?,token=? where userId=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, us.getPhone());
			ps.setString(2, us.getPhone_md5());
			ps.setString(3, us.getCode());
			ps.setString(4, us.getToken());
			ps.setShort(5, us.getUserId());
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

	//查询所有用户数据
	public List<User> queryAllUser(){
		List<User> list = new ArrayList<User>();
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,phone,phone_md5,code,token from User";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getShort(1));
				user.setPhone(rs.getString(2));
				user.setPhone_md5(rs.getString(3));
				user.setCode(rs.getString(4));
				user.setToken(rs.getString(5));
				list.add(user);
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return list;
	}

	//根据令牌查询一条用户数据
	public User queryUserByToken(String token){
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,phone,phone_md5,code,token from User where token=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, token);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getShort(1));
				user.setPhone(rs.getString(2));
				user.setPhone_md5(rs.getString(3));
				user.setCode(rs.getString(4));
				user.setToken(rs.getString(5));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return user;
	}

	//根据用户手机号查询一条用户数据
	public User queryUserByPhone(String phone){
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,phone,phone_md5,code,token from User where phone=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, phone);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getShort(1));
				user.setPhone(rs.getString(2));
				user.setPhone_md5(rs.getString(3));
				user.setCode(rs.getString(4));
				user.setToken(rs.getString(5));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return user;
	}

	//根据用户加密手机号查询一条用户数据
		public User queryUserByPhoneMD5(String phone_md5){
			User user = null;
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select userId,phone,phone_md5,code,token from User where phone_md5=?";
			try {
				ps = conn.prepareStatement(sql);
				//设置占位符对应的值
				ps.setString(1, phone_md5);
				rs = ps.executeQuery();
				while(rs.next()){
					user = new User();
					user.setUserId(rs.getShort(1));
					user.setPhone(rs.getString(2));
					user.setPhone_md5(rs.getString(3));
					user.setCode(rs.getString(4));
					user.setToken(rs.getString(5));
					System.out.println(user.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, ps, conn);	
			}
			return user;
		}
	
	//根据用户手机号和验证码查询一条用户数据
	public User queryUser(String phone_md5,String code){
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,phone,phone_md5,code,token from User where phone_md5=? and code=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setString(1, phone_md5);
			ps.setString(2, code);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getShort(1));
				user.setPhone(rs.getString(2));
				user.setPhone_md5(rs.getString(3));
				user.setCode(rs.getString(4));
				user.setToken(rs.getString(5));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return user;
	}

	//根据用户ID查询一条用户数据
	public User queryUser(short userId){
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userId,phone,phone_md5,code,token from User where userId=?";
		try {
			ps = conn.prepareStatement(sql);
			//设置占位符对应的值
			ps.setShort(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getShort(1));
				user.setPhone(rs.getString(2));
				user.setPhone_md5(rs.getString(3));
				user.setCode(rs.getString(4));
				user.setToken(rs.getString(5));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);	
		}
		return user;
	}

	//根据用户ID删除一条用户数据
	public boolean deleteUser(String userId){
		boolean result = false;
		short id = Short.parseShort(userId);
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from User where userId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setShort(1, id);
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
