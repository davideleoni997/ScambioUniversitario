package graphic;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import bean.UserBean;
import controller.ReportController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import logic.Report;
import util.Property;

public class AdminMenuController {
	
	private ViewController vc;
	
	@FXML
	private Button btnLogOut;
	
	@FXML
	private Label lblUser;
	
	@FXML
	private Label txtUser;
	
	@FXML
	private ListView<Pane> listReports;
	
	public AdminMenuController() {
		vc = ViewController.getInstance();
		
		
	}
	
	public void update(UserBean lb) {
		
		txtUser.setText(lb.getUsername());
		ReportController rc = new ReportController();
		List<Report> list = rc.getReport();
		for(Report report : list) {
			try{
				FXMLLoader fl = new FXMLLoader();
			
			
				fl.setLocation(getClass().getResource("ReportAdapter.fxml"));
				Pane root = (Pane) fl.load();
				ReportAdapter ra =fl.getController();
			
				ra.setReport(report);
				ra.setUser(lb);
	
				listReports.getItems().add(root);		
			}
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"AdminMenuController",e);
			}
		}
	}
	
	@FXML
	public void logOut() {
		Property prop = new Property();
		prop.setProperty("user_id", "0");
		vc.goBack();
	}

}
