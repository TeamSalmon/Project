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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import projectsalmon.Student;
import projectsalmon.StudentsClass;
import javafx.fxml.Initializable;


public class DefineClass2Controller implements Initializable{
	
	
	//ArrayList<Student> students = scc.getOptionalStusents();
	ArrayList<Student> students;
	ArrayList<String> details;
	ObservableList<String> leftstudents_names;
	ObservableList<String> leftselected_names;

	ArrayList<String> rightstudents_names = new ArrayList<String>();
	ArrayList<String> rightselected_names = new ArrayList<String>();
	
	
	Main myMain = Main.getInstance();

	@FXML // fx:id="paneFX"
    private Pane paneFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="requestFX1"
    private Label requestFX1; // Value injected by FXMLLoader
	
	@FXML // fx:id="requestFX2"
    private Label requestFX2; // Value injected by FXMLLoader
	
	@FXML // fx:id="leftLabelFX"
    private Label leftLabelFX; // Value injected by FXMLLoader

	@FXML // fx:id="rightLabelFX"
    private Label rightLabelFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="leftListlFX"
    private ListView<String> leftListFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="rightListFX"
    private ListView<String> rightListFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="continuePT"
    private Button continuePT; // Value injected by FXMLLoader

    @FXML // fx:id="exitPT"
    private Button exitPT; // Value injected by FXMLLoader
    
    @FXML // fx:id="moveFX"
    private Button moveFX; // Value injected by FXMLLoader
  
    
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {
		//leftListFX.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    	students = StudentsClassController.getOptionalStudents();
    	
    	details = new ArrayList<String>();
		
    	for (Student student : students)
    	{
    		details.add( student.getFirst_name() + " " + student.getLast_name() + " " + student.getId() );
    	}
    	    	
		leftstudents_names = FXCollections.observableArrayList(details); 

    	leftListFX.setItems(leftstudents_names);
 	}
    
    
	
    @FXML void moveStudents(ActionEvent event) throws IOException 
    {
    	String name;
    	
    	name = leftListFX.getSelectionModel().getSelectedItem();
    	if(name != null)
    	{
	    	leftstudents_names.remove(name);
	    	rightstudents_names.add(name);
    	}
    	else
    	{
    		name = rightListFX.getSelectionModel().getSelectedItem();
        	if(name != null)
        	{
    	    	rightstudents_names.remove(name);
    	    	leftstudents_names.add(name);
        	}
    	}
	  
    	leftListFX.setItems(FXCollections.observableArrayList(leftstudents_names));
	   	rightListFX.setItems(FXCollections.observableArrayList(rightstudents_names));
    }
    
    

    @FXML void nextFrame(ActionEvent event) throws IOException 
    {
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
