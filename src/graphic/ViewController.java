package graphic;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.UserBean;
import controller.LoginController;
import controller.MessageController;
import controller.OrderController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Message;
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
			
	
		VBox root = new VBox();
		
		Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Order order = OrderController.getOrderDetail(id);
		
		String out= "Nome: "+order.getItem().getNome()+" Compratore: "+order.getBuyer();
		
		TextField tfOrderInfo = new TextField(out);
		tfOrderInfo.setEditable(false);
		
		Button btnBack = new Button("back");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				vc.goBack();	
			}
		});
		
		root.getChildren().addAll(tfOrderInfo,btnBack);
		primaryStage.setScene(scene);
		
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
		}
		
	}
	
	public void createProfileMenu(UserBean lb) {
		try {
			VBox root = new VBox();
			Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			if(!lb.getCompany()) {
			Label lblName = new Label("Name:");
			TextField tfName = new TextField(lb.getNome());
			tfName.setEditable(false);
			Label lblCognome = new Label("Cognome:");
			TextField tfCognome = new TextField(lb.getCognome());
			tfCognome.setEditable(false);
			
			Button btnOrders = new Button("View orders");
			btnOrders.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
						scenes.add(scene);
						vc.createOrderMenu(lb.getUsername());
				}
			});
			
			Button btnModify = new Button("Modify Profile");
			btnModify.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
						scenes.add(scene);
						vc.createModifyMenu(lb);
				}
			});
			
			root.getChildren().addAll(lblName,tfName,lblCognome,tfCognome,btnOrders,btnModify);
			
			}
			else {
			Label lblName = new Label("Name:");
			TextField tfName = new TextField(lb.getNome());
			tfName.setEditable(false);
			
			Button btnOrders = new Button("View orders");
			btnOrders.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
						scenes.add(scene);
						vc.createOrderMenu(lb.getUsername());
				}
			});
			
			root.getChildren().addAll(lblName,tfName,btnOrders);
			}
			
			Button btnMessage = new Button("Messages");
			btnMessage.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					vc.createMessageMenu(lb);	
				}
			});
			
			Button btnBack = new Button("LogOut");
			btnBack.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					vc.goBack();	
				}
			});
			
			root.getChildren().addAll(btnMessage,btnBack);
			primaryStage.setScene(scene);
			
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
			}
	}
	
	public void createModifyMenu(UserBean lb) {
		try {
			
			LoginController lc = LoginController.getInstance();
			VBox root = new VBox();
			
			Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Label lblName = new Label("Name:");
			TextField tfName = new TextField(lb.getNome());
			Label lblCognome = new Label("Surname:");
			TextField tfCognome = new TextField(lb.getCognome());
			Label lblUsername = new Label("Username:");
			TextField tfUsername = new TextField(lb.getUsername());
			Label lblOldPassword = new Label("Insert Old psw");
			TextField tfOldPassword = new TextField("");
			Label lblNewPassword = new Label("Insert New psw");
			TextField tfNewPassword = new TextField("");
			
			Button btnSubmit = new Button("Submit");
			btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
				
					//if(lb.getPassword() == tfOldPassword.getText()) {
					lb.setNome(tfName.getText());
					lb.setCognome(tfCognome.getText());
					lb.setUsername(tfUsername.getText());
					lb.setPassword(tfNewPassword.getText());
					if(lc.updateInfo(lb)) {
						scenes.pop();
						vc.createProfileMenu(lb);
					}
					else System.out.println("Error");
					
					}
					
				//}
			});
			
			
			Button btnBack = new Button("Back");
			btnBack.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					vc.goBack();	
				}
			});
			
			root.getChildren().addAll(lblName,tfName,lblCognome,tfCognome,lblUsername,tfUsername,lblOldPassword,tfOldPassword,lblNewPassword,tfNewPassword,btnSubmit,btnBack);
			primaryStage.setScene(scene);
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
			}
			
	}
	
	public void createMessageMenu(UserBean lb) {
		try {	
			
			ScrollPane scrlRoot = new ScrollPane();
			VBox root = new VBox();
			scrlRoot.setContent(root);
			
			Scene scene = new Scene(scrlRoot,primaryStage.getWidth(),primaryStage.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			TextField tfMessageInfo;
			Label lblMessages;			
			Button btnMessageDetail;
			lblMessages = new Label("Messages:");
						
			//System.out.println(iC.newInsertionMockup());			
			Message list[] = null;
			
			root.getChildren().add(lblMessages);
			
			MessageController mc = new MessageController();
			
			list = 	mc.getMessageList(lb);	
			if(list != null)
			for(int i=0; i<list.length;i++)
			{
				if(list[i]!=null) {
				tfMessageInfo= new TextField();
				tfMessageInfo.setText(""+list[i].getFrom());		
				tfMessageInfo.setEditable(false);
				root.getChildren().add(tfMessageInfo);
				
				btnMessageDetail = new Button("details");
				final int messageId = list[i].getFrom();
				btnMessageDetail.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					scenes.add(scene);
					vc.createMessageDetailMenu(lb,messageId);	
					
				}
			});
				root.getChildren().add(btnMessageDetail);
			}
			}
			
			else {
				tfMessageInfo= new TextField();
				tfMessageInfo.setText("No Orders");
				tfMessageInfo.setEditable(false);
				root.getChildren().add(tfMessageInfo);
			}
			
			Button btnBack = new Button("back");
			btnBack.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					vc.goBack();	
				}
			});
			
			root.getChildren().add(btnBack);
			
			
			primaryStage.setScene(scene);
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
		}
	}
		
	public void createMessageDetailMenu(UserBean lb,Integer id) {
		VBox root = new VBox();
		
		Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Message conv[] = null;
		MessageController mc = new MessageController();
		conv = mc.getConversation(id);
		
		if(conv!=null)
		for(int i=0;i<conv.length;i++) {
			if(conv[i]!=null) {
			Label lbSender = new Label("From : "+ conv[i].getFrom());
			TextField tfMessage = new TextField(conv[i].getDesc());
			tfMessage.setEditable(false);
			
			root.getChildren().addAll(lbSender,tfMessage);
			}
		}
		Label lblNew = new Label("New message:");
		TextField tfNewMessage = new TextField("");
		
		Button btnSubmit = new Button("Submit");
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
			MessageController mc = new MessageController();
			mc.newMessage(lb.getId(), id, tfNewMessage.getText());
			}
		});
		
		Button btnBack = new Button("back");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				vc.goBack();	
			}
		});
		
		root.getChildren().addAll(lblNew,tfNewMessage,btnSubmit,btnBack);
		
		
		primaryStage.setScene(scene);
		
	}
	
	public void goBack() {
		this.primaryStage.setScene(scenes.pop());
	}
	
}
