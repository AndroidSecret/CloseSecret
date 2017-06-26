package com.secret.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secret.model.Message;
import com.secret.service.ContactService;
import com.secret.service.MessageService;
import com.secret.service.ReplyService;
import com.secret.service.UserService;
import com.secret.serviceImpl.ContactServiceImpl;
import com.secret.serviceImpl.MessageServiceImpl;
import com.secret.serviceImpl.ReplyServiceImpl;
import com.secret.serviceImpl.UserServiceImpl;

import net.sf.json.JSONObject;

public class PubMessage extends HttpServlet{	//发表消息
	private static final long serialVersionUID = 1L;
	
	private static MessageService msgService = MessageServiceImpl.getInstance();
	
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
        String phone_md5 = request.getParameter("phone_md5");
        String token = request.getParameter("token");
        String msg = request.getParameter("msg");
        System.out.println("phone_md5:"+phone_md5);  
        System.out.println("token:"+token);  
        System.out.println("msg:"+msg);  
        
        String str="";   
        int status = 0;
        if ( !(str.equals(phone_md5)) && !(str.equals(token)) && !(str.equals(msg)) ){  
        	
        	Message msgObj = new Message(msg, phone_md5);
        	if(msgService.addTopic(msgObj)){
        		status = 1;
        	}
        	
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
        String action = "publish";
        request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}

