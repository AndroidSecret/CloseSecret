package com.secret.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secret.service.UserService;
import com.secret.serviceImpl.UserServiceImpl;
import com.secret.util.MD5Tool;

import net.sf.json.JSONObject;

public class SendPass extends HttpServlet{	//发送验证码
	private static final long serialVersionUID = 1L;
	
	private static UserService userService = UserServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置 编码格式  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        
        //获得用户手机号（app端需要传参数 phone）  
        String phone = request.getParameter("phone");  
        System.out.println("phone : "+phone);
        
        String str=""; 
        short status = 1;
        if(!(str.equals(phone))){  
        	String phone_md5 = MD5Tool.md5(phone);
        	System.out.println("phone_md5:"+phone_md5);
        	if(userService.getUserByPhone(phone) != null){
        		if(userService.addUser(phone_md5,phone)){
                }
        	}
        	status = 1;
        	//向客户端返回json数据
        	Map<String,Object> map = new HashMap<String, Object>();
        	map.put("status", status);
        	JSONObject json = JSONObject.fromObject(map);
        	str = json.toString();
        }else{  
            str="参数为空！";  
        }  
        response.setContentType("text/json");  
        request.setAttribute("result", str);
        String action = "send_pass";
        request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}

