package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Order;

public class OrderDetailController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	
	private Order order;
	
	@FXML
	private Label txtbuyer;
	
	@FXML
	private Label txtseller;
	
	@FXML
	private Label txtprezzo;
	
	@FXML
	private Label txtitem;
	
	@FXML
	private Label txtdate;
	
	@FXML
	private Label lblBuyer;
	
	@FXML
	private Label lblSeller;
	
	@FXML
	private Label lblPrezzo;
	
	@FXML
	private Label lblItem;
	
	@FXML
	private Label lblDate;
	
	@FXML
	private Button btnback;
	
	public OrderDetailController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	@FXML
	public void back() {
		vc.goBack();
	}
	
	public void setOrder(Order o) {
		this.order = o;
	}
	
	public void update() {
		txtbuyer.setText(order.getBuyer());
		txtseller.setText(order.getSeller());
		txtprezzo.setText(String.valueOf(order.getItem().getPrezzo()));
		txtitem.setText(order.getItem().getNome());
		txtdate.setText(String.valueOf(order.getData()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblBuyer.setText(lg.getBuyerString()+":");
		lblSeller.setText(lg.getSellerString()+":");
		lblPrezzo.setText(lg.getPriceString()+":");
		lblItem.setText(lg.getItemString()+":");
		lblDate.setText(lg.getDateString()+":");
		btnback.setText(lg.getBackString());
	}
	
	private void pay() {
		//TODO add button to pay if not paid and mockup payment system
	}
}
