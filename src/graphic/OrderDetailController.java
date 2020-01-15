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
		lblbuyer.setText(order.getBuyer());
		lblseller.setText(order.getSeller());
		lblprezzo.setText(String.valueOf(order.getItem().getPrezzo()));
		lblitem.setText(order.getItem().getNome());
		lbldate.setText(String.valueOf(order.getData()));
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
}
