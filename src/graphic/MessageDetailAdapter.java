package graphic;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.Message;

public class MessageDetailAdapter {
	
	
	@FXML
	private Label lblsender;
	
	@FXML
	private Label lblmsg;
	
	
	public MessageDetailAdapter() {
		//Costruttore necessario a javafx
	}
	
	public void setAdapter(Message msg) {
		
		lblsender.setText(String.valueOf(msg.getFrom()));
	
		lblmsg.setText(msg.getDesc());
	}
}
