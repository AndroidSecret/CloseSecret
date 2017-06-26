package com.secret.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secret.model.Reply;
import com.secret.model.User;
import com.secret.service.ContactService;
import com.secret.service.MessageService;
import com.secret.service.ReplyService;
import com.secret.service.UserService;
import com.secret.serviceImpl.ContactServiceImpl;
import com.secret.serviceImpl.MessageServiceImpl;
import com.secret.serviceImpl.ReplyServiceImpl;
import com.secret.serviceImpl.UserServiceImpl;

import net.sf.json.JSONObject;

public class PubComment extends HttpServlet{	//发表评论
	private static final long serialVersionUID = 1L;
	
	private static UserService userService = UserServiceImpl.getInstance();
	private static ReplyService repService = ReplyServiceImpl.getInstance();
	
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
        String content = request.getParameter("content");    
        String msgId = request.getParameter("msgId");
          
        String str="";   
        int status = 0;
        if ( !(str.equals(phone_md5)) && !(str.equals(token)) && !(str.equals(content)) && !(str.equals(msgId)) ){  

        	short userId = 0;
        	User user = userService.getUser(phone_md5);
        	if(user != null){
        		userId = user.getUserId();
        	}
        	short rmsgId = Short.parseShort(msgId);
        	if(repService.addCommment(new Reply(userId, rmsgId, content))){
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
        String action = "pub_comment";
        request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}



