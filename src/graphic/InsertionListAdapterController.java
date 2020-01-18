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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import bean.InsertionBean;

public class InsertionListAdapterController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private InsertionBean ib;
	
	@FXML
	private ImageView imgMain;
	@FXML
	private Label lblTitle;
	@FXML
	private Label txtTitle;
	@FXML
	private Label lblPrice;
	@FXML
	private Label txtPrice;
	@FXML
	private Label lblSeller;
	@FXML
	private Label txtSeller;
	@FXML
	private Label lblDate;
	@FXML
	private Label txtDate;
	@FXML
	private Button btnDetails;
	
	public InsertionListAdapterController() {
		vc = ViewController.getInstance();
		
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
			
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"InsertionAdapterController",e);
		}
	}
	
	public void setData(InsertionBean ib) {
		this.ib = ib;
		txtTitle.setText(ib.getTitle());
		txtPrice.setText(ib.getPrice() + " Euro");
		txtSeller.setText(ib.getSeller());
		txtDate.setText(ib.getDate().toString());
		imgMain.setImage(ib.getImage1());
	}
	
	@FXML
	public void details() {
		vc.getScenes().push(btnDetails.getScene());
		vc.createInsertionDetailMenu(ib);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTitle.setText(lg.getTitleString());
		lblPrice.setText(lg.getPriceString());
		lblSeller.setText(lg.getSellerString());
		lblDate.setText(lg.getDateString());
		btnDetails.setText(lg.getDetailsString());
		try {
		Image img = new Image(getClass().getResourceAsStream(("No-Image-Available.png")));
		imgMain.setImage(img);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"Image loading",e);
		}
	}
}
