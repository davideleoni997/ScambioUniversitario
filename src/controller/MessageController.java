package controller;

import bean.UserBean;
import dao.MessageDao;
import logic.Message;

public class MessageController {

	
	public MessageController() {
		//Costruttore vuoto
	}
	
	public Message[] getMessageList(UserBean ub) {
		return MessageDao.messageList(ub.getId());	
	}
	
	public Message[] getConversation(Integer sender) {
		return MessageDao.conversation(sender);
	}
	
	public void newMessage(Integer sender,Integer to,String desc) {
		MessageDao.newMessage(sender, to, desc);
	}
}
