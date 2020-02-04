package graphic;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import bean.UserBean;

import controller.OrderController;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import logic.Order;
import logic.Report;
import util.Property;

public class ViewController {
	private static final String ERROR_CLASS = "ViewController:";
	private static final String CSSPATH = "application.css";
	private static ViewController vc = null;
	private Deque<Scene> scenes;
	public Deque<Scene> getScenes() {
		return scenes;
	}

	private Stage primaryStage;
	
	protected ViewController() {
		this.primaryStage = null;
		scenes = new LinkedList<>();
	}
	
	public static ViewController getInstance() {
		if(vc==null)
			vc = new ViewController();
		
			return vc;
	}
	
	public void changeLanguage(int lang) { //Metodo cambio lingua
		if(lang ==0) {
			Locale.setDefault(new Locale("en"));
			System.setProperty("user.language", "en");
		}
		else
		{
			Locale.setDefault(new Locale("it"));
			System.setProperty("user.language", "it");
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void createMainMenu() { //metodo caricamento main menu
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("MainMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}
	
	public void createRegisterMenu() { //Metodo caricamento registrazione
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("RegisterMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}

	public void createLoginMenu(String dispatch) { //Metodo creazione schermata login
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("LoginMenu.fxml"));
			Pane root = (Pane) fl.load();    //Carico fxml della scena
			LoginViewController lvc = fl.getController();
			lvc.setDisp(dispatch);
			
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 

			primaryStage.setScene(scene);  //Setto stage
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
		
	}
	
	public void createResearchMenu(String text) {
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("ResearchMenu.fxml"));
			Pane root = (Pane) fl.load();    //Carico fxml della scena
			ResearchMenuController rmc = fl.getController();
			rmc.setResearch(text);
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 

			primaryStage.setScene(scene);  //Setto stage
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
		
	}
	
	public void createOrderMenu(String user) { //Metodo caricamento schermata lista ordini
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("OrderMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			OrderMenuViewController omvc = fl.getController(); //Prendo controller scena
			omvc.updateOrders(user); //Aggiorno i miei ordini
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}
	
	public void createOrderDetailMenu(Integer id) {
	try {
			
		 
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("OrderDetail.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			OrderDetailController odc = fl.getController(); //Prendo controller scena
			
			Order order = OrderController.getOrderDetail(id);
			odc.setOrder(order);
			odc.update();
			
			//Aggiorno ordine
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 
			primaryStage.setScene(scene);
		
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
		
	}
	
	public void createProfileMenu(UserBean lb) {
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			fl.setLocation(getClass().getResource("ProfileMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			ProfileMenuController pmc = fl.getController(); //Prendo controller scena
			
			pmc.setUser(lb);
			//Aggiorno ordine
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			 
			primaryStage.setScene(scene);
			
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
			}
	}
	
	public void createModifyMenu(UserBean lb) {
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("ModifyProfile.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			
			ModifyProfileViewController mpvc = fl.getController();
			mpvc.setLb(lb);
			
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		 
			primaryStage.setScene(scene);
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
			}
			
	}
	
	public void createMessageMenu(UserBean lb) {
		try {	
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("MessageMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			
			MessageMenuController mmc = fl.getController();
			mmc.update(lb);
			
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}
		
	public void createMessageDetailMenu(UserBean lb,Integer id) {
	try {	
		FXMLLoader fl = new FXMLLoader(); //Creo loader
		
		fl.setLocation(getClass().getResource("MessageDetail.fxml"));
		Pane root = (Pane) fl.load(); //Carico fxml scena
		
		MessageDetailController mmc = fl.getController();
		mmc.update(lb,id);
		
		Scene scene = new Scene(root); //nuova scena
		scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
		primaryStage.setScene(scene);
		
	}
	catch(Exception e) {
		Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
	}
	}
	
	public void goBack() {
		this.primaryStage.setScene(scenes.pop());
	}

	public void createInsertionDetailMenu(InsertionBean ib) {
		try {
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("InsertionDetail.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
		
			InsertionDetailController idc = fl.getController();
			idc.setInsertion(ib);
		
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			primaryStage.setScene(scene);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}

	public void createNewInsertionMenu() {
		Property prop = new Property();
		if(prop.loadProperty("user_id").equalsIgnoreCase("0")) {
			vc.createLoginMenu("NewInsertion");
		}
		else {
			try {
				FXMLLoader fl = new FXMLLoader(); //Creo loader
			
				fl.setLocation(getClass().getResource("NewInsertionMenu.fxml"));
				Pane root = (Pane) fl.load(); //Carico fxml scena
			
				Scene scene = new Scene(root); //nuova scena
				scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
				primaryStage.setScene(scene);
			}
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
			}
		}
		   
	}

	public void createAdminMenu(UserBean lb) {
		try {
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("AdminMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			AdminMenuController amc = fl.getController();
			amc.update(lb);
		
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			primaryStage.setScene(scene);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}

	public void createReportDetail(Report rep, UserBean lb) {
		try {
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("ReportDetail.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			ReportDetailController rdc = fl.getController();
			rdc.setReport(rep);
			rdc.setUser(lb);
		
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			primaryStage.setScene(scene);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}

	public void createNewReportMenu(Integer id) {
		try {
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("NewReport.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			NewReportController rdc = fl.getController();
			rdc.setInfo(id);
		
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			primaryStage.setScene(scene);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}

	public void createModifyInsertionMenu(Integer id) {
		try {
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("ModifyInsertionMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			ModifyInsertionMenuController mimc = fl.getController();
			mimc.setUser(id);
		
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			primaryStage.setScene(scene);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
		
	}

	public void createModifyInsertionDetailMenu(InsertionBean ib) {
		try {
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("ModifyInsertionDetailMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			ModifyInsertionDetailController mimc = fl.getController();
			mimc.setInsertion(ib);
		
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource(CSSPATH).toExternalForm());
			primaryStage.setScene(scene);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,ERROR_CLASS,e);
		}
	}

	
	
}
