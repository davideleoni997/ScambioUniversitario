package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import controller.MessageController;
import logic.Message;

class TestSendMessage {

	@Test
	void testSendMessage() {
		//We try to send a message to ourselves with the message "Hello", this won't work
		//if the user does not exists; if the user exists we get the last message description and see if it is equals to "Hello"
		//Creator : Davide Leoni
		
		MessageController mc = new MessageController();
		mc.newMessage(3, 3, "Hello");
		List<Message> message = mc.getConversation(3);
		assertEquals("Hello",message.get(message.size()-1).getDesc());
	}

}
