package graphic;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ModifyInsertionAdapterController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private InsertionBean ib;
	
	@FXML
	private ImageView imgMain;
	@FXML
	private Label modLblTitle;
	@FXML
	private Label modTxtTitle;
	@FXML
	private Label modLblPrice;
	@FXML
	private Label modTxtPrice;
	@FXML
	private Label modLblSeller;
	@FXML
	private Label modTxtSeller;
	@FXML
	private Label modLblDate;
	@FXML
	private Label modTxtDate;
	@FXML
	private Button modBtnDetails;
	
	public ModifyInsertionAdapterController() {
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
	
	@FXML
	public void details() {
		vc.getScenes().push(modBtnDetails.getScene());
		vc.createModifyInsertionDetailMenu(ib);
	}
	
	public void setData(InsertionBean ib) {
		this.ib = ib;
		modTxtTitle.setText(ib.getBasic().getTitle());
		modTxtPrice.setText(ib.getBasic().getPrice() + "Euros");
		modTxtSeller.setText(ib.getSeller());
		modTxtDate.setText(ib.getBasic().getDate().toString());
		if(ib.getImages().size() > 0)
			imgMain.setImage(ib.getImages().get(0));
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		modLblTitle.setText(lg.getTitleString());
		modLblPrice.setText(lg.getPriceString());
		modLblSeller.setText(lg.getSellerString());
		modLblDate.setText(lg.getDateString());
		modBtnDetails.setText(lg.getModifyString());
		try {
			Image img = new Image(getClass().getResourceAsStream(("No-Image-Available.png")));
			imgMain.setImage(img);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"Image loading",e);
		}
	}
}
