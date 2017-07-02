
package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ServerClient.ClientConsole;
import controllers.SecretaryController;
import javafx.beans.value.ObservableStringValue;
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
import projectsalmon.StudentsClass;
import javafx.fxml.Initializable;

/**
 * This GUI controller is responsible for the first stage of
 * the secretary's "Assign Class to a Course" functionality.
 * In this screen the user selects a chosen StudentsClass and a Course to assign it to. 
 *
 * @see SecretaryController
 * @see AssignClassToCourseController2
 * @see AssignClassToCourseController3
 * @author Elia
 */

public class AssignClassToCourseController1 implements Initializable{
	
	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<StudentsClass> classes = new ArrayList<StudentsClass>();
	ArrayList<String> courses_names = new ArrayList<String>();
	ArrayList<String> classes_names = new ArrayList<String>();
	
	Main myMain = Main.getInstance();

	
	@FXML
    private Pane paneFX;

    @FXML
    private Button continuePT;

    @FXML
    private Label requestFX1;

    @FXML
    private ComboBox<String> courseFX;

    @FXML
    private Button exitPT;

    @FXML
    private ComboBox<String> classFX;
    
     
    /**
     * Initializes the scene with lists of optional classes and courses.   
     */
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {			
		try {
			courses = SecretaryController.getOptionalCourses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			classes = SecretaryController.getOptionalClasses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Course course : courses)
    	{
			courses_names.add(course.getName());
    	}
		
		for (StudentsClass sclass : classes)
    	{
			classes_names.add(sclass.getClassName());
    	}
		
		ObservableList<String> observCoursesNames = FXCollections.observableArrayList(courses_names); 
		ObservableList<String> observClassesNames = FXCollections.observableArrayList(classes_names);
   
		courseFX.setItems(observCoursesNames);
		classFX.setItems(observClassesNames);

    }
    
	
	/**
     * Passes the user's choice of class and course to SecretaryController 
     * and moves to AssignClassToCourseController2.  
     */
    @FXML void nextFrame(ActionEvent event) throws IOException 
    {
    	String chosen_course = courseFX.getValue();
    	String chosen_class = classFX.getValue();

    	if (chosen_course == null || chosen_class == null)
    	{
    		requestFX1.setText("Please fill all the fields and try again.");
    	}
    	
    	else{
	    	SecretaryController.updateChosenCourseAndClass(chosen_course, chosen_class);
	    	
	    	((Node)event.getSource()).getScene().getWindow().hide();
			myMain.getMange().changeScene(myMain.getMange().initializationScreens(10));
		
    	}
    }
	
    
    /**
     *  Return to the main menu of the user.
     */
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.assignClassToCourseEXIT(1);
    }

}
