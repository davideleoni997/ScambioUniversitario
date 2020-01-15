package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import bean.UserBean;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Message;

public class ConversationListAdapter implements Initializable{
	private LanguageFactory lg;
	private Message msg;
	private UserBean lb;
	
	@FXML
	private Label lblSender;
	
	@FXML
	private Label lblsender;
	
	@FXML
	private Button btndetail; 
	
	private ViewController vc;
	
	public ConversationListAdapter() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	public void setAdapter(UserBean lb,Message msg) {
		this.lb=lb;
		this.msg = msg;
		lblsender.setText(String.valueOf(msg.getFrom()));
	}
	
	@FXML
	public void messageDetail() {
		vc.getScenes().push(btndetail.getScene());
		vc.createMessageDetailMenu(lb, msg.getFrom());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblSender.setText(lg.getFromString());
		btndetail.setText(lg.getDetailsString());
	}
	
}
