package graphic;

import javafx.application.Application;
import javafx.stage.Stage;
import util.Property;

public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		Property prop = new Property();
		prop.setProperty("user_id", "0");
		ViewController vc = ViewController.getInstance();
		vc.setPrimaryStage(primaryStage);
		vc.createMainMenu();
		primaryStage.setTitle("Scambio");
		primaryStage.show();  //Mostro schermata
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
