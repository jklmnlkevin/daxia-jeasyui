package com.daxia.wy.service;

import com.daxia.wy.dao.UserMessagesDAO;
import com.daxia.wy.model.UserMessages;

public class UserMessagesService{
	private UserMessagesDAO userMessagesDAO;
	
	public void save(UserMessages userMessages){
		this.userMessagesDAO.save(userMessages);
	}

	public UserMessagesDAO getUserMessagesDAO() {
		return userMessagesDAO;
	}

	public void setUserMessagesDAO(UserMessagesDAO userMessagesDAO) {
		this.userMessagesDAO = userMessagesDAO;
	}
	
}
