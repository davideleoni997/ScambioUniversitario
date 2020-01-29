package graphic;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.LoginController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.Property;

public class MainMenuController implements Initializable{
	LanguageFactory lg;
	ViewController vc;
	
	@FXML
	private Button btnAdmin;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button btnRegister;
	
	@FXML
	private TextField txtResearch;
	
	@FXML
	private Label lblSearch;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private Label lblInsertionText;
	
	@FXML
	private Button btnInsertion;
	
	public MainMenuController() {
		vc = ViewController.getInstance();
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
			
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"LogViewController",e);
		}
	}

	
	@FXML
	private void login() {
		Property prop = new Property();
		if(prop.loadProperty("user_id").equalsIgnoreCase("0")) {
			vc.getScenes().push(btnLogin.getScene());
			vc.createLoginMenu("Profile");
		}
		else {
			LoginController lc = LoginController.getInstance();
			vc.getScenes().push(btnLogin.getScene());
			Integer id = Integer.parseInt(prop.loadProperty("user_id"));
			vc.createProfileMenu(lc.getUserFromId(id));
		}
	}
	
	@FXML
	private void register() {
		vc.getScenes().push(btnLogin.getScene());
		vc.createRegisterMenu();
	}
	
	@FXML
	private void search() {
		vc.getScenes().push(btnSearch.getScene());
		vc.createResearchMenu(txtResearch.getText());
	}
	
	@FXML
	private void newInsertion() {
		vc.getScenes().push(btnInsertion.getScene());
		vc.createNewInsertionMenu();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRegister.setText(lg.getRegister());
		lblSearch.setText(lg.getSearchText());
		btnSearch.setText(lg.getSearchString());
		lblInsertionText.setText(lg.getNewInsertionText());
		btnInsertion.setText(lg.getNewInsertionString());
		
	}
	
	@FXML
	private void adminLog() {
		vc.getScenes().push(btnLogin.getScene());
		vc.createLoginMenu("admin");
	}
	
}
