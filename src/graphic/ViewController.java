package graphic;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;

import controller.OrderController;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import logic.Order;

public class ViewController {
	private static ViewController vc = null;
	private Stack<Scene> scenes;
	public Stack<Scene> getScenes() {
		return scenes;
	}

	private Stage primaryStage;
	
	protected ViewController() {
		ViewController.vc = this;
		this.primaryStage = null;
		scenes = new Stack<Scene>();
	}
	
	public static ViewController getInstance() {
		if(vc==null)
			vc = new ViewController();
		
			return vc;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void createMainMenu() {
		
	}

	public void createLoginMenu() { //Metodo creazione schermata login
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
		
			fl.setLocation(getClass().getResource("LoginMenu.fxml"));
			Pane root = (Pane) fl.load();    //Carico fxml della scena
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 
			
			primaryStage.setTitle("Scambio");
			primaryStage.setScene(scene);  //Setto stage
			
			
			primaryStage.show();  //Mostro schermata
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:Login",e);
		}
		
	}
	
	public void createOrderMenu(String user) {
		try {
			
			FXMLLoader fl = new FXMLLoader(); //Creo loader
			
			fl.setLocation(getClass().getResource("OrderMenu.fxml"));
			Pane root = (Pane) fl.load(); //Carico fxml scena
			OrderMenuViewController omvc = fl.getController(); //Prendo controller scena
			omvc.updateOrders(user); //Aggiorno i miei ordini
			Scene scene = new Scene(root); //nuova scena
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:CreateOrder",e);
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 
			primaryStage.setScene(scene);
		
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 
			primaryStage.setScene(scene);
			
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 
			primaryStage.setScene(scene);
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
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
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
	}
	catch(Exception e) {
		Logger.getGlobal().log(Level.WARNING,"ViewController:MessageDetail",e);
	}
	}
	
	public void goBack() {
		this.primaryStage.setScene(scenes.pop());
	}
	
}
