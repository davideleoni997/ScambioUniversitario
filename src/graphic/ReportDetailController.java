package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import bean.UserBean;
import controller.ReportController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Report;

public class ReportDetailController implements Initializable{
	private Report rep;
	private ViewController vc;
	private LanguageFactory lg;
	private UserBean lb;
	
	@FXML
	private Label lblFrom;
	
	@FXML
	private Label txtFrom;
	
	@FXML
	private Label lblDesc;
	
	@FXML
	private Label txtDesc;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnBan;
	
	@FXML
	private Button btnNoAction;
	
	
	
	public ReportDetailController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}

	@FXML
	public void goback() {
		vc.goBack();
	}
	
	@FXML
	public void noAction() {
		ReportController rc = new ReportController();
		rc.removeReport(rep.getId());
		vc.getScenes().pop();
		vc.createAdminMenu(lb);
	}
	
	@FXML
	public void ban() {
		ReportController rc = new ReportController();
		rc.Ban(rep.getId(), rep.getInsId());
		vc.getScenes().pop();
		vc.createAdminMenu(lb);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblFrom.setText(lg.getFromString());
		lblDesc.setText(lg.getDescriptionString());
		btnBack.setText(lg.getBackString());
		btnNoAction.setText(lg.getNoAction());
		btnBan.setText(lg.getDeleteInsertionString());
	}
	
	public void setReport(Report rep) {
		this.rep = rep;
		txtFrom.setText(String.valueOf(rep.getReporter()));
		txtDesc.setText(rep.getDesc());
	}
	
	public void setUser(UserBean lb) {
		this.lb = lb;
	}
}
