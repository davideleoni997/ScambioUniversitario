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
	private ImageView img1;
	
	@FXML
	private ImageView img2;
	
	@FXML
	private ImageView img3;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private TextField txtTitle;
	
	@FXML
	private Label lblSeller;
	
	@FXML
	private Label txtSeller;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private TextField txtPrice;
	
	@FXML
	private Label lblDate;
	
	@FXML
	private Label txtDate;
	
	@FXML
	private Label lblDescription;
	
	@FXML
	private TextArea txtDescription;
	
	@FXML
	private Label lblSubj;
	
	@FXML
	private TextField txtSubj;
	
	@FXML
	private Label lblCity;
	
	@FXML
	private TextField txtCity;
	
	@FXML
	private Label lblUni;
	
	@FXML
	private TextField txtUni;
	
	
	@FXML
	private RadioButton txtBook;
	
	
	@FXML
	private RadioButton txtNotes;
	
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
		basic.setTitle(txtTitle.getText());
		basic.setDesc(txtDescription.getText());
		basic.setPrice(Integer.parseInt(txtPrice.getText()));
		ib.setBasic(basic);
		Filters filter = new Filters();
		filter.setBook(txtBook.isSelected());
		filter.setNotes(txtNotes.isSelected());
		filter.setCity(txtCity.getText());
		filter.setSubject(txtSubj.getText());
		filter.setUniversity(txtUni.getText());
		ib.setFilter(filter);
		ic.modify(ib);
		vc.goBack();
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
		txtUni.setText(ib.getFilter().getUniversity());
		txtCity.setText(ib.getFilter().getCity());
		txtSubj.setText(ib.getFilter().getSubject());
		txtBook.setSelected(ib.getFilter().getBook());
		txtBook.setText(lg.getBooksString());
		txtNotes.setSelected(ib.getFilter().getNotes());
		txtNotes.setText(lg.getNotesString());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTitle.setText(lg.getTitleString());
		lblSeller.setText(lg.getSellerString());
		lblPrice.setText(lg.getPriceString());
		lblDate.setText(lg.getDateString());
		lblDescription.setText(lg.getDescriptionString());
		lblUni.setText(lg.getUniversityString());
		lblCity.setText(lg.getCityString());
		lblSubj.setText(lg.getSubjectString());
		btnBack.setText(lg.getBackString());
		btnModify.setText(lg.getModifyString());
		btnDelete.setText(lg.getDeleteInsertionString());
		
		try {
			Image img = new Image(getClass().getResourceAsStream(("No-Image-Available.png")));
			img1.setImage(img);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"Image loading",e);
		}
	}
	
	
}
