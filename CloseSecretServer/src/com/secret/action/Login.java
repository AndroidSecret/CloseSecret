package com.secret.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secret.service.ContactService;
import com.secret.service.MessageService;
import com.secret.service.ReplyService;
import com.secret.service.UserService;
import com.secret.serviceImpl.UserServiceImpl;
import com.secret.util.Token;

import net.sf.json.JSONObject;

public class Login extends HttpServlet{	//登录
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
        //设置编码格式  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        
        //获得用户手机号和验证码（app端需要传参数 phone_md5和code）  
        String phone_md5 = request.getParameter("phone_md5");  
        String code = request.getParameter("code");  
        System.out.println("phone_md5 : "+phone_md5);
        System.out.println("code : "+code);
        
        String str="";    
        if(!(str.equals(phone_md5)&&str.equals(code))){  
        	int status = 0;
        	if(userService.getUser(phone_md5) == null){
        		if(userService.addUser(phone_md5)){
            		System.out.println("添加成功");
            		status = 1;
            	}
        		
        	}else{
        		if(userService.login(phone_md5, code)){
            		status = 1;
            	}
        	}
        	
            String token = Token.generate();     
            if(userService.updateToken(token, phone_md5)){
            	System.out.println("成功更新token");
            }
        	//向客户端返回json数据
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("token", token);
        	map.put("status", status);
        	JSONObject json = JSONObject.fromObject(map);
        	str = json.toString();
        }else{  
            str="参数为空！";  
        }  
        response.setContentType("text/json");  
        request.setAttribute("result", str);
        String action = "login";
        request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
