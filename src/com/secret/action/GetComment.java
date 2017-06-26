package com.secret.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetComment extends HttpServlet{	//获取评论
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
        String msgId = request.getParameter("msgId");
          
        String str="";  
        short status = 0;
        if ( !(str.equals(phone_md5)) && !(str.equals(token)) && !(str.equals(msgId)) ){  
        	short rmsgId = Short.parseShort(msgId);
        	List<Reply> repList = repService.queryComment(rmsgId);
        	if(repList != null){
        		status = 1;
        		Map<String,Object> map = new HashMap<String, Object>();
            	List<JSONObject> array = new ArrayList<JSONObject>();
        		for(Reply item : repList){
        			JSONObject jsonItem = new JSONObject();
        			
        			String rcontent = item.getReplyContent();
        			User user = userService.getUser(item.getUserId());
        			String cont_phone_md5 = "";
        			if(user != null){
        				cont_phone_md5 = user.getPhone_md5();
        			}
        			
        			jsonItem.element("content", rcontent);
        			jsonItem.element("phone_md5", cont_phone_md5);
        			array.add(jsonItem);
        		}
        		map.put("items", JSONArray.fromObject(array));
            	map.put("status", status);
            	JSONObject json = JSONObject.fromObject(map);
            	str = json.toString();
        		
        	}
        }else{  
            str="参数为空！";  
        }  
        response.setContentType("text/json"); 
        request.setAttribute("result", str);
        String action = "get_comment";
        request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
