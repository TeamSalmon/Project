package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.SecretaryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import projectsalmon.StudentsClassInCourse;
import projectsalmon.Teacher;

public class RemovetTeacherFromCourseController implements Initializable {
	Main myMain = Main.getInstance();
	boolean teacherIDExists = false;
	boolean courseNumExists = false;
	boolean teacherClassCourse = false;
	ObservableList<String> list;

	@FXML
	private ComboBox<String> classCMB;

	@FXML
	private Button searchCourseBTN;

	@FXML
	private Button sendRemoveTeacherRequestBt;

	@FXML
	private TextField courseNumberTB;

	@FXML
	private Button backBt;

	@FXML
	private TextArea teacherNameTB;

	@FXML
	private TextField teacherIDTB;

	@FXML
	private TextArea descriptionTB;

	@FXML
	private Button searchTeacherIDBt;

	@FXML
	void searchCourse(ActionEvent event) throws IOException {

		ArrayList<String> teacherClassCourseArr = new ArrayList<String>();
		String courseNum = courseNumberTB.getText();
		String teacherID = teacherIDTB.getText();
		if(teacherID.length()!= 0){
		Teacher newTeacher = SecretaryController.searchTeacherID(teacherID);
		if (newTeacher != null)
			teacherIDExists = true;}
		else{
			Alert alert = new Alert(AlertType.WARNING, "Please fill teacher ID text box.", ButtonType.OK);
			alert.showAndWait();
		}
		if(courseNum.length()!= 0){
			ArrayList<StudentsClassInCourse> classCourseArr = SecretaryController.searchCourseNum(courseNum);
			if (classCourseArr.size() != 0) {
				courseNumExists = true;
		}
		else{
			Alert alert = new Alert(AlertType.WARNING, "Please fill course number text box.", ButtonType.OK);
			alert.showAndWait();
		}
		
			if ((courseNumExists) && (teacherIDExists)) {

				for (StudentsClassInCourse classCourse : classCourseArr)
					if (classCourse.getTeacher().getId() == teacherID) {
						teacherClassCourseArr.add(classCourse.getTeacher().getId());
					}

				if (teacherClassCourseArr.size() != 0) {
					teacherClassCourse = true;
					list = FXCollections.observableArrayList(teacherClassCourseArr);
					classCMB.setItems(list);
				} else {

					Alert alert = new Alert(AlertType.WARNING, "Teacher " + teacherNameTB.getText()
							+ " is not participate in the course on current semester.", ButtonType.OK);
					alert.showAndWait();
				}
			} else if (!teacherIDExists) {
				Alert alert = new Alert(AlertType.WARNING, "Invalid teacher ID.", ButtonType.OK);
				alert.showAndWait();
			} else if (!courseNumExists) {
				Alert alert = new Alert(AlertType.WARNING, "Invalid course number.", ButtonType.OK);
				alert.showAndWait();
			}
		}
	}

	@FXML
	void classList(ActionEvent event) {

	}

	@FXML
	void searchTeacherID(ActionEvent event) {
		String id;
		if ((id = teacherIDTB.getText()) != "") {
			Teacher newTeacher = SecretaryController.searchTeacherID(id);
			if (newTeacher != null) {
				teacherIDExists = true;
				teacherNameTB.setText(newTeacher.getFirst_name() + " " + newTeacher.getLast_name());
			} else {

				teacherNameTB.setText("Invalid teacher ID");
			}
		}
	}

	@FXML
	void back(ActionEvent event) {
		try {
			myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void sendRemoveTeacherRequest(ActionEvent event) {
		teacherClassCourse = false;
		teacherIDExists = false;
		courseNumExists = false;

		ArrayList<String> teacherClassCourseArr = new ArrayList<String>();
		String courseNum = courseNumberTB.getText();
		String teacherID = teacherIDTB.getText();
		Teacher newTeacher = SecretaryController.searchTeacherID(teacherID);
		ArrayList<StudentsClassInCourse> classCourseArr = SecretaryController.searchCourseNum(courseNum);

		if (newTeacher != null)
			teacherIDExists = true;
		if (classCourseArr.size() != 0)
			courseNumExists = true;

		if ((courseNumExists) && (teacherIDExists)) {
			for (StudentsClassInCourse classCourse : classCourseArr)
				if (classCourse.getTeacher().getId() == teacherID) {
					teacherClassCourseArr.add(classCourse.getTeacher().getId());
				}
			if (teacherClassCourseArr.size() != 0)
				teacherClassCourse = true;
			else {
				Alert alert = new Alert(AlertType.WARNING,
						"Teacher " + teacherNameTB.getText() + " is not participate in the course on current semester.",
						ButtonType.OK);
				alert.showAndWait();
			}

		} 
		else if (!teacherIDExists){
			Alert alert = new Alert(AlertType.WARNING, "Invalid teacher ID.", ButtonType.OK);
			alert.showAndWait();
			} 
		else if (!courseNumExists) {
			Alert alert = new Alert(AlertType.WARNING, "Invalid course number.", ButtonType.OK);
			alert.showAndWait();
		}
		
		
		if(teacherClassCourse ){
			if(classCMB.getValue()==null){
				Alert alert = new Alert(AlertType.WARNING, "Please choose group of the course.", ButtonType.OK);
				alert.showAndWait();
			}
		
			else if(descriptionTB.getText().length()== 0){
				Alert alert = new Alert(AlertType.WARNING, "Please fill description field.", ButtonType.OK);
				alert.showAndWait();
			}
			else{
				if(SecretaryController.removeTeacherfromCourseRequest(courseNumberTB.getText(),
						teacherIDTB.getText(), descriptionTB.getText())){
					Alert alert = new Alert(AlertType.NONE, "Your request has been sent successfully.", ButtonType.OK);
					alert.showAndWait();
				}
				else{
					Alert alert = new Alert(AlertType.WARNING, "Your request wasn't sent \n please try again.",
							ButtonType.OK);
					alert.showAndWait();
				}
				
			}
		}

		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
