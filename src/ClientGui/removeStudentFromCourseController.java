package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SecretayController;
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
    private Button searchCourseBt;

    @FXML
    private TextField courseNumberTB;

    @FXML
    private TextArea descriptionTB;

    @FXML
    private Button backBt;

    @FXML
    private TextField studentIDTB;

    @FXML
    private TextArea studentNameTB;

    @FXML
    private Button sendRequestBt;

    @FXML
    void sendRequest(ActionEvent event) {
    	//check if student in the course group
    		if(!(studentInTheCourse = SecretayController.searchStudentInCourse(courseNumberTB.getText(),studentIDTB.getText()))){
    			Alert alert = new Alert(AlertType.WARNING, "Student "+ studentNameTB.getText()+" is not in this course.", ButtonType.OK);
        		alert.showAndWait();
        	}
    		else{
    			studentDelitedCourse = SecretayController.deleteStudentfromCourse(courseNumberTB.getText(),studentIDTB.getText());
    			if(studentDelitedCourse){
    				Alert alert = new Alert(AlertType.NONE, "Your request has been sent successfully.", ButtonType.OK);
					alert.showAndWait();
    			}
    		}
    }

    @FXML
    void back(ActionEvent event)  throws IOException {
   	 myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
   }

    @FXML
    void searchStudentID(ActionEvent event) throws IOException
    {
    	String id;
    	if ((id = studentIDTB.getText()) != "")
    	{
    		Student newStudent = SecretayController.searchStudentID(id);
    		if (newStudent != null){
    			studentIDExists=true;
    			studentNameTB.setText( newStudent.getFirst_name() +" "+ newStudent.getLast_name() );
    		}
    		else {
    			
    			studentNameTB.setText("Incorrect Student ID");
    		}	
    	}
    }

    
    
    @FXML
	void searchCourse(ActionEvent event) {}
	@FXML
//	void searchCourse(ActionEvent event) throws IOException{
//			
//		ArrayList<String> classCourseArr = new ArrayList<String>() ;
//		String courseNum;
//		
//    	if ((courseNum = courseNumberTB.getText()) != "")
//			classCourseArr = SecretayController.searchCourseNum(courseNum);
//    	
//		if(classCourseArr==null){
//	    	Alert alert = new Alert(AlertType.WARNING, "Wrong course number.", ButtonType.OK);
//	    	alert.showAndWait();
//	    }
//		else courseNumExists=true;
//
//	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
