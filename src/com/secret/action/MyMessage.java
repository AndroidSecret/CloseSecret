package com.secret.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.secret.model.Message;
import com.secret.service.MessageService;
import com.secret.serviceImpl.MessageServiceImpl;

public class MyMessage extends HttpServlet{	//我的消息列表
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
        System.out.println("phone_md5"+phone_md5);
        System.out.println("token"+token);  
        
        String str="";  
        int status = 0;
        if ( !(str.equals(phone_md5)) && !(str.equals(token)) ){  

        	List<Message> timeline = msgService.getMyMessage(phone_md5);
        	Map<String,Object> map = new HashMap<String, Object>();
        	List<JSONObject> array = new ArrayList<JSONObject>();	
        	if(timeline.size() > 0){
        		System.out.println("not null");
        		status = 1;
        		for(int i = 0 ;i < timeline.size(); i ++){
        			Message item = timeline.get(i);
        			JSONObject jsonItem = new JSONObject();
        			
        			String imsg =((Message)item).getMsg();
        			String iphone_md5 = ((Message)item).getPhone_md5();
        			short imsgId = ((Message)item).getMsgId();
        			String icreatedAt = ((Message)item).getCreatedAt();
        			
        			jsonItem.element("msg", imsg);
        			jsonItem.element("phone_md5", iphone_md5);
        			jsonItem.element("msgId", imsgId);
        			jsonItem.element("createdAt", icreatedAt);
        			array.add(jsonItem);
        			System.out.println("myMessage:"+jsonItem.toString());
        		}
        		
        	}
        	map.put("items", JSONArray.fromObject(array));
    		System.out.println(JSONArray.fromObject(array));
        	map.put("status", status);
    		JSONObject json = JSONObject.fromObject(map);
        	str = json.toString();
        }else{  
            str="参数为空！";  
        }  
        response.setContentType("text/json");  
        request.setAttribute("result", str);
        String action = "timeline";
        request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
