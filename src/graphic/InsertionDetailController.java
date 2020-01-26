package graphic;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import controller.MessageController;
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

	private static final String USER_ID = "user_id";
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
	
	//TODO Pulsante e schermata reportInsertion
	
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
		if(prop.loadProperty(USER_ID).equals("0")) {
			vc.getScenes().push(btnBuy.getScene());
			vc.createLoginMenu("Buy");
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(lg.getConfirmationString());
			alert.setHeaderText(lg.getConfirmationBuyText());
			ButtonType buttonConf = new ButtonType(lg.getYesString());
			ButtonType buttonCanc = new ButtonType(lg.getNoString());
			alert.getButtonTypes().setAll(buttonConf,buttonCanc);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonConf){
				OrderController.newOrder(Integer.parseInt(prop.loadProperty(USER_ID)), ib.getSellerId(), ib.getBasic().getTitle(), ib.getId(), ib.getBasic().getPrice());
				MessageController mc = new MessageController();
				mc.newMessage(Integer.parseInt(prop.loadProperty(USER_ID)), ib.getSellerId(), "I have bought your item :"+ ib.getBasic().getTitle());
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
		
		txtTitle.setText(ib.getBasic().getTitle());
		txtSeller.setText(ib.getSeller());
		txtPrice.setText(ib.getBasic().getPrice()+" Euros");
		txtDate.setText(ib.getBasic().getDate().toString());
		txtDescription.setText(ib.getBasic().getDesc());
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
