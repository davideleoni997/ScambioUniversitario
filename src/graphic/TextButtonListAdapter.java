package graphic;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import bean.OrderBean;
import factory.LanguageFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class TextButtonListAdapter implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	
	private Integer orderId;
	
	@FXML
	private Label lbldetail;
	
	@FXML
	private Button btndetail;
	
	public TextButtonListAdapter() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btndetail.setText(lg.getDetailsString());
		
	}
}
