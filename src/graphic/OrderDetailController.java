package graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Order;

public class OrderDetailController {

	private ViewController vc;
	
	private Order order;
	
	@FXML
	private Label lblbuyer;
	
	@FXML
	private Label lblseller;
	
	@FXML
	private Label lblprezzo;
	
	@FXML
	private Label lblitem;
	
	@FXML
	private Label lbldate;
	
	@FXML
	private Button btnback;
	
	public OrderDetailController() {
		vc = ViewController.getInstance();
	}
	
	@FXML
	public void back() {
		vc.goBack();
	}
	
	public void setOrder(Order o) {
		this.order = o;
	}
	
	public void update() {
		lblbuyer.setText(order.getBuyer());
		lblseller.setText(order.getSeller());
		lblprezzo.setText(String.valueOf(order.getItem().getPrezzo()));
		lblitem.setText(order.getItem().getNome());
		lbldate.setText(String.valueOf(order.getData()));
	}
}
