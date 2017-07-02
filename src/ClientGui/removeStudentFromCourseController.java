package ClientGui;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SecretaryController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import projectsalmon.Student;
import projectsalmon.StudentsClassInCourse;

public class removeStudentFromCourseController   implements Initializable{

	
	Main	myMain=	Main.getInstance();
	boolean studentIDExists = false;
	boolean courseNumExists = false;
	boolean studentInTheCourse = false;
	boolean studentDelitedCourse = false;
	
    @FXML
    private Button searchStudentIDBt;

    @FXML
    private TextField courseNumberTB;

    @FXML
    private TextArea descriptionTB;

    @FXML
    private Button backBt;

    @FXML
    private TextField studentIDTB;

    @FXML
    private TextField studentNameTB;

    @FXML
    private Button sendRemoveStudentRequestBt;

    @FXML
    void sendRemoveStudentRequest(ActionEvent event) {
    	//check if the course is exists
    	if(SecretaryController.searchCourseNum(courseNumberTB.getText())!=null){
    	courseNumExists=true;
    	}
    	//check if student in the course group this semester
    		if(!(studentInTheCourse = SecretaryController.searchStudentInCourseCurrentSemester(courseNumberTB.getText(),studentIDTB.getText()))){
    			Alert alert = new Alert(AlertType.WARNING, "Student "+ studentNameTB.getText()+" is not participate in the course on current semester.", ButtonType.OK);
        		alert.showAndWait();
        	}
    		else{
    			
    			studentDelitedCourse = SecretaryController.removeStudentfromCourseRequest(courseNumberTB.getText(),studentIDTB.getText(),descriptionTB.getText());
    			if(studentDelitedCourse){
    				Alert alert = new Alert(AlertType.NONE, "Your request has been sent successfully.", ButtonType.OK);
					alert.showAndWait();
    			}
    			else{
    			Alert alert = new Alert(AlertType.WARNING, "Your request wasn't sent \n please try again.", ButtonType.OK);
				alert.showAndWait();
    			}
    		}
    }

    @FXML
    void back(ActionEvent event)  throws IOException {
   	 myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
   }



	@FXML
	void searchStudentID(ActionEvent event) throws IOException {

		if (studentIDTB.getText().length() != 0) {
			
			ArrayList<String> newStudent = SecretaryController.searchStudentNameByID(studentIDTB.getText());
			if (newStudent != null) {
				studentIDExists = true;
				studentNameTB.setText(newStudent.get(0) + " " + newStudent.get(1));
			} else 
				studentNameTB.setText("Invalid Student ID");
		}
		else 
			studentNameTB.setText("Invalid Student ID");
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
