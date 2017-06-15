package ClientGui;

import java.util.ArrayList;

import ServerClient.ClientConsole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

public class TeacherGUI
{
	Main	myMain=	Main.getInstance();
    @FXML
    private ImageView userImage;

    @FXML
    private ComboBox userControl;

    @FXML
    private ImageView courses;
    
    ObservableList<String> list;

    @FXML
    void userControl(ActionEvent event)
    {
    	
    }

    @FXML
    void viewCourses(MouseEvent event)
    {
    	
    }
    public void setComboBox()
    {
    	ArrayList<String> options = new ArrayList<String>();	
		options.add("personal info");
		options.add("logout");
		list = FXCollections.observableArrayList(options);
		userControl.setItems(list);
    }
    public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/AcademicFrame.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/ClientGui/test.css").toExternalForm());
		primaryStage.setTitle("testt");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}

}
