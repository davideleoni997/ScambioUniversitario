package controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import dao.MessageDao;
import logic.Message;

public class MessageController {

	
	public MessageController() {
		//Costruttore vuoto
	}
	
	public Message[] getMessageList(UserBean ub) {
		try {
			return MessageDao.messageList(ub.getId());
		} catch (Exception e) {
			
		Logger.getGlobal().log(Level.WARNING, "getMessage", e);
		return new Message[0];
		}	
	}
	
	public Message[] getConversation(Integer sender) {
		try {
			return MessageDao.conversation(sender);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getConv", e);
			return new Message[0];
		
		}
	}
	
	public void newMessage(Integer sender,Integer to,String desc) {
		try {
			MessageDao.newMessage(sender, to, desc);
		} catch (Exception e) {
			
		Logger.getGlobal().log(Level.WARNING, "newMessage", e);
	
		}
	}
}
