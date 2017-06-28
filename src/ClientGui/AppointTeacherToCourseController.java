/**
 * Sample Skeleton for 'AppointTeacherToCourseGUI.fxml' Controller Class
 */

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import projectsalmon.Teacher;

public class AppointTeacherToCourseController implements Initializable {

	Main myMain = Main.getInstance();
	boolean courseNumExists = false;
	boolean teacherIDExists = false;
	boolean teacherInTheCourse = false;
	boolean requestSent = false;

	ObservableList<String> list;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="sendRequestBTN"
	private Button sendRequestBTN; // Value injected by FXMLLoader

	@FXML // fx:id="searchCourseBTN"
	private Button searchCourseBTN; // Value injected by FXMLLoader

	@FXML // fx:id="RequestTeacherTXT"
	private Label RequestTeacherTXT; // Value injected by FXMLLoader

	@FXML // fx:id="RequestTeacherWIN"
	private AnchorPane RequestTeacherWIN; // Value injected by FXMLLoader

	@FXML // fx:id="classCMB"
	private ComboBox<String> classCMB; // Value injected by FXMLLoader

	@FXML // fx:id="searchTeacherIdBTN"
	private Button searchTeacherIdBTN; // Value injected by FXMLLoader

	@FXML // fx:id="courseNumberTB"
	private TextField courseNumberTB; // Value injected by FXMLLoader

	@FXML // fx:id="descriptionTB"
	private TextArea descriptionTB; // Value injected by FXMLLoader

	@FXML // fx:id="backBt"
	private Button backBt; // Value injected by FXMLLoader

	@FXML // fx:id="TeacherNameTB"
	private TextArea TeacherNameTB; // Value injected by FXMLLoader

	@FXML // fx:id="teacherIdTB"
	private TextField teacherIdTB; // Value injected by FXMLLoader

	@FXML
	void sendAppointTeacherRequest(ActionEvent event) {
		String description = descriptionTB.getText();
		if (teacherIDExists == false && courseNumExists == false) {
			Alert alert = new Alert(AlertType.WARNING, "Please fill the form.", ButtonType.OK);
			alert.showAndWait();
		} else if (teacherIDExists == false) {
			Alert alert = new Alert(AlertType.WARNING, "Please enter a valid teacher ID.", ButtonType.OK);
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
			if (!(teacherInTheCourse = SecretaryController
					.searchTeacherInCourseCurrentSemester(courseNumberTB.getText(), teacherIdTB.getText()))) {

				requestSent = SecretaryController.sendAppointTeacherRequest(teacherIdTB.getText(), classCMB.getValue(),
						descriptionTB.getText());
				if (requestSent) {
					Alert alert = new Alert(AlertType.NONE, "Your request has been successfully sent.", ButtonType.OK);
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.WARNING, "Your request was not sent \n please try again.",
							ButtonType.OK);
					alert.showAndWait();
				}
			}

			else {
				Alert alert = new Alert(AlertType.WARNING, "Teacher already assigned to the chosen course.",
						ButtonType.OK);
				alert.showAndWait();
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
	void classList(ActionEvent event) {

	}

	@FXML
	void searchTeacherID(ActionEvent event) {
		String id;
		if ((id = teacherIdTB.getText()) != "") {
			Teacher newTeacher = SecretaryController.searchTeacherID(id);
			if (newTeacher != null) {
				teacherIDExists = true;
				TeacherNameTB.setText(newTeacher.getFirst_name() + " " + newTeacher.getLast_name());
			} else {

				TeacherNameTB.setText("Invalid teacher ID");
			}
		}
	}

	@FXML
	void searchCourse(ActionEvent event) {
		ArrayList<String> classCourseArr;
		String courseNum;

		if ((courseNum = courseNumberTB.getText()) != "") {
			classCourseArr = SecretaryController.searchCourseNum(courseNum);

			if (classCourseArr != null) {
				courseNumExists = true;

				list = FXCollections.observableArrayList(classCourseArr);
				classCMB.setItems(list);
			} else {

				TeacherNameTB.setText("Invalid course number.");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
