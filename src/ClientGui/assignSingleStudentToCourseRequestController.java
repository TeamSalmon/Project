package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.IIOException;

import controllers.SecretaryController;
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

import projectsalmon.StudentsClassInCourse;

public class assignSingleStudentToCourseRequestController implements Initializable {

	Main myMain = Main.getInstance();
	boolean studentIDExists = false;
	boolean courseNumExists = false;
	boolean requestSent = false;

	
	ObservableList<String> list;

	@FXML
	private TextArea descriptionTB;

	@FXML
	private Button searchStudentIDBt;

	@FXML
	private ComboBox<String> classCMB;

	@FXML
	private TextField studentIDTB;

	@FXML
	private TextField studentNameTB;

	@FXML
	private TextField courseNumberTB;

	@FXML
	private Button searchCourseBt;

	@FXML
	private AnchorPane cmbClass;

	@FXML
	private Button backBt;

	@FXML
	private Button sendAssignStudentRequestBt;

	@FXML
	void sendAssignStudentRequest(ActionEvent event) throws IOException {
		boolean studentInTheCourse = false;
		boolean haveNoPreconditions = false;
		String description = descriptionTB.getText();
		if (studentIDExists == false && courseNumExists == false) {
			Alert alert = new Alert(AlertType.WARNING, "Please fill the form.", ButtonType.OK);
			alert.showAndWait();
		} else if (studentIDExists == false) {
			Alert alert = new Alert(AlertType.WARNING, "Please enter a valid student ID.", ButtonType.OK);
			alert.showAndWait();
		} else if (courseNumExists == false) {
			Alert alert = new Alert(AlertType.WARNING, "Please enter a valid course number", ButtonType.OK);
			alert.showAndWait();
		} else if (classCMB.getValue() == null) {
			Alert alert = new Alert(AlertType.WARNING, "Please choose group for the course.", ButtonType.OK);
			alert.showAndWait();
		} else if (description.length() == 0) {
			Alert alert = new Alert(AlertType.WARNING, "Please fill description field.", ButtonType.OK);
			alert.showAndWait();
		}

		else {

			
			//check if classcourse belong to current semester
			boolean courseBelongToCurrSemester = SecretaryController.classCourseBelongToCurrSemester(classCMB.getValue());
			if (!courseBelongToCurrSemester){
				Alert alert = new Alert(AlertType.WARNING, "There are no such group of course current semester.",
						ButtonType.OK);
				alert.showAndWait();
			}//********************8
			else if((haveNoPreconditions=SecretaryController.haveNoPreconditions(studentIDTB.getText(),courseNumberTB.getText()))){
				
				 Alert alert = new Alert(AlertType.WARNING, "Student "+studentNameTB.getText()+" don't have proper prerequisites",
				 ButtonType.OK);
				 alert.showAndWait();
			}
			// check if student take this course in this semester
			else if ((studentInTheCourse = SecretaryController
					.searchStudentInCourseCurrentSemester(courseNumberTB.getText(), studentIDTB.getText()))) {

				Alert alert = new Alert(AlertType.WARNING, "Student already assigned to the chosen course.",
						ButtonType.OK);
				alert.showAndWait();
				
			}

			else {

				requestSent = SecretaryController.sendAssignRequest(studentIDTB.getText(), classCMB.getValue(),
						descriptionTB.getText(), courseNumberTB.getText());
				if (requestSent) {
					Alert alert = new Alert(AlertType.NONE, "Your request has been successfully sent.", ButtonType.OK);
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.WARNING, "Your request was not sent \n please try again.",
							ButtonType.OK);
					alert.showAndWait();
				}
			}

		}

	}

//	private boolean haveNoPreconditions(String studentID, String courseNumber) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@FXML
	void back(ActionEvent event) throws IOException {
		myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
	}

	@FXML
	void classList(ActionEvent event) {

	}

	@FXML
	void searchStudentID(ActionEvent event) throws IOException {

		if (studentIDTB.getText().length() != 0) {
			
			ArrayList<String> newStudent = SecretaryController.searchStudentNameByID(studentIDTB.getText());
			if (newStudent.get(0) != null) {
				studentIDExists = true;
				studentNameTB.setText(newStudent.get(0) + " " + newStudent.get(1));
			} else 
				studentNameTB.setText("Invalid Student ID");
		}
		else 
			studentNameTB.setText("Invalid Student ID");
	}


	@FXML
	void searchCourse(ActionEvent event) throws IOException {

		ArrayList<String> classCourseIDArr = new ArrayList<String>();
		ArrayList<String> StudentsClassInCourseArr= new ArrayList<String>();
		
		
		//ArrayList<String> classCourseArr;
		String courseNum;

		if ((courseNum = courseNumberTB.getText()).length() != 0) {
			StudentsClassInCourseArr = SecretaryController.searchCourseNum(courseNum);
		if(StudentsClassInCourseArr !=null){
			for(String classCourse:StudentsClassInCourseArr)	
				classCourseIDArr.add(classCourse);
		}
			if (classCourseIDArr.size() != 0) {
				courseNumExists = true;

				list = FXCollections.observableArrayList(classCourseIDArr);
				classCMB.setItems(list);
			} 
			else {
				Alert alert = new Alert(AlertType.WARNING, "Invalid course number.",ButtonType.OK);
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING, "Invalid course number.",ButtonType.OK);
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
