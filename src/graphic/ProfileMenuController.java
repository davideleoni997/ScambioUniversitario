package graphic;


import java.net.URL;

import java.util.ResourceBundle;


import bean.UserBean;
import factory.LanguageFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.Property;

public class ProfileMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private UserBean ub;
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblSurname;
	
	@FXML
	private Label txtname;
	
	@FXML
	private Label txtsurname;
	
	@FXML
	private Button btnOrders;
	
	@FXML
	private Button btnMod;
	
	@FXML
	private Button btnMsg;
	
	@FXML
	private Button btnLogOut;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnModifyOrders;
	
	@FXML
	private ImageView imgLogo;
	
	public ProfileMenuController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
		
	
	}
	
	@FXML
	public void viewOrders() {
		vc.getScenes().push(txtname.getScene());
		vc.createOrderMenu(ub.getUsername());
	}
	
	@FXML
	public void modifyProfile() {
		vc.getScenes().push(txtname.getScene());
		vc.createModifyMenu(ub);
	}
	
	@FXML
	public void viewMessages() {
		vc.getScenes().push(txtname.getScene());
		vc.createMessageMenu(ub);
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	@FXML
	public void modifyInsertions() {
		vc.getScenes().push(txtname.getScene());
		vc.createModifyInsertionMenu(ub.getId());
	}
	
	@FXML
	public void logOut() {
		Property prop = new Property();
		prop.setProperty("user_id", "0");
		vc.goBack();
	}
	
	public void setUser(UserBean ub) {
		
		this.ub = ub;
		txtname.setText(ub.getNome());
		txtsurname.setText(ub.getCognome());
		Runnable update = () -> 
		{ if(ub.getCompany()) {
			imgLogo.setImage(ub.getLogo());
			imgLogo.setVisible(true);
			lblSurname.setVisible(false);
		} };
		Platform.runLater(update);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblName.setText(lg.getNameString()+":");
		lblSurname.setText(lg.getSurnameString()+":");
		btnOrders.setText(lg.getViewOrders());
		btnMod.setText(lg.getModifyProfile());
		btnMsg.setText(lg.getMessagesString());
		btnBack.setText(lg.getBackString());
		btnModifyOrders.setText(lg.getModifyInsertionString());
	}
}
