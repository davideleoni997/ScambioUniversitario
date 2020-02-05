package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import dao.MessageDao;
import logic.Message;

public class MessageController {
	//Singleton controller with the responsability of communicating with the Message entity
	private static MessageController instance;

    public static MessageController getInstance() {
        if (instance == null)
            instance = new MessageController();
        return instance;
    }
	
	private MessageController() {
		//Constructor private since it is a singleton
	}
	
	public List<Message> getMessageList(UserBean ub) {
		//Method to retrieve a list of messages corresponding to a user represented by the bean
		try {
			//retrieve the messages of a user using its id
			return MessageDao.messageList(ub.getId());
		} catch (Exception e) {
			
		Logger.getGlobal().log(Level.WARNING, "getMessage", e);
		return new LinkedList<>();
		}	
	}
	
	public List<Message> getConversation(Integer sender, Integer to) {
		//Get a list of messages exchanged between two users using their id
		try {
			return MessageDao.conversation(sender,to);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getConv", e);
			return new LinkedList<>();
		
		}
	}
	
	public void newMessage(Integer sender,Integer to,String desc) {
		//method to create a new message
		try {
			MessageDao.newMessage(sender, to, desc);
		} catch (Exception e) {
			
		Logger.getGlobal().log(Level.WARNING, "newMessage", e);
	
		}
	}
}
