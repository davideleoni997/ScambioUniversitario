package graphic;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.Message;

public class MessageDetailAdapter {
	
	private Message msg;
	@FXML
	private Label lblsender;
	
	@FXML
	private Label lblmsg;
	
	
	public MessageDetailAdapter() {
		
	}
	
	public void setAdapter(Message msg) {
		this.msg=msg;
		lblsender.setText(String.valueOf(this.msg.getFrom()));
	
		lblmsg.setText(this.msg.getDesc());
	}
}
