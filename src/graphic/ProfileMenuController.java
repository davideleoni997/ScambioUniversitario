package graphic;

import bean.UserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileMenuController {
	private ViewController vc;
	private UserBean ub;
	
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
}
