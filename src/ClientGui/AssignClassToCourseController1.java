
package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ServerClient.ClientConsole;
import controllers.SecretaryController;
import controllers.StudentsClassController;
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



public class AssignClassToCourseController1 implements Initializable{
	
	
	ArrayList<Course> courses;
	ArrayList<StudentsClass> classes;
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
    
     
    
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {			
		courses = SecretaryController.getOptionalCourses();
		classes = SecretaryController.getOptionalClasses();
		
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
			myMain.getMange().changeScene(myMain.getMange().initializationScreens(9));
		
    	}
    }
	
    
    
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.assignClassToCourseEXIT(1);
    }

}
