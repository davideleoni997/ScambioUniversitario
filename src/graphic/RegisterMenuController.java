package graphic;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import controller.RegistrationController;
import external.MockupUniDB;
import factory.LanguageFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RegisterMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private File logo; 
	
	@FXML
	private ToggleGroup tgStud;
	
	@FXML
	private RadioButton radioStud;
	
	@FXML
	private RadioButton radioCompany;
	
	@FXML
	private Label lblStudQuestion;
	
	@FXML
	private Label lblEnroll;
	
	@FXML
	private TextField txtEnroll;
	
	@FXML
	private Label lblName;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label lblSurname;
	
	@FXML
	private TextField txtSurname;
	
	@FXML
	private Label lblUser;
	
	@FXML
	private TextField txtUser;
	
	@FXML
	private Label lblPsw;
	
	@FXML
	private TextField txtPsw;
	
	@FXML
	private Label lblCheck;
	
	@FXML
	private TextField txtCheck;
	
	@FXML
	private Label lblLogo;
	
	@FXML
	private Button btnLogo;
	
	@FXML
	private Label lblUpload;
	
	@FXML
	private ImageView imgLogo;
	
	@FXML
	private Button btnSubmit;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Label lblError;
	
	public RegisterMenuController() {
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblStudQuestion.setText(lg.getStudQuestion());
		lblEnroll.setText(lg.getEnrollString());
		lblName.setText(lg.getNameString());
		lblSurname.setText(lg.getSurnameString());
		lblPsw.setText(lg.getNewPasswordString());
		lblCheck.setText(lg.getCheckString());
		lblLogo.setText(lg.getLogoString());
		btnLogo.setText(lg.getUploadString());
		btnSubmit.setText(lg.getSubmitString());
		btnBack.setText(lg.getBackString());
		radioStud.setText(lg.getStudString());
		radioCompany.setText(lg.getCompanyString());
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	@FXML
	public void submit() {
		if(txtPsw.getText().equals(txtCheck.getText())) {
			RegistrationController rc = RegistrationController.getInstance();
			RadioButton selected = (RadioButton) tgStud.getSelectedToggle();
			if(selected.getText().equals(lg.getStudString())) {
				if(MockupUniDB.isUserInDB(txtEnroll.getText(), txtName.getText(), txtSurname.getText()))
					if(!rc.registraUtente(txtName.getText(), txtSurname.getText(), txtUser.getText(), txtPsw.getText(), false, txtEnroll.getText())) {
						Runnable update = () -> {
							lblError.setText(lg.getAlreadyExistingString());
						};
						Platform.runLater(update);
					}
					else {
						Runnable update = () -> {
							lblError.setText("Okay");
						};
						Platform.runLater(update);
					}
						
				else {
					Runnable update = () -> {
						lblError.setText(lg.getEnrollErrorString());
					};
					Platform.runLater(update);
					
				}
			}
			else
				rc.registraSocieta(txtName.getText(), txtUser.getText(), txtPsw.getText(), true, logo);
		}
		else {
			Runnable update = () -> {
				lblError.setText(lg.getCheckError());
			};
			Platform.runLater(update);
		}
			
		
	}
	
	@FXML
	public void upload() {
		final FileChooser fc = new FileChooser();
		ExtensionFilter ef = new ExtensionFilter("Images","*.png");
		fc.getExtensionFilters().add(ef);
		logo = fc.showOpenDialog(vc.getPrimaryStage());
		if(logo!=null) {
			Runnable update = () -> {
				lblUpload.setText(logo.getPath());
			lblUpload.setVisible(true);
			imgLogo.setImage(new Image(logo.toURI().toString()));
			imgLogo.setVisible(true);};
			
			Platform.runLater(update);
			
		}
		else {
			Runnable update = () -> {
				lblUpload.setText(lg.getNoLogoString());
			lblUpload.setVisible(true);};
			
			Platform.runLater(update);
		}
	}
	
	@FXML
	public void studentSelected() {
		Runnable update = () -> {
			lblEnroll.setVisible(true);
		txtEnroll.setEditable(true);
		txtEnroll.setVisible(true);
		lblSurname.setVisible(true);
		txtSurname.setVisible(true);
		lblLogo.setVisible(false);
		btnLogo.setVisible(false);
		btnLogo.setDisable(true);
		imgLogo.setVisible(false);
		lblUpload.setVisible(false);};
		Platform.runLater(update);
		
		
	}
	
	@FXML
	public void companySelected() {
		Runnable update = () -> {lblEnroll.setVisible(false);
		txtEnroll.setEditable(false);
		txtEnroll.setVisible(false);
		lblSurname.setVisible(false);
		txtSurname.setVisible(false);
		lblLogo.setVisible(true);
		btnLogo.setDisable(false);
		btnLogo.setVisible(true);
		imgLogo.setVisible(true);
		if(logo == null) {
			lblUpload.setText("");
			lblUpload.setVisible(true);
		}
		};
		Platform.runLater(update);
	}
}
