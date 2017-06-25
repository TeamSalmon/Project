/**
 * Sample Skeleton for 'LoginPermissionGUI.fxml' Controller Class
 */

package ClientGui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import projectsalmon.LoginUser;

public class LoginPermissionController implements Initializable{

    private static final String FXCollection = null;

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
    	String SelectedPermissionSTR=new String(perCBX.getValue());
    	Alert alert = new Alert(AlertType.INFORMATION,"seccess->move to "+SelectedPermissionSTR+" screen", ButtonType.OK);
		alert.showAndWait();
    }

    @FXML
    void back(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		userNameTXT.setText(LoginUser.loginUser.getFirst_name()+" "+LoginUser.loginUser.getLast_name()+",");
		
		ArrayList<String> perSTR=new ArrayList<String>();
		ArrayList<String> perList=new ArrayList<String>();
		perSTR.add("Student");
		perSTR.add("Parent");
		perSTR.add("Teacher");
		perSTR.add("School Manager");
		perSTR.add("System Admin");
		perSTR.add("Secretary");
		Integer mask=1;	
//	    ObservableList<String> list = FXCollection.observableArrayList();
		for(int i=0;i<6;i++){
			if (((mask<<i)&LoginUser.loginUser.getPermission())!=0){
				perList.add(perSTR.get(i));
			}
			list= FXCollections.observableArrayList(perList);
			perCBX.setItems(list);
		}
			
	}//end of method

}//end of class
