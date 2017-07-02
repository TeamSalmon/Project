/**
 * Sample Skeleton for 'LoginPermissionGUI.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import projectsalmon.LoginUser;

public class LoginPermissionController implements Initializable{
	Main myMain = Main.getInstance();
	private static final String FXCollection = null;
	ArrayList<String>	forFunc=new ArrayList<String>();
	
	@FXML // fx:id="permissionWND"
    private AnchorPane permissionWND; // Value injected by FXMLLoader

    @FXML // fx:id="baclBTN"
    private Button baclBTN; // Value injected by FXMLLoader

    @FXML // fx:id="welcomTXT"
    private Text welcomTXT; // Value injected by FXMLLoader

    @FXML // fx:id="choosePerTXT"
    private Text choosePerTXT; // Value injected by FXMLLoader

    @FXML // fx:id="userNameTXT"
    private Text userNameTXT; // Value injected by FXMLLoader

    @FXML // fx:id="perCBX"
    private ComboBox<String> perCBX; // Value injected by FXMLLoader
    ObservableList<String> list ;

    @FXML
    void c3299(ActionEvent event) {

    }

    @FXML
	void clickPerCBX(ActionEvent event) {
		String SelectedPermissionSTR = new String(perCBX.getValue());
		/*
		 * Alert alert = new Alert(AlertType.INFORMATION,"seccess->move to "
		 * +SelectedPermissionSTR+" screen", ButtonType.OK);
		 * alert.showAndWait();
		 */
		switch (SelectedPermissionSTR) {

		case "Student":// Student
				// ((Node)event.getSource()).getScene().getWindow().hide();
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(12));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "Parent":// Parent 
			if (LoginUser.loginUser.getIsBlock() == 1) {
				sendErrorMSG("Your permission as a parent is denied,\n"
						+ " please contact school manager or select an other roule");
				try {
					myMain.getMange().changeScene(myMain.getMange().initializationScreens(-101));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} // end of if (LoginUser.loginUser.getIsBlock() == 1)
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(-104));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Teacher":// Teacher
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(3));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "School Manager":// School manager
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(-102));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "System Admin":// System administrator
			/*
			 * try { myMain.getMange().changeScene(myMain.getMange().
			 * initializationScreens(other window)); } catch (IOException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); }
			 */

			break;
		case "Secretary":// Secretary
			/*
			 * try { myMain.getMange().changeScene(myMain.getMange().
			 * initializationScreens(-103)); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			sendErrorMSG("Only Secretary-->moving");
			break;
		}//end of switch
    
    }//end of "clickPerCBX" method

	@FXML
	void back(ActionEvent event) {
		try {
			myMain.getMange().changeScene(myMain.getMange().initializationScreens(-100));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public void sendErrorMSG(String msg){
		Alert alert = new Alert(AlertType.ERROR,msg, ButtonType.CANCEL);
		alert.showAndWait();
	} 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		userNameTXT.setText(LoginUser.loginUser.getFirst_name() + " " + LoginUser.loginUser.getLast_name() + ",");

		ArrayList<String> perSTR = new ArrayList<String>();
		ArrayList<String> perList = new ArrayList<String>();
		perSTR.add("Student");
		perSTR.add("Parent");
		perSTR.add("Teacher");
		perSTR.add("School Manager");
		perSTR.add("System Admin");
		perSTR.add("Secretary");
		Integer mask = 1;

		switch (LoginUser.loginUser.getPermission()) {

		case 1://Student only
			// ((Node)event.getSource()).getScene().getWindow().hide();
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(12));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 2://Parent only
			if (LoginUser.loginUser.getIsBlock() == 1) {
				sendErrorMSG("Your permission as a parent is denied, please contact school manager");
				try {
					myMain.getMange().changeScene(myMain.getMange().initializationScreens(-101));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}//end of if (LoginUser.loginUser.getIsBlock() == 1)
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(-104));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4://Teacher only
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(3));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 8://School manager only
			try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(-102));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 16://System administrator only
		/*	try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(other window));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			break;
		case 32://Secretary only
			/*try {
				myMain.getMange().changeScene(myMain.getMange().initializationScreens(-103));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			sendErrorMSG("Only Secretary-->moving");
			break;
		default:
			for (int i = 0; i < 6; i++) {
				if (((mask << i) & LoginUser.loginUser.getPermission()) != 0) {
					perList.add(perSTR.get(i));
				}
			}
				list = FXCollections.observableArrayList(perList);
				perCBX.setItems(list);
				break;			
		}// end switch
return;
	}// end of method

}//end of class
