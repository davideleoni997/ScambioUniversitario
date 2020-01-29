package graphic;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



import bean.UserBean;
import controller.LoginController;

import factory.LanguageFactory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.Property;


public class LoginViewController implements Initializable{

	private static final String ADMIN = "admin";
	private LanguageFactory lg;
	private ViewController vc;
	private String dispatch;
	
	@FXML
	private Label lblWelcome;
	@FXML
	private Label lbluser;
	@FXML
	private Label lblpsw;
	@FXML
	private TextField tfuser;
	@FXML
	private TextField tfpsw;
	@FXML
	private Button btnsub;
	@FXML
	private Label lblerrormsg;
	@FXML
	private Button btnBack;

	public LoginViewController() {
		//Needed to initialize the controller for javaFX
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		vc = ViewController.getInstance();
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
			
			lblWelcome.setText(lg.getWelcomeString());
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"LogViewController",e);
		}
    } 
	
	@FXML
	public void login(ActionEvent event) {
		UserBean lb = new UserBean();
		lb.setUsername(tfuser.getText());
		lb.setPassword(tfpsw.getText());
		LoginController lc = LoginController.getInstance();
		String logType = "user";
		if(dispatch.equalsIgnoreCase(ADMIN))
			logType = ADMIN;
			
		try {
			if(lc.validate(logType,lb)) {
				Property prop = new Property();
				prop.setProperty("user_id",String.valueOf(lb.getId()));
				
				if(dispatch.equalsIgnoreCase("NewInsertion"))
					vc.createNewInsertionMenu();
				if(dispatch.equalsIgnoreCase("Profile"))
					vc.createProfileMenu(lb);
				if(dispatch.equalsIgnoreCase("Buy"))
					vc.goBack();
				if(dispatch.equalsIgnoreCase(ADMIN)) {
					vc.createAdminMenu(lb);
				}
			}
			else {
				lblerrormsg.setText(lg.getWrongData());
				lblerrormsg.setVisible(true);
			}
		} catch (ClassNotFoundException e) {
			Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
		} catch (SQLException e) {
			
			Logger.getGlobal().log(Level.WARNING, "SQLException", e);
		}
	}
	
	public void setDisp(String disp) {
		this.dispatch = disp;
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
}

