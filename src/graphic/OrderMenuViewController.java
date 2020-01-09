package graphic;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import bean.OrderBean;
import controller.OrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import logic.Order;

public class OrderMenuViewController {

	private ViewController vc;
	
	@FXML
	private ListView<VBox> listOrders;
	
	@FXML
	private Label lblOrders;
	
	@FXML
	private Button btnBack;
	
	public OrderMenuViewController() {
		vc = ViewController.getInstance();
		
	}
	
	
	public void updateOrders(String user) {
		try{
			Order list[] = null;
		
	
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
			e.printStackTrace();
		}
	}
	
	@FXML
	public void back() {
		vc.goBack();
	}
}
