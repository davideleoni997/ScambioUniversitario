package graphic;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.OrderBean;
import controller.OrderController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import logic.Order;

public class OrderMenuViewController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	
	@FXML
	private ListView<VBox> listOrders;
	
	@FXML
	private Label lblOrders;
	
	@FXML
	private Button btnBack;
	
	public OrderMenuViewController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	
	public void updateOrders(String user) {
		try{
			Order[] list = null;
		
	
		OrderController oc = new OrderController(user);
		list = oc.getOrdersInfo(user);
		
		if(list != null)
			for(int i=0; i<list.length;i++)
			{
			
			
				if(list[i]!=null) {
					FXMLLoader fl = new FXMLLoader();
				
					fl.setLocation(getClass().getResource("TextButtonAdapter.fxml"));
					VBox root = (VBox) fl.load();
					TextButtonListAdapter tbla =fl.getController();
					OrderBean ob = new OrderBean();
					ob.setId(list[i].getId());
					ob.setUser(list[i].getSeller());
					tbla.setOrder(ob);
			
					listOrders.getItems().add(root);		
		
				}
		}
		else {
			//No orders
		}
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:Login",e);
		}
	}
	
	@FXML
	public void back() {
		vc.goBack();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblOrders.setText(lg.getYourOrdersString());
		
	}
}
