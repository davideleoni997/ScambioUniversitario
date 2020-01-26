package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import controller.ReportController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.Property;

public class NewReportController implements Initializable{

	private ViewController vc;
	private LanguageFactory lg;
	private Integer insId;
	
	
	@FXML
	private Label lblInsertion;
	
	@FXML
	private Label txtInsertion;
	
	@FXML
	private Label lblDescription;
	
	@FXML
	private TextArea txtDescription;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnSubmit;
	
	public NewReportController() {
		vc = ViewController.getInstance();
		
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);		
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	@FXML
	public void submit() {
		Property prop = new Property();
		ReportController rc = new ReportController();
		rc.newReport(insId, txtDescription.getText(), Integer.parseInt(prop.loadProperty("user_id")));
	}
	
	public void setInfo(Integer insId) {
		this.insId = insId;
		txtInsertion.setText(insId + "");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblInsertion.setText("ID :");
		lblDescription.setText(lg.getDescriptionString());
		btnBack.setText(lg.getBackString());
		btnSubmit.setText(lg.getSubmitString());
		
	}
}
