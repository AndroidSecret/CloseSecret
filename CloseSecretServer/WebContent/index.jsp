<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

out.clear();

String action = (String)request.getAttribute("action");

String result =(String)request.getAttribute("result");

if(action!=null){
	if(action.equals("send_pass")){
		out.println(result);
	}else if(action.equals("login")){
		out.println(result);
	}else if(action.equals("upload_contacts")){
		out.println(result);
	}else if(action.equals("timeline")){
		out.println(result);
	}else if(action.equals("get_comment")){
		out.println(result);
	}else if(action.equals("pub_comment")){
		out.println(result);
	}else if(action.equals("publish")){
		out.println(result);
	}
}else{
	out.print("test");
}

%>