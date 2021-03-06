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
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import logic.Filters;
import logic.Filters.Date;



public class ResearchMenuController implements Initializable{
	private LanguageFactory lg;
	private ViewController vc;
	private String research;
	private Filters filters;
	
	@FXML
	private ListView<Pane> listSearch;
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private Label lblSearch;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private MenuButton menuFilters;
	
	@FXML
	private CustomMenuItem filterLblUni;
	
	@FXML
	private Label lblUni;
	
	@FXML
	private CustomMenuItem filterUni;
	
	@FXML
	private TextField txtUni;
	
	@FXML
	private CustomMenuItem filterLblCity;
	
	@FXML
	private Label lblCity;
	
	@FXML
	private CustomMenuItem filterCity;
	
	@FXML
	private TextField txtCity;
	
	@FXML
	private CustomMenuItem filterLblSubj;
	
	@FXML
	private Label lblSubject;
	
	@FXML
	private CustomMenuItem filterSubject;
	
	@FXML
	private TextField txtSubject;
	
	@FXML
	private CustomMenuItem filterBook;
	
	@FXML
	private RadioButton radioBook;
	
	@FXML
	private CustomMenuItem filterNotes;
	
	@FXML
	private RadioButton radioNotes;
	
	@FXML
	private CustomMenuItem filterApply;
	
	@FXML
	private Button btnApply;
	
	@FXML
	private CustomMenuItem filterClear;
	
	@FXML
	private Button btnClear;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private MenuButton menuOrder;
	
	@FXML
	private CustomMenuItem orderTimeNew;
	
	@FXML
	private RadioButton radioOrderNew;
	
	@FXML
	private CustomMenuItem orderTimeOld;
	
	@FXML
	private RadioButton radioOrderOld;
	
	@FXML
	private ToggleGroup orderTime;
	
	@FXML
	private ToggleGroup toggleDistance;
	
	@FXML
	private CustomMenuItem orderApply;
	
	@FXML
	private Button btnApplyOrder;
	
	@FXML
	private CustomMenuItem orderClear;
	
	@FXML
	private Button btnClearOrder;
	
	
	public ResearchMenuController() {
		filters = new Filters();
		vc = ViewController.getInstance();
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ResearchMenuController",e);
		}
	}
	
	
	public void setResearch(String research) {
		this.research = research;
		txtSearch.setText(research);
		search();
	}
	
	@FXML
	public void newSearch() {
		listSearch.getItems().clear();	
		this.research = txtSearch.getText();
		search();
	}

	@FXML
	public void applyFilter() {
		
		
		listSearch.getItems().clear();
		if(!txtUni.getText().isEmpty())
			filters.setUniversity(txtUni.getText());
		else
			filters.setUniversity("");
		
		if(!txtCity.getText().isEmpty())
			filters.setCity(txtCity.getText());
		else
			filters.setCity("");
		
		if(!txtSubject.getText().isEmpty())
			filters.setSubject(txtSubject.getText());
		else
			filters.setSubject("");
			
		filters.setBook(radioBook.isSelected());
		
	    filters.setNotes(radioNotes.isSelected());
		search();
	}
	
	@FXML
	public void applyOrder() {
		
		
		listSearch.getItems().clear();
		
		if(radioOrderNew.isSelected())
			filters.setDate(Date.NEW);
		else
			filters.setDate(Date.OLD);
		search();
	}
	
	@FXML
	public void clearFilters() {
		txtUni.clear();
		txtCity.clear();
		txtSubject.clear();
		radioBook.setSelected(true);
		radioNotes.setSelected(true);
	}
	
	@FXML
	public void clearOrder() {
		radioOrderNew.setSelected(true);
		radioOrderOld.setSelected(false);
	}
	
	
	public void search() {
		
		
		InsertionController ic = InsertionController.getInstance();
		List<InsertionBean> results = ic.getResearchResults(research, filters);
		update(results);
		
	}
	
	private void update(List<InsertionBean> insertionList) {
		for(InsertionBean inserzione : insertionList) {
			try {
			//Crea adapter oggetto inserzione e assegnagli dati bean
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("InsertionListAdapter.fxml"));
			Pane root = (Pane) fl.load();    //Carico fxml della scena
			InsertionListAdapterController iac = fl.getController();
			iac.setData(inserzione);
			
			listSearch.getItems().add(root);
			}
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"InsertionBeanAdapter",e);
			}
		}
	}
	
	@FXML
	public void goBack() {
		vc.goBack();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		filterLblUni.setHideOnClick(false);
		filterUni.setHideOnClick(false);
		filterLblCity.setHideOnClick(false);
		filterCity.setHideOnClick(false);
		filterLblSubj.setHideOnClick(false);
		filterSubject.setHideOnClick(false);
		filterBook.setHideOnClick(false);
		filterNotes.setHideOnClick(false);
		filterApply.setHideOnClick(false);
		filterClear.setHideOnClick(false);
		lblSearch.setText(lg.getSearchString()+":");
		btnSearch.setText(lg.getSearchString());
		btnBack.setText(lg.getBackString());
		menuFilters.setText(lg.getFiltersString());
		lblUni.setText(lg.getUniversityString());
		lblCity.setText(lg.getCityString());
		lblSubject.setText(lg.getSubjectString());
		radioBook.setText(lg.getBooksString());
		radioNotes.setText(lg.getNotesString());
		btnApply.setText(lg.getApplyString());
		btnClear.setText(lg.getClearString());
		orderTimeNew.setHideOnClick(false);
		orderTimeOld.setHideOnClick(false);
		
		orderApply.setHideOnClick(false);
		orderClear.setHideOnClick(false);
		menuOrder.setText(lg.getOrderString());
		radioOrderNew.setText(lg.getNewerString());
		radioOrderOld.setText(lg.getOlderString());
		
		btnApplyOrder.setText(lg.getApplyString());
		btnClearOrder.setText(lg.getClearString());
	}
}
