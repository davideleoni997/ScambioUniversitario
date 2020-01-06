package controller;

import bean.LoginBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Order;
public class ViewController {

	private Stage primaryStage;
	
	public ViewController() {
		this.primaryStage = null;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void createLoginMenu(ViewController vc) {
		try {
			VBox root = new VBox();
			
			Scene scene = new Scene(root,400,800);
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
						System.out.println("credenziali ok");
						System.out.println(lb.getNome() + lb.getCognome() + lb.getCompany());
					
					vc.createOrderMenu(lb.getUsername(),vc);
						
					
					}
					else
						System.out.print("Errore credenziali");
				}
			});
			
			root.getChildren().addAll(lbluser,tfuser,lblpsw,tfpsw,btnsub);
			primaryStage.show();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void createOrderMenu(String user,ViewController vc) {
		try {
			VBox root = new VBox();
			
			Scene scene = new Scene(root,400,800);
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
					vc.createOrderDetailMenu(orderId);	
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
			
			
			primaryStage.setScene(scene);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createOrderDetailMenu(Integer id) {
		try {
		VBox root = new VBox();
		Scene scene = new Scene(root,400,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Order order = OrderController.getOrderDetail(id);
		
		String out= "Nome: "+order.getItem().getNome()+" Compratore: "+order.getBuyer();
		
		TextField tfOrderInfo = new TextField(out);
		tfOrderInfo.setEditable(false);
		
		root.getChildren().add(tfOrderInfo);
		primaryStage.setScene(scene);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
