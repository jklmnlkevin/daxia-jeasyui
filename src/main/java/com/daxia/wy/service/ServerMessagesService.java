package com.daxia.wy.service;

import com.daxia.wy.dao.ServerMessagesDAO;
import com.daxia.wy.model.ServerMessages;

public class ServerMessagesService {
	private ServerMessagesDAO serverMessagesDAO;
	
	public void save(ServerMessages serverMessages){
		serverMessagesDAO.save(serverMessages);
	}

	public ServerMessagesDAO getServerMessagesDAO() {
		return serverMessagesDAO;
	}

	public void setServerMessagesDAO(ServerMessagesDAO serverMessagesDAO) {
		this.serverMessagesDAO = serverMessagesDAO;
	}
	
}
