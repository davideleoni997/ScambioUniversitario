package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import dao.MessageDao;
import logic.Message;

public class MessageController {
	
	private static MessageController instance;

    public static MessageController getInstance() {
        if (instance == null)
            instance = new MessageController();
        return instance;
    }
	
	private MessageController() {
		//Costruttore vuoto
	}
	
	public List<Message> getMessageList(UserBean ub) {
		try {
			return MessageDao.messageList(ub.getId());
		} catch (Exception e) {
			
		Logger.getGlobal().log(Level.WARNING, "getMessage", e);
		return new LinkedList<>();
		}	
	}
	
	public List<Message> getConversation(Integer sender) {
		try {
			return MessageDao.conversation(sender);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getConv", e);
			return new LinkedList<>();
		
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
