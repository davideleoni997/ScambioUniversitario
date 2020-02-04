package graphic;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import controller.MessageController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.layout.Pane;

import logic.Message;

public class MessageMenuController implements Initializable{

	private LanguageFactory lg;
	@FXML
	private Button btnback;
	
	@FXML
	private Label lblMessages;
	
	@FXML
	private ListView<Pane> listMessages;
	
	private ViewController vc;
	
	public MessageMenuController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	public void update(UserBean lb) {
		try {
		
		
		
		MessageController mc = MessageController.getInstance();
		
		List<Message> list = mc.getMessageList(lb);	
		for(Message msg : list){
				
				FXMLLoader fl = new FXMLLoader();
				
				fl.setLocation(getClass().getResource("ConversationAdapter.fxml"));
				Pane root = (Pane) fl.load();
				ConversationListAdapter ca =fl.getController();	
				ca.setAdapter(lb, msg);
			
			
				listMessages.getItems().add(root);
			}
		}		
		
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"MessageMenuController" ,e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblMessages.setText(lg.getMessagesString()+":");
		btnback.setText(lg.getBackString());
	}
	
}
