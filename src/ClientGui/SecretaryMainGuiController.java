package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SecretaryMainGuiController implements Initializable {

	Main myMain = Main.getInstance(); 

	  @FXML
	    private Button defineClassBT;

	    @FXML
	    private AnchorPane cmbClass;

	    @FXML
	    private Button assignTeacherRequestBT;

	    @FXML
	    private Button unassignStudentRequestBT;

	    @FXML
	    private Button OpenNewSemesterBT;

	    @FXML
	    private Button assignStudentRequestBT;

	    @FXML
	    private Button unassignTeacherRequestBT;

	    @FXML
	    void defineClass(ActionEvent event) {
	    	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(7));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void OpenNewSemester(ActionEvent event) {
	    /*	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(Enter Number Of Window));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	    }

	    @FXML
	    void assignStudentRequest(ActionEvent event) {
	    	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(5));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void assignTeacherRequest(ActionEvent event) {
	    	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(20));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void unassignStudentRequest(ActionEvent event) {
	    	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(6));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void unassignTeacherRequest(ActionEvent event) {
	    	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(21));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
