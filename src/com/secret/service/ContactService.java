package com.secret.service;

import java.util.List;

import com.secret.model.Contact;

public interface ContactService {	//contact实体类的业务接口

	//增加联系人
	public void addContact();
	
	//删除联系人
	public void delContact();
	
	//上传联系人数据
	public boolean uploadContact(List<Contact> contacts);
	
	//删除联系人数据
	public boolean deleteOldContacts(short userId);
}
