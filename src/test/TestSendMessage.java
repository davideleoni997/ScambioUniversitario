package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import controller.LoginController;
import controller.MessageController;
import controller.RegistrationController;
import logic.Message;

class TestSendMessage {

	private static final String USERNAME = "dav";

	@Test
	void testSendMessage() {
		//We try to send a message to ourselves with the message "Hello", this won't work
		//if the id of the user does not exists; if the user exists we get the last message description and see if it is equals to "Hello"
		//Creator : Davide Leoni
		RegistrationController rc = RegistrationController.getInstance();
		rc.registraUtente("Davide", "Leoni", USERNAME, "pass", false, "X12345"); //Create a new user, if it fails the user exists
		//An exception will be thrown but we can just Log it
		LoginController lc = LoginController.getInstance();
		MessageController mc = MessageController.getInstance();
		mc.newMessage(lc.getIdFromUsername(USERNAME), lc.getIdFromUsername(USERNAME), "Hello");
		List<Message> message = mc.getConversation(lc.getIdFromUsername(USERNAME),lc.getIdFromUsername(USERNAME));
		assertEquals("Hello",message.get(message.size()-1).getDesc());
	}

}
