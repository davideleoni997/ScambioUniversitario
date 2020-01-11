package graphic;

import javafx.scene.control.Label;
import bean.OrderBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TextButtonListAdapter {

	private ViewController vc;
	
	private Integer orderId;
	
	@FXML
	private Label lbldetail;
	
	@FXML
	private Button btndetail;
	
	public TextButtonListAdapter() {
		vc = ViewController.getInstance();
	}
	
	@FXML
	public void showDetail(ActionEvent event) {
		vc.getScenes().push(btndetail.getScene());
		vc.createOrderDetailMenu(orderId);	
	}
	
	public void setOrder(OrderBean ob) {
		this.orderId = ob.getId();
		lbldetail.setText("ID: "+ ob.getId());
	}
}
