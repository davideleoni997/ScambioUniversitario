package graphic;

import bean.UserBean;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModifyProfileViewController {

	private ViewController vc;
	
	private UserBean lb;
	
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
		else lblerror.setText("Error");
		
		}
		else
			lblerror.setText("Wrong Old password");
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
}
