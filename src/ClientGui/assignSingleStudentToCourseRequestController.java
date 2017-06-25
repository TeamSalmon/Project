package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.IIOException;

import Controllers.SecretayController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import projectsalmon.Course;
import projectsalmon.Student;
import projectsalmon.StudentsClassInCourse;

public class assignSingleStudentToCourseRequestController  implements Initializable{

	
	Main	myMain=	Main.getInstance();
	boolean studentIDExists = false;
	boolean courseNumExists = false;
	boolean requestSent = false;
	ObservableList<String> list;
	
	@FXML
    private TextArea deacriptionTB;
	
	@FXML
    private Button searchStudentIDBt;

    @FXML
    private ComboBox<String> classCMB;

    @FXML
    private TextField studentIDTB;
    
    @FXML
    private TextArea studentNameTB;

    @FXML
    private TextField courseNumberTB;
    
  
    @FXML
    private Button searchCourseBt;

    @FXML
    private AnchorPane cmbClass;

    @FXML
    private Button backBt;


    @FXML
    private Button sendRequestBt;

   

    @FXML
    void sendRequest(ActionEvent event) throws IOException {
    	
    	
    	if(studentIDExists==false &&courseNumExists==false){
    		Alert alert = new Alert(AlertType.WARNING, "Please fill the form.", ButtonType.OK);
		alert.showAndWait();
    	}
    	else if(studentIDExists==false){
    		Alert alert = new Alert(AlertType.WARNING, "Wrong student ID.", ButtonType.OK);
    		alert.showAndWait();
    	}
    	else if(courseNumExists==false){
    		Alert alert = new Alert(AlertType.WARNING, "The couse is not exists.", ButtonType.OK);
    		alert.showAndWait();
    	}	
    	else if(classCMB.getValue()==null){
    		Alert alert = new Alert(AlertType.WARNING, "Please choose class of the course.", ButtonType.OK);
    		alert.showAndWait();
    	}		
    	else if(deacriptionTB.getText()==""){
    		Alert alert = new Alert(AlertType.WARNING, "Please fill description field.", ButtonType.OK);
    		alert.showAndWait();
    	}	
    		 		
    	else{
    		//check prerequisites********************************************
    		//if ok continue
    		//else{
    		//Alert alert = new Alert(AlertType.WARNING, "Student "+studentNameTB.getText()+" don't have proper prerequisites", ButtonType.OK);
    		//alert.showAndWait();}	
    		
    		
    		requestSent=SecretayController.sendStudentRequest(studentIDTB.getText(), classCMB.getValue(), deacriptionTB.getText());
    		if(requestSent){
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
    void classList(ActionEvent event) {

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
	void searchCourse(ActionEvent event) throws IOException{
			
		ArrayList<String> classCourseArr ;
		String courseNum;
		
    	if ((courseNum = courseNumberTB.getText()) != ""){
			classCourseArr = SecretayController.searchCourseNum(courseNum);
			
			if (classCourseArr != null) {
				courseNumExists = true;
				
				list = FXCollections.observableArrayList(classCourseArr);
				classCMB.setItems(list);
			} else {

				studentNameTB.setText("Incorrect Student ID");
			}
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}


