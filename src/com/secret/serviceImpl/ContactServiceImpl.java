package com.secret.serviceImpl;

import java.util.List;

import com.secret.dao.ContactDao;
import com.secret.model.Contact;
import com.secret.service.ContactService;

public class ContactServiceImpl implements ContactService{	//contact类的业务接口的具体实现类

	private static ContactDao contDao = null;

	private static ContactServiceImpl csi = null;
	
	public static ContactServiceImpl getInstance() {
		if (contDao == null) {
			contDao = new ContactDao();
		}
		if (csi == null) {
			csi = new ContactServiceImpl();
		}
		return csi;
	}
	
	@Override
	public void addContact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delContact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean uploadContact(List<Contact> contacts) {
		return contDao.addContact(contacts);
		
	}

	@Override
	public boolean deleteOldContacts(short userId) {
		boolean result = contDao.deleteContacts(userId);
		return result;
	}

}
