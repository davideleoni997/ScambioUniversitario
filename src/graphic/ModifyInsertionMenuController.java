package graphic;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import controller.InsertionController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ModifyInsertionMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Label lblInsertions;
	
	@FXML
	private ListView<Pane> listInsertions;
	
	public ModifyInsertionMenuController() {
		vc = ViewController.getInstance();
		if(System.getProperty("user.language").equalsIgnoreCase("en"))
			lg = LanguageFactory.getfactory(0);
		else
			lg = LanguageFactory.getfactory(1);
	}
	
	public void setUser(Integer id) {
		InsertionController ic = InsertionController.getInstance();
		List<InsertionBean> list = ic.myInsertions(id);
		for(InsertionBean inserzione : list) {
			try {
			//Crea adapter oggetto inserzione e assegnagli dati bean
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("ModifyInsertionAdapter.fxml"));
			Pane root = (Pane) fl.load();    //Carico fxml della scena
			ModifyInsertionAdapterController iac = fl.getController();
			iac.setData(inserzione);
			
			listInsertions.getItems().add(root);
			}
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"ModifyInsAdapt",e);
			}
		}
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setText(lg.getBackString());
		lblInsertions.setText(lg.getYourInsertionsString());
	}
}
