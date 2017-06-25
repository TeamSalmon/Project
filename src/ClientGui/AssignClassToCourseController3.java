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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import projectsalmon.*;
import javafx.fxml.Initializable;


public class AssignClassToCourseController3 implements Initializable{
		
	
	Main myMain = Main.getInstance();
	
	@FXML
	private Pane paneFX;

	@FXML
	private Button continuePT;

	@FXML	   
	private Label requestFX1;

	@FXML
	private Button exitPT;
	 
	@FXML
	private Button mainMenuFX;

	@FXML
	private Label leftLabelFX;

	@FXML
	private ComboBox<String> teacherFX;
  
	@FXML
	private Text textFX;
	
	 
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {
		mainMenuFX.setVisible(false);

		ArrayList<Teacher>  optionalTeachers = SecretaryController.getOptionalTeachers();
		
		if ( optionalTeachers.size() > 0 )
		{
			ArrayList<String> teachersNames = new ArrayList<String>(); 
			for(Teacher teacher : optionalTeachers)
			{
				teachersNames.add( teacher.getFirst_name() + " " + teacher.getLast_name() + " " + teacher.getId() );
			}
			
			teacherFX.setItems(FXCollections.observableArrayList(teachersNames)); 
		}
		else
		{
			teacherFX.setVisible(false);
			leftLabelFX.setVisible(false);
			continuePT.setVisible(false);
			mainMenuFX.setVisible(true);
			requestFX1.setText("Couldn't find avaliable teachers for this class and course.");
		}
		
    }
    
    

    @FXML void nextFrame(ActionEvent event) throws IOException 
    {	
    	
    	String chosen_teacher = teacherFX.getValue();

    	if (chosen_teacher == null)
    	{
    		requestFX1.setText("Please select a teacher and try again.");
    	}
    	
    	else
    	{		
    		// actually update DB with the new details
        	SecretaryController.setSave_changes(true);
        	
    		SecretaryController.updateChosenTeacher(chosen_teacher);
    	
    		teacherFX.setVisible(false);
			leftLabelFX.setVisible(false);
			continuePT.setVisible(false);
    		mainMenuFX.setVisible(true);
    		requestFX1.setVisible(false);   	
    		
    		textFX.setText("The class " + SecretaryController.get_chosen_class().getClassName() + 
    						   " was assigned successfully to the course " 
    						   + SecretaryController.get_chosen_course().getName() +
    						   " with the teacher " + chosen_teacher);
    	
    		textFX.setVisible(true);

    	}
    	
 	}
	
    
    @FXML void mainMenu(ActionEvent event) throws IOException
    {
     	// go to main menu of secretary
    	//((Node)event.getSource()).getScene().getWindow().hide();
		//myMain.getMange().initializationScreens(9);
		//myMain.getMange().changesence(2);
    }

    
    
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	 myMain.getMange().changesence(0);
    }

}
