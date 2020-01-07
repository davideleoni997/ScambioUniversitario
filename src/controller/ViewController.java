package controller;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.LoginBean;
import dao.MessageDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Message;
import logic.Order;
public class ViewController {
	private Stack<Scene> scenes;
	private Stage primaryStage;
	
	public ViewController() {
		this.primaryStage = null;
		scenes = new Stack<Scene>();
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void createLoginMenu(ViewController vc) { //Metodo creazione schermata login
		try {
			VBox root = new VBox(); //Nuovo box verticale
			
			Scene scene = new Scene(root,300,400); //nuova scena
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Scambio");
			primaryStage.setScene(scene);
			
			
			Label lbluser;
			Label lblpsw;
			TextField tfuser;
			TextField tfpsw;
			Button btnsub;
			
			lbluser=new Label("Username");
			lblpsw=new Label("Password");
			tfuser= new TextField("my user");
			tfpsw= new TextField("my pass");
			btnsub = new Button("Submit");
			btnsub.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					LoginBean lb = new LoginBean();
					lb.setUsername(tfuser.getText());
					lb.setPassword(tfpsw.getText());
					if(lb.validate("user")) {
					
					scenes.add(scene);
					vc.createProfileMenu(lb, vc);
					
					
					}
					else
						System.out.print("Errore credenziali");
				}
			});
			
			root.getChildren().addAll(lbluser,tfuser,lblpsw,tfpsw,btnsub);
			primaryStage.show();  //Mostro schermata
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"ViewController:",e);
		}
		
	}
	
	public void createOrderMenu(String user,ViewController vc) {
		try {
			ScrollPane scrlRoot = new ScrollPane();
			VBox root = new VBox();
			scrlRoot.setContent(root);
			
			Scene scene = new Scene(scrlRoot,primaryStage.getWidth(),primaryStage.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			TextField tfOrderInfo;
			Label lblOrders;			
			Button btnOrderDetail;
			lblOrders = new Label("Orders:");
							
						
			OrderController oc = new OrderController(user);
						
			//System.out.println(iC.newInsertionMockup());			
			Order list[] = null;
			
			root.getChildren().add(lblOrders);
			
			list = oc.getOrdersInfo(user);
			
			if(list != null)
			for(int i=0; i<list.length;i++)
			{
				if(list[i]!=null) {
				tfOrderInfo= new TextField();
				tfOrderInfo.setText(list[i].getItem().getNome());		
				tfOrderInfo.setEditable(false);
				root.getChildren().add(tfOrderInfo);
				
				btnOrderDetail = new Button("details");
				final int orderId = list[i].getId();
				btnOrderDetail.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					scenes.add(scene);
					vc.createOrderDetailMenu(orderId,vc);	
					
				}
			});
				root.getChildren().add(btnOrderDetail);
			}
			}
			
			else {
				tfOrderInfo= new TextField();
				tfOrderInfo.setText("No Orders");
				root.getChildren().add(tfOrderInfo);
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
	
	public void createOrderDetailMenu(Integer id,ViewController vc) {
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
	
	public void createProfileMenu(LoginBean lb,ViewController vc) {
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
						vc.createOrderMenu(lb.getUsername(), vc);
				}
			});
			
			Button btnModify = new Button("Modify Profile");
			btnModify.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
						scenes.add(scene);
						vc.createModifyMenu(lb,vc);
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
						vc.createOrderMenu(lb.getUsername(), vc);
				}
			});
			
			root.getChildren().addAll(lblName,tfName,btnOrders);
			}
			
			Button btnMessage = new Button("Messages");
			btnMessage.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent event) {
					vc.createMessageMenu(lb,vc);	
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
	
	public void createModifyMenu(LoginBean lb,ViewController vc) {
		try {
			
			
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
					if(lb.updateInfo()) {
						scenes.pop();
						vc.createProfileMenu(lb, vc);
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
	
	public void createMessageMenu(LoginBean lb, ViewController vc) {
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
			
			list = MessageDao.messageList(lb.getId());			
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
					vc.createMessageDetailMenu(lb,messageId,vc);	
					
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
		
	public void createMessageDetailMenu(LoginBean lb,Integer id,ViewController vc) {
		VBox root = new VBox();
		
		Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Message conv[] = null;
		conv = MessageDao.conversation(id);
		
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
			MessageDao.newMessage(lb.getId(), id, tfNewMessage.getText());
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
