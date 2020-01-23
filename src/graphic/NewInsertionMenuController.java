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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.Property;

public class NewInsertionMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private LinkedList<File> images;
	
	
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
		List<File> images = fc.showOpenMultipleDialog(vc.getPrimaryStage());
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
		lblNewInsertion.setText("New Insertion"); //TODO Strings
		lblTitle.setText(lg.getTitleString());
		lblPrice.setText(lg.getPriceString());
		lblDescription.setText(lg.getDescriptionString());
		lblImages.setText("Images");
		btnInsert.setText("Insert");
		btnCancel.setText(lg.getBackString());
		
	}
	
	@FXML
	public void insert() {
		Property prop = new Property();
		InsertionController ic = new InsertionController();
		ic.newInsertion(txtTitle.getText(), txtDescription.getText(), txtPrice.getText(),images,Integer.parseInt(prop.loadProperty("user_id")));
		vc.goBack();
	}
	
}
