/**
 * Sample Skeleton for 'secondStage.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ServerClient.ClientConsole;
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
import projectsalmon.*;
import projectsalmon.StudentsClass;
import javafx.fxml.Initializable;


public class AssignClassToCourseController1 implements Initializable{
	
	
	//ArrayList<Student> students = scc.getOptionalStusents();
	ArrayList<Course> courses;
	ArrayList<StudentsClass> classes;
	ArrayList<String> courses_names;
	ArrayList<String> classes_names;

	ArrayList<String> rightstudents_names = new ArrayList<String>();
	ArrayList<String> rightselected_names = new ArrayList<String>();
	
	
	Main myMain = Main.getInstance();

	@FXML // fx:id="paneFX"
    private Pane paneFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="requestFX1"
    private Label requestFX1; // Value injected by FXMLLoader
	
	@FXML // fx:id="requestFX2"
    private Label requestFX2; // Value injected by FXMLLoader
	
	@FXML // fx:id="courseFX"
    private ComboBox<String> courseFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="classFX"
    private ComboBox<String> classFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="continuePT"
    private Button continuePT; // Value injected by FXMLLoader

    @FXML // fx:id="exitPT"
    private Button exitPT; // Value injected by FXMLLoader

  
    
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
			courses_names.add(sclass.getClassName());
    	}
		
		courseFX.setItems(FXCollections.observableArrayList(courses_names));
		classFX.setItems(FXCollections.observableArrayList(classes_names));

    }
    
    

    @FXML void nextFrame(ActionEvent event) throws IOException 
    {
    	String chosen_course = courseFX.getValue();
    	String chosen_class = classFX.getValue();

    	
    	ArrayList<Student> chosen_students = new ArrayList<Student>();
    	// Get the chosen students and pass them to 'scc'
    	for(Student student: students)
    	{
    		String detailsOfStudent = student.getFirst_name() + " " + student.getLast_name() + " " + student.getId();
    		if( rightstudents_names.contains(detailsOfStudent))
    			chosen_students.add(student);
    	}
    	StudentsClass result = StudentsClassController.createNewClass(chosen_students);
    	
    	leftLabelFX.setVisible(false);
    	rightLabelFX.setVisible(false);
    	leftListFX.setVisible(false);
    	rightListFX.setVisible(false);
    	requestFX2.setVisible(false);
    	moveFX.setVisible(false);
    	continuePT.setVisible(false);

    	 if(result != null)
    	 {
    		 requestFX1.setText("The class " + result.getClassName() + " (ID: " + result.getClassId() + ") " + "was created successfully.");
    	 }
    	 else
    	 {
    		 requestFX1.setText("Couldn't create the new class.");

    	 }
    	
    	//((Node)event.getSource()).getScene().getWindow().hide();
		//myMain.getMange().initializationScreens(8);
		//myMain.getMange().changesence(3);
 	}
	
    
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	 myMain.getMange().changesence(0);
    }

}
