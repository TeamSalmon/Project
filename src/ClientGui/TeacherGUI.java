package ClientGui;

import Controllers.*;
import projectsalmon.*;
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
  

    @FXML
    void userControl(ActionEvent event)
    {
    	String value = (String) userControl.getValue();
    	if(value.equals("logout"))
    	{}	//Controllers.UserController.logout();
    	
    	else if(value.equals("personal info"))
    	{/*close current window and open the personal info window*/}
    		
    }

    @FXML
    void viewCourses(MouseEvent event)
    {
    	
    }
    public void setComboBox()
    {
		userControl.getItems().add("personal info");
		userControl.getItems().add("logout");	
    }
    public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/TeacherGUI.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/test.css").toExternalForm());
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}

}
