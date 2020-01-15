package graphic;


import java.net.URL;
import java.util.ResourceBundle;

import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import logic.Message;

public class MessageDetailAdapter implements Initializable{
	
	private LanguageFactory lg;
	
	@FXML
	private Label lblSender;
	
	@FXML
	private Label lblMessage;
	
	@FXML
	private Label lblsender;
	
	@FXML
	private Label lblmsg;
	
	
	public MessageDetailAdapter() {
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	public void setAdapter(Message msg) {
		
		lblsender.setText(String.valueOf(msg.getFrom()));
	
		lblmsg.setText(msg.getDesc());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblSender.setText(lg.getFromString());
		lblMessage.setText(lg.getMessageString());
	}
}
