package graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import controller.MessageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import logic.Message;

public class MessageDetailController {
	private UserBean lb;
	private Message[] conv;
	private Integer id;
	@FXML
	private TextField txtNewMessage;
	
	@FXML
	private ListView<Pane> listMessages;
	
	@FXML
	private Button btnmessage;
	
	@FXML
	private Button btnback;
	
	private ViewController vc;
	
	public MessageDetailController() {
		vc = ViewController.getInstance();
	}
	
	public void update(UserBean lb,Integer id) {
	try {
		this.id = id;
		this.lb=lb;
		
		MessageController mc = new MessageController();
		conv = mc.getConversation(id);
		if(conv!=null)
			for(int i=0;i<conv.length;i++) {
				if(conv[i]!=null) {
					
					FXMLLoader fl = new FXMLLoader();
					fl.setLocation(getClass().getResource("MessageDetailAdapter.fxml"));
					Pane root = (Pane) fl.load();
					MessageDetailAdapter mda = fl.getController();
					mda.setAdapter(conv[i]);
					
					listMessages.getItems().add(root);
					
				}
			}
		
		}
	catch(Exception e) {
		Logger.getGlobal().log(Level.WARNING,"MessageDetailController",e);
	}
	}
	
	@FXML
	public void submit() {
		MessageController mc = new MessageController();
		mc.newMessage(lb.getId(), id, txtNewMessage.getText());
		listMessages.getItems().clear();
		this.update(lb,id);
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
}
