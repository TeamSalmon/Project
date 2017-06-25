/**
 * Sample Skeleton for 'appointTeacherRequestGUI.fxml' Controller Class
 */

package ClientGui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class appointTeacherRequestController implements Initializable{

    @FXML // fx:id="sendRequestBTN"
    private Button sendRequestBTN; // Value injected by FXMLLoader

    @FXML // fx:id="searchCourseBTN"
    private Button searchCourseBTN; // Value injected by FXMLLoader

    @FXML // fx:id="RequestTeacherTXT"
    private Label RequestTeacherTXT; // Value injected by FXMLLoader

    @FXML // fx:id="RequestTeacherWIN"
    private AnchorPane RequestTeacherWIN; // Value injected by FXMLLoader

    @FXML // fx:id="classCMB"
    private ComboBox<?> classCMB; // Value injected by FXMLLoader
    ObservableList<String> list;//list of class of student to the specific course

    @FXML // fx:id="searchTeacherIdBTN"
    private Button searchTeacherIdBTN; // Value injected by FXMLLoader

    @FXML // fx:id="courseNumberTB"
    private TextField courseNumberTB; // Value injected by FXMLLoader

    @FXML // fx:id="deacriptionTB"
    private TextArea deacriptionTB; // Value injected by FXMLLoader

    @FXML // fx:id="backBt"
    private Button backBt; // Value injected by FXMLLoader

    @FXML // fx:id="TeacherNameTB"
    private TextArea TeacherNameTB; // Value injected by FXMLLoader

    @FXML // fx:id="teacherIdTB"
    private TextField teacherIdTB; // Value injected by FXMLLoader

    @FXML
    void sendRequest(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void classList(ActionEvent event) {

    }

    @FXML
    void searchTeacherID(ActionEvent event) {

    }

    @FXML
    void searchCourse(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
