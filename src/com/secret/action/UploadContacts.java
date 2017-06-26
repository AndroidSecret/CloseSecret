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

import com.secret.model.Contact;
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

public class UploadContacts extends HttpServlet{	//上传联系人
	private static final long serialVersionUID = 1L;

	private static UserService userService = UserServiceImpl.getInstance();
	private static ReplyService repService = ReplyServiceImpl.getInstance();
	private static MessageService msgService = MessageServiceImpl.getInstance();
	private static ContactService contService = ContactServiceImpl.getInstance();

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
		String contacts = request.getParameter("contacts");
		System.out.println("phone_md5:"+phone_md5);
		System.out.println("token:"+token);
		System.out.println("contacts:"+contacts);  
		System.out.println();

		String str="";  
		short status = 0;
		if ( !(str.equals(phone_md5)) && !(str.equals(token)) && !(str.equals(contacts)) ){  

			User user = userService.getUser(phone_md5);
			short userId = 0;
			if(user != null){
				userId = user.getUserId();
			}
			contService.deleteOldContacts(userId);
			List<Contact> contList = new ArrayList<Contact>();
			JSONArray jsonArray = JSONArray.fromObject(contacts);
			Contact cont = null;
			for(int i = 0 ; i < jsonArray.size(); i ++){
				cont = new Contact();
				
				JSONObject obj = new JSONObject();
				obj = (JSONObject) jsonArray.get(i);
				System.out.println(obj);
				
				String contact_phone_md5 = (String) obj.get("phone_md5");
				System.out.println(contact_phone_md5);
				
				cont.setUserId(userId);
				cont.setUser_phone_md5(phone_md5);
				cont.setContact_phone_md5(contact_phone_md5);
				contList.add(cont);
				System.out.println("----------------------");
			}
			if(contService.uploadContact(contList)){
				status = 1;
			}

			//向客户端返回json数据
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", status);
			JSONObject json = JSONObject.fromObject(map);
			str = json.toString();
		}else{  
			str="参数为空！";  
		}  
		response.setContentType("text/json");  
		request.setAttribute("result", str);
		String action = "upload_contacts";
		request.setAttribute("action", action);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}

