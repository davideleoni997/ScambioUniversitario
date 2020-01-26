package graphic;

import java.net.URL;
import java.util.ResourceBundle;

import bean.UserBean;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.Report;

public class ReportAdapter implements Initializable{
	private ViewController vc;
	private Report rep;
	private LanguageFactory lg;
	private UserBean lb;
	
	@FXML
	private Button btnDetail;
	
	@FXML
	private Label txtInfo;
	
	public ReportAdapter() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	public void setReport(Report rep) {
		this.rep = rep;
		txtInfo.setText("ID :"+ rep.getId() +" " + lg.getFromString()+ ": "+ rep.getReporter() +" Reported:" + rep.getInsId());
	}
	
	public void setUser(UserBean lb) {
		this.lb = lb;
	}
	
	@FXML
	public void detail() {
		vc.getScenes().push(btnDetail.getScene());
		vc.createReportDetail(rep, lb);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnDetail.setText(lg.getDetailsString());
		
	}
}
