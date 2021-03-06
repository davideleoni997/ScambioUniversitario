package graphic;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.InsertionController;
import factory.LanguageFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import logic.BasicInformations;
import logic.Filters;
import util.Property;

public class NewInsertionMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private List<File> images;
	
	
	@FXML
	private Label lblNewInsertion;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private TextField txtTitle;
	
	@FXML
	private Label lblDescription;
	
	@FXML
	private TextArea txtDescription;
	
	@FXML
	private Label lblPrice;
	
	@FXML
	private TextField txtPrice;
	
	@FXML
	private Label lblImages;
	
	@FXML
	private Button btnUpload;
	
	@FXML
	private ImageView imgMain;
	
	@FXML
	private ImageView img2;
	
	@FXML
	private ImageView img3;
	
	@FXML
	private Button btnInsert;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private Label lblUni;
	
	@FXML
	private TextField txtUni;
	
	@FXML
	private Label lblCity;
	
	@FXML
	private TextField txtCity;
	
	@FXML
	private Label lblSubj;
	
	@FXML
	private TextField txtSubj;
	
	@FXML
	private RadioButton radioBook;
	
	@FXML
	private RadioButton radioNotes;
	
	public NewInsertionMenuController() {
		this.images = new LinkedList<>();
		vc = ViewController.getInstance();
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"NewInsertionController",e);
		}
	}
	
	@FXML
	private void goBack() {
		vc.goBack();
	}

	@FXML
	public void upload() {
		final FileChooser fc = new FileChooser();
		ExtensionFilter ef = new ExtensionFilter("Images","*.png");
		fc.getExtensionFilters().add(ef);
		this.images = fc.showOpenMultipleDialog(vc.getPrimaryStage());
		if(images != null){
			
			Runnable update = () -> {
				imgMain.setImage(new Image(images.get(0).toURI().toString()));
				imgMain.setVisible(true);
				this.images.add(images.get(0));
				
				if(images.size() >= 2) {
					
					img2.setImage(new Image(images.get(1).toURI().toString()));
					img2.setVisible(true);
					this.images.add(images.get(1));
					
				}
				if(images.size() >= 3) {
					
					img3.setImage(new Image(images.get(2).toURI().toString()));
					img3.setVisible(true);
					this.images.add(images.get(2));
				}
					
				};
			Platform.runLater(update);
			}
		
		else {
			Runnable update = () -> {
			imgMain.setVisible(false);
			img2.setVisible(false);
			img3.setVisible(false);
			
			
		};
			Platform.runLater(update);
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblNewInsertion.setText(lg.getNewInsertionString());
		lblTitle.setText(lg.getTitleString());
		lblPrice.setText(lg.getPriceString());
		lblDescription.setText(lg.getDescriptionString());
		lblImages.setText(lg.getImagesString());
		btnInsert.setText(lg.getInsertString());
		btnCancel.setText(lg.getBackString());
		lblUni.setText(lg.getUniversityString());
		lblCity.setText(lg.getCityString());
		lblSubj.setText(lg.getSubjectString());
		radioBook.setText(lg.getBooksString());
		radioNotes.setText(lg.getNotesString());
		
	}
	
	@FXML
	public void insert() {
		Property prop = new Property();
		InsertionController ic = InsertionController.getInstance();
		BasicInformations basic = new BasicInformations();
		basic.setTitle(txtTitle.getText());
		basic.setDesc(txtDescription.getText());
		if(txtPrice.getText().matches("[0-9]+"))
			basic.setPrice(Integer.parseInt(txtPrice.getText()));
		Filters filter = new Filters();
		filter.setUniversity(txtUni.getText());
		filter.setCity(txtCity.getText());
		filter.setSubject(txtSubj.getText());
		filter.setBook(radioBook.isSelected());
		filter.setNotes(radioNotes.isSelected());
		if(txtPrice.getText().matches("[0-9]+")) {
			ic.newInsertion(basic,images,Integer.parseInt(prop.loadProperty("user_id")),filter);
			vc.goBack();
		}
		else
			txtPrice.setText("Insert a proper number");
	}
	
}
