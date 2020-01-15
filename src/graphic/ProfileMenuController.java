package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import bean.UserBean;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private UserBean ub;
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblSurname;
	
	@FXML
	private Label lblname;
	
	@FXML
	private Label lblsurname;
	
	@FXML
	private Button btnOrders;
	
	@FXML
	private Button btnMod;
	
	@FXML
	private Button btnMsg;
	
	@FXML
	private Button btnBack;
	
	public ProfileMenuController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	@FXML
	public void viewOrders() {
		vc.getScenes().push(lblname.getScene());
		vc.createOrderMenu(ub.getUsername());
	}
	
	@FXML
	public void modifyProfile() {
		vc.getScenes().push(lblname.getScene());
		vc.createModifyMenu(ub);
	}
	
	@FXML
	public void viewMessages() {
		vc.getScenes().push(lblname.getScene());
		vc.createMessageMenu(ub);
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	public void setUser(UserBean ub) {
		this.ub = ub;
		lblname.setText(ub.getNome());
		lblsurname.setText(ub.getCognome());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblName.setText(lg.getNameString()+":");
		lblSurname.setText(lg.getSurnameString()+":");
		btnOrders.setText(lg.getViewOrders());
		btnMod.setText(lg.getModifyProfile());
		btnMsg.setText(lg.getMessagesString());
		
	}
}
