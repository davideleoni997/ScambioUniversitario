package graphic;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;

import controller.OrderController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.Property;

public class InsertionDetailController implements Initializable{

	private LanguageFactory lg;
	private ViewController vc;
	private InsertionBean ib;
	
	@FXML
	private ImageView img1;
	
	@FXML
	private ImageView img2;
	
	@FXML
	private ImageView img3;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label txtTitle;
	
	@FXML
	private Label lblSeller;
	
	@FXML
	private Label txtSeller;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private Label txtPrice;
	
	@FXML
	private Label lblDate;
	
	@FXML
	private Label txtDate;
	
	@FXML
	private Label lblDescription;
	
	@FXML
	private Label txtDescription;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnBuy;
	
	public InsertionDetailController() {
		vc = ViewController.getInstance();
		
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
			
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"InsertionDetailController",e);
		}
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}
	
	@FXML
	public void buy() {
		Property prop = new Property();
		if(prop.loadProperty("user_id").equals("0")) {
			vc.getScenes().push(btnBuy.getScene());
			vc.createLoginMenu("Buy");
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");//TODO Strings
			alert.setHeaderText("Do you really want to buy the item?");
			ButtonType buttonConf = new ButtonType("Yes");
			ButtonType buttonCanc = new ButtonType("No");
			alert.getButtonTypes().setAll(buttonConf,buttonCanc);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonConf){
				OrderController.newOrder(Integer.parseInt(prop.loadProperty("user_id")), ib.getSellerId(), ib.getTitle(), ib.getId(), ib.getPrice());
			} 
			}
	}
	
	public void setInsertion(InsertionBean ib) {
		this.ib = ib;
		if(ib.getImages().size() > 0)
		img1.setImage(ib.getImages().get(0));
		if(ib.getImages().size() > 1)
		img2.setImage(ib.getImages().get(1));
		if(ib.getImages().size() > 2)
		img3.setImage(ib.getImages().get(2));
		txtTitle.setText(ib.getTitle());
		txtSeller.setText(ib.getSeller());
		txtPrice.setText(ib.getPrice()+" Euros");
		txtDate.setText(ib.getDate().toString());
		txtDescription.setText(ib.getDesc());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTitle.setText(lg.getTitleString());
		lblSeller.setText(lg.getSellerString());
		lblPrice.setText(lg.getPriceString());
		lblDate.setText(lg.getDateString());
		lblDescription.setText(lg.getDescriptionString());
		btnBack.setText(lg.getBackString());
		btnBuy.setText(lg.getBuyString());
		
	}
}
