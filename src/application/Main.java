package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			  URL url = getClass().getResource("ServerGUI.fxml");
			    AnchorPane pane = FXMLLoader.load( url );
			    Scene scene = new Scene( pane ); 
			    primaryStage.setTitle( "Server" );
			    primaryStage.setScene(scene);
			    primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
