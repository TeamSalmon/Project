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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import projectsalmon.*;
import javafx.fxml.Initializable;

/**
 * This GUI controller is responsible for the third stage of
 * the secretary's "Assign Class to a Course" functionality.
 * In this screen the user is selects a teacher for the class
 * in the specified course from a given list of optional teachers. 
 *
 * @see SecretaryController
 * @see AssignClassToCourseController2
 * @see AssignClassToCourseController3
 * @author Elia
 */
public class AssignClassToCourseController3 implements Initializable{
		
	
	public Main myMain = Main.getInstance();
	
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
	
	
	 /**
     * Initializes the scene with a list of the optional teachers in this course. 
     */
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {
		mainMenuFX.setVisible(false);

		ArrayList<Teacher> optionalTeachers = null;
		try {
			optionalTeachers = SecretaryController.getOptionalTeachers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
    
	/**
     * Continues to a final success or error message and the end of the functionality.  
     */
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
    		exitPT.setVisible(false);
    		requestFX1.setVisible(false);   	
    		
    		textFX.setText("The class " + SecretaryController.get_chosen_class().getClassName() + 
    						   " was assigned successfully to the course " 
    						   + SecretaryController.get_chosen_course().getName() +
    						   " with the teacher " + chosen_teacher);
    	
    		textFX.setVisible(true);

    	}
 	}

    /**
     *  Return to the main menu of the user.
     */
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.assignClassToCourseEXIT(3);
    }

}
