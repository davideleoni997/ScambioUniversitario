package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import bean.UserBean;
import controller.LoginController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModifyProfileViewController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	
	private UserBean lb;
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblSurname;
	
	@FXML
	private Label lblOldPsw;
	
	@FXML
	private Label lblNewPsw;
	
	@FXML
	private TextField txtname;
	
	@FXML
	private TextField txtsurname;
	
	@FXML
	private TextField txtuser;
	
	@FXML
	private TextField txtoldpsw;
	
	@FXML
	private TextField txtnewpsw;
	
	@FXML
	private Button btnsubmit;
	
	@FXML
	private Button btnback;
	
	@FXML
	private Label lblerror;
	
	public ModifyProfileViewController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	@FXML
	public void submit() {
		LoginController lc = LoginController.getInstance();
		if(lb.getPassword().equalsIgnoreCase(txtoldpsw.getText())) {
		lb.setNome(txtname.getText());
		lb.setCognome(txtsurname.getText());
		lb.setUsername(txtuser.getText());
		lb.setPassword(txtnewpsw.getText());
		if(lc.updateInfo(lb)) {
			vc.getScenes().pop();
			vc.createProfileMenu(lb);
		}
		else lblerror.setText(lg.getErrorString());
		
		}
		else
			lblerror.setText(lg.getWrongOldPsw());
	}
	
	
	
	@FXML
	public void goBack() {
		vc.goBack();
	}

	public void setLb(UserBean lb) {
		this.lb = lb;
		txtname.setText(lb.getNome());
		txtsurname.setText(lb.getCognome());
		txtuser.setText(lb.getUsername());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblName.setText(lg.getNameString()+":");
		lblSurname.setText(lg.getSurnameString()+":");
		lblOldPsw.setText(lg.getOldPasswordString()+":");
		lblNewPsw.setText(lg.getNewPasswordString()+":");
		btnsubmit.setText(lg.getSubmitString());
		txtname.setEditable(false);
		txtsurname.setEditable(false);
		
	}
}
