package com.secret.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.secret.model.User;

public class DBUtil {	//mysql数据库的数据操作工具类

	public static String URL;	//数据库连接地址
	public static String USERNAME;	//用户名
	public static String PASSWORD;	//密码
	public static String DRIVER;	//mysql的驱动类

	private Connection conn = null;	//定义数据库的连接
	private PreparedStatement pStatement = null;	//定义sql语句的执行对象
	private ResultSet rs = null;	//定义查询返回的结果集合
	private static ResourceBundle rb = ResourceBundle.getBundle("com.secret.util.db-config");

	//单例模式
	private DBUtil(){}

	//使用静态块加载驱动程序
	static{
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
		DRIVER = rb.getString("jdbc.driver");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//定义一个获取数据库连接的方法
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param stat
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stat,Connection conn){
		try {
			if(rs!=null)rs.close();
			if(stat!=null)stat.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stat,Connection conn){
		try {
			if(stat!=null)stat.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		User user = null;
//		//通过工具类获取数据库连接
//        Connection conn = DBUtil.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "select userId,phone,phone_md5,code,token from User";
//        try {
//            ps = conn.prepareStatement(sql);
//            //设置占位符对应的值
////            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                user = new User();
//                user.setUserId(rs.getShort(1));
//                user.setPhone(rs.getString(2));
//                user.setPhone_md5(rs.getString(3));
//                user.setCode(rs.getString(4));
//                user.setToken(rs.getString(5));
//                System.out.println(user.toString());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            //通过工具类关闭数据库连接
//            DBUtil.close(rs, ps, conn);
//        }
//	}
	
	/**
	 * 结果：
	 * userId:1	phone:123456	phone_md5:213124	code:1234	token:1111
	 * userId:2	phone:654321	phone_md5:361516543	code:4321	token:2222
	 */

}



