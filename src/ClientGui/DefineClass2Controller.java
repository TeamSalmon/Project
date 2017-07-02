
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import projectsalmon.Student;
import projectsalmon.StudentsClass;
import javafx.fxml.Initializable;


/**
 * This GUI controller is responsible for the second stage of
 * the the secretary's "Define Class" functionality.
 * In this screen the user selects the students that will be in the new class.
 *
 * @see SecretaryController
 * @see DefineClass1Controller
 * @author Elia
 */
public class DefineClass2Controller implements Initializable{
	
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<String> details = new ArrayList<String>();
	ObservableList<String> leftstudents_names;

	ArrayList<String> rightstudents_names = new ArrayList<String>();
	
	boolean full_alert;
	
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
    
    @FXML // fx:id="exitPT"
    private Button exitPT1; // Value injected by FXMLLoader
    
    @FXML // fx:id="moveFX"
    private Button moveFX; // Value injected by FXMLLoader
  
    
    /**
     * Initializes a list of optional students and an empty list for the new class
     */
	@Override public void initialize (URL location, ResourceBundle resoources) 
    {
		try 
		{
			students = SecretaryController.getOptionalStudents(SecretaryController.getGrade());
		//try to insert 12
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		if(students.size() == 0)
		{
			leftLabelFX.setVisible(false);
			rightLabelFX.setVisible(false);
			leftListFX.setVisible(false);
			rightListFX.setVisible(false);
			requestFX2.setVisible(false);
			moveFX.setVisible(false);
			continuePT.setVisible(false);
			exitPT.setVisible(false);
			exitPT1.setVisible(true);

		
				requestFX1.setText("Couldn't find free students at level " + SecretaryController.getGrade() +".");
		
				requestFX1.setTextFill(Color.RED);
		}
		else
		{
			details = new ArrayList<String>();
		
			for (Student student : students)
			{
				details.add( student.getFirst_name() + " " + student.getLast_name() + " " + student.getId() );
			}
    	    	
			leftstudents_names = FXCollections.observableArrayList(details); 

			leftListFX.setItems(leftstudents_names);
		}
	}
    
    
	/**
	 * Handles an attempt of the user to move a student to/from the new class
	 * @param event
	 * @throws IOException
	 */
    @FXML void moveStudents(ActionEvent event) throws IOException 
    {
    	if (full_alert == true)
    	{
    		rightLabelFX.setText("New class");
    		rightLabelFX.setTextFill(Color.BLACK);
    		full_alert = false;
    	}
    	String name = leftListFX.getSelectionModel().getSelectedItem();
    	
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
    
    
    /** 
     *Check the capacity of the new class and move on to get message about success or an error
     * @param event
     * @throws IOException 
     */
     @FXML void nextFrame(ActionEvent event) throws IOException 
    {
    	if (rightstudents_names.size() < 1)
     	{
     		rightLabelFX.setText("New class - Empty");
     		rightLabelFX.setTextFill(Color.RED);
     		full_alert = true;// not full, but canceling alert
     	}
    	 
    	else if (rightstudents_names.size() > 30)
    	{
    		rightLabelFX.setText("New class - Over 30 students");
    		rightLabelFX.setTextFill(Color.RED);
    		full_alert = true;
    	}
		else 
		{
			ArrayList<Student> chosen_students = new ArrayList<Student>();
			// Get the chosen students and pass them to the controller
			for (Student student : students) {
				String detailsOfStudent = student.getFirst_name() + " " + student.getLast_name() + " "
						+ student.getId();
				if (rightstudents_names.contains(detailsOfStudent))
					chosen_students.add(student);
			}
			StudentsClass result = SecretaryController.createNewClass(chosen_students);

			leftLabelFX.setVisible(false);
			rightLabelFX.setVisible(false);
			leftListFX.setVisible(false);
			rightListFX.setVisible(false);
			requestFX2.setVisible(false);
			moveFX.setVisible(false);
			continuePT.setVisible(false);
			exitPT.setVisible(false);
			exitPT1.setVisible(true);

			if (result != null) {
				requestFX1.setText("The class " + result.getClassName() + " (ID: " + result.getClassId() + ") "
						+ "was created successfully.");
			} else {
				requestFX1.setText("Couldn't create the new class.");
			}
		}
	}
	
    
    /**
     * Return to the main menu of the actor
     * @param event
     * @throws IOException
     */
    @FXML void exit(ActionEvent event) throws IOException
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.defineClassEXIT(2);
    }

}
