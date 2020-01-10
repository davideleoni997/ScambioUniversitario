package graphic;
	
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		ViewController vc = ViewController.getInstance();
		vc.setPrimaryStage(primaryStage);
		vc.createLoginMenu();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
