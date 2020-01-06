package Graphic;
	
import controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		ViewController vc = new ViewController();
		vc.setPrimaryStage(primaryStage);
		vc.createLoginMenu(vc);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
