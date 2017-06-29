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
import projectsalmon.*;
import javafx.fxml.Initializable;

/**
 * This GUI controller is responsible for the second stage of
 * the secretary's "Assign Class to a Course" functionality.
 * In this screen the user is informed about the students that
 * could not be assigned to the course due to lack of preconditions.
 *
 * @see SecretaryController
 * @see AssignClassToCourseController1
 * @see AssignClassToCourseController3
 * @author Elia
 */
public class AssignClassToCourseController2 implements Initializable {

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

  
    /**
     * Initializes the scene with lists of misfit students or a message
     * if all the students has the required preconditions.   
     */
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {
		ArrayList<Student> misfitStudents = null;
		try {
			misfitStudents = SecretaryController.getMisfitStudents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( misfitStudents.size() > 0 )
		{
			ArrayList<String> misfitNames = new ArrayList<String>(); 
			for(Student student : misfitStudents)
			{
				misfitNames.add( student.getFirst_name() + " " + student.getLast_name() + " " + student.getId() );
			}
			
			leftListFX.setItems(FXCollections.observableArrayList(misfitNames)); 
		}

		if (misfitStudents.size() > 0) {
			ArrayList<String> misfitNames = new ArrayList<String>();
			for (Student student : misfitStudents) {
				misfitNames.add(student.getFirst_name() + " " + student.getLast_name() + " " + student.getId());
			}

			leftListFX.setItems(FXCollections.observableArrayList(misfitNames));
		} else {
			leftListFX.setVisible(false);
			leftLabelFX.setVisible(false);
			requestFX1.setText("All the students in the class has the course's preconditions.");
		}

	}

	
	/**
     * Continues to AssignClassToCourseController3.  
     */
	@FXML
	void nextFrame(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(11));
	}

	
	  /**
     *  Return to the main menu of the user.
     */
	@FXML
	void exit(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		SecretaryController.assignClassToCourseEXIT(2);
	}

}
