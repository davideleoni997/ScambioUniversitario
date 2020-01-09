package graphic;

import bean.UserBean;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginViewController{

	private ViewController vc;
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

	public LoginViewController() {
		vc = ViewController.getInstance();
		
		
	}
	
	@FXML
	public void Login(ActionEvent event) {
		UserBean lb = new UserBean();
		lb.setUsername(tfuser.getText());
		lb.setPassword(tfpsw.getText());
		LoginController lc = LoginController.getInstance();
		if(lc.validate("user",lb)) {
		
		vc.getScenes().add(btnsub.getScene());
		vc.createProfileMenu(lb);
		
		
		}
		else
			lblerrormsg.setText("Dati Errati");
			lblerrormsg.setVisible(true);
	}
}

