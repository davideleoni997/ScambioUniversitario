package graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import controller.MessageController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.layout.Pane;

import logic.Message;

public class MessageMenuController {

	@FXML
	private Button btnback;
	
	@FXML
	private ListView<Pane> listMessages;
	
	private ViewController vc;
	
	public MessageMenuController() {
		vc = ViewController.getInstance();
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	public void update(UserBean lb) {
		try {
		Message[] list = null;
		
		
		
		MessageController mc = new MessageController();
		
		list = 	mc.getMessageList(lb);	
		if(list != null)
			for(int i=0; i<list.length;i++)
			{
			if(list[i]!=null) {
				
				FXMLLoader fl = new FXMLLoader();
				
				fl.setLocation(getClass().getResource("ConversationAdapter.fxml"));
				Pane root = (Pane) fl.load();
				ConversationListAdapter ca =fl.getController();	
				ca.setAdapter(lb, list[i]);
			
			
				listMessages.getItems().add(root);
			}
		}
		
		else {
			//NO Messages
		}
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"MessageMenuController" ,e);
		}
	}
	
}
