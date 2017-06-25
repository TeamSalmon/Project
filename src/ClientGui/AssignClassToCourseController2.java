/**
 * Sample Skeleton for 'secondStage.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ServerClient.ClientConsole;
import controllers.SecretaryController;
import controllers.StudentsClassController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import projectsalmon.*;
import javafx.fxml.Initializable;


public class AssignClassToCourseController2 implements Initializable{
	
		
	
	Main myMain = Main.getInstance();

	@FXML
    private Pane paneFX;

    @FXML
    private Button continuePT;

    @FXML
    private ListView<String> leftListFX;

    @FXML
    private Label requestFX2;

    @FXML
    private Label requestFX1;

    @FXML
    private Button exitPT;

    @FXML
    private Label leftLabelFX;

  
    
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {
		ArrayList<Student>  misfitStudents = SecretaryController.getMisfitStudents();
		
		if ( misfitStudents.size() > 0 )
		{
			ArrayList<String> misfitNames = new ArrayList<String>(); 
			for(Student student : misfitStudents)
			{
				misfitNames.add( student.getFirst_name() + " " + student.getLast_name() + " " + student.getId() );
			}
			
			leftListFX.setItems(FXCollections.observableArrayList(misfitNames)); 
		}
		else
		{
			leftListFX.setVisible(false);
			leftLabelFX.setVisible(false);
			requestFX1.setText("All the students in the calss has the course's preconditions.");
		}
		
    }
    

    @FXML void nextFrame(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(10));
 	}
	
    
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.assignClassToCourseEXIT(2);
    }

}