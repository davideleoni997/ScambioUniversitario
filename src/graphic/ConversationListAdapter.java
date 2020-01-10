package graphic;

import bean.UserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Message;

public class ConversationListAdapter {

	private Message msg;
	private UserBean lb;
	
	@FXML
	private Label lblsender;
	
	@FXML
	private Button btndetail; 
	
	private ViewController vc;
	
	public ConversationListAdapter() {
		vc = ViewController.getInstance();
	}
	
	public void setAdapter(UserBean lb,Message msg) {
		this.lb=lb;
		this.msg = msg;
		lblsender.setText(String.valueOf(msg.getFrom()));
	}
	
	@FXML
	public void messageDetail() {
		vc.getScenes().add(btndetail.getScene());
		vc.createMessageDetailMenu(lb, msg.getFrom());
	}
	
}
