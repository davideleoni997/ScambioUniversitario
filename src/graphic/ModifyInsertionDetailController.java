package graphic;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import controller.InsertionController;
import factory.LanguageFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.BasicInformations;
import logic.Filters;


public class ModifyInsertionDetailController implements Initializable{
	
	private LanguageFactory lg;
	private ViewController vc;
	private InsertionBean ib;
	
	@FXML
	private ImageView modImg1;
	
	@FXML
	private ImageView modImg2;
	
	@FXML
	private ImageView modImg3;
	
	@FXML
	private Label modLblTitle;
	
	@FXML
	private TextField modTxtTitle;
	
	@FXML
	private Label modLblSeller;
	
	@FXML
	private Label modTxtSeller;
	
	@FXML
	private Label modLblPrice;
	
	@FXML
	private TextField modTxtPrice;
	
	@FXML
	private Label modLblDate;
	
	@FXML
	private Label modTxtDate;
	
	@FXML
	private Label modLblDescription;
	
	@FXML
	private TextArea modTxtDescription;
	
	@FXML
	private Label modLblSubj;
	
	@FXML
	private TextField modTxtSubj;
	
	@FXML
	private Label modLblCity;
	
	@FXML
	private TextField modTxtCity;
	
	@FXML
	private Label modLblUni;
	
	@FXML
	private TextField modTxtUni;
	
	
	@FXML
	private RadioButton modTxtBook;
	
	
	@FXML
	private RadioButton modTxtNotes;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnModify;
	
	@FXML
	private Button btnDelete;
	
	public ModifyInsertionDetailController() {
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
	public void delete() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(lg.getDeleteInsertionString());
		alert.setHeaderText(lg.getDeleteConfirm());
		ButtonType buttonConf = new ButtonType(lg.getYesString());
		ButtonType buttonCanc = new ButtonType(lg.getNoString());
		alert.getButtonTypes().setAll(buttonConf,buttonCanc);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonConf){
		InsertionController ic = InsertionController.getInstance();
		ic.delete(ib.getId());
		vc.goBack();
		}
	}
	
	@FXML
	public void modify() {
		InsertionController ic = InsertionController.getInstance();
		BasicInformations basic = new BasicInformations();
		basic.setTitle(modTxtTitle.getText());
		basic.setDesc(modTxtDescription.getText());
		basic.setPrice(Integer.parseInt(modTxtPrice.getText()));
		ib.setBasic(basic);
		Filters filter = new Filters();
		filter.setBook(modTxtBook.isSelected());
		filter.setNotes(modTxtNotes.isSelected());
		filter.setCity(modTxtCity.getText());
		filter.setSubject(modTxtSubj.getText());
		filter.setUniversity(modTxtUni.getText());
		ib.setFilter(filter);
		ic.modify(ib);
		vc.goBack();
	}
	

	public void setInsertion(InsertionBean ib) {
		this.ib = ib;
		
		if(ib.getImages().size() > 0)
			modImg1.setImage(ib.getImages().get(0));
		
		if(ib.getImages().size() > 1)
			modImg2.setImage(ib.getImages().get(1));
		
		if(ib.getImages().size() > 2)
			modImg3.setImage(ib.getImages().get(2));
		
		modTxtTitle.setText(ib.getBasic().getTitle());
		modTxtSeller.setText(ib.getSeller());
		modTxtPrice.setText(ib.getBasic().getPrice()+" Euros");
		modTxtDate.setText(ib.getBasic().getDate().toString());
		modTxtDescription.setText(ib.getBasic().getDesc());
		modTxtUni.setText(ib.getFilter().getUniversity());
		modTxtCity.setText(ib.getFilter().getCity());
		modTxtSubj.setText(ib.getFilter().getSubject());
		modTxtBook.setSelected(ib.getFilter().getBook());
		modTxtBook.setText(lg.getBooksString());
		modTxtNotes.setSelected(ib.getFilter().getNotes());
		modTxtNotes.setText(lg.getNotesString());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		modLblTitle.setText(lg.getTitleString());
		modLblSeller.setText(lg.getSellerString());
		modLblPrice.setText(lg.getPriceString());
		modLblDate.setText(lg.getDateString());
		modLblDescription.setText(lg.getDescriptionString());
		modLblUni.setText(lg.getUniversityString());
		modLblCity.setText(lg.getCityString());
		modLblSubj.setText(lg.getSubjectString());
		btnBack.setText(lg.getBackString());
		btnModify.setText(lg.getModifyString());
		btnDelete.setText(lg.getDeleteInsertionString());
		
		try {
			Image img = new Image(getClass().getResourceAsStream(("No-Image-Available.png")));
			modImg1.setImage(img);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"Image loading",e);
		}
	}
	
	
}
