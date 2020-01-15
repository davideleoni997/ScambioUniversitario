package graphic;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainMenuController implements Initializable{
	LanguageFactory lg;
	ViewController vc;
	
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
		vc.getScenes().push(btnLogin.getScene());
		vc.createLoginMenu();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnRegister.setText(lg.getRegister());
		lblSearch.setText(lg.getSearchText());
		btnSearch.setText(lg.getSearchString());
		
	}
	
}