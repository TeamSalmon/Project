/**
 * Sample Skeleton for 'LoginGUI.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ServerClient.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import projectsalmon.LoginUser;
import projectsalmon.User;

public class LoginController implements Initializable {
	Main myMain = Main.getInstance();
	ArrayList<String>	forFunc=new ArrayList<String>();
	@FXML // fx:id="PasswordTXT"
	private PasswordField PasswordTXT; // Value injected by FXMLLoader

	@FXML // fx:id="UserIdLBL"
	private Label UserIdLBL; // Value injected by FXMLLoader

	@FXML // fx:id="ActivityLogTXT"
	private TextArea ActivityLogTXT; // Value injected by FXMLLoader

	@FXML // fx:id="idTXT"
	private TextField idTXT; // Value injected by FXMLLoader

	@FXML // fx:id="psdLBL"
	private Label psdLBL; // Value injected by FXMLLoader

	@FXML // fx:id="LoginBT"
	private Button LoginBT; // Value injected by FXMLLoader

	@FXML // fx:id="LoginWND"
	private AnchorPane LoginWND; // Value injected by FXMLLoader

	@FXML
	void login(ActionEvent event) throws IOException {
		String idSTR = idTXT.getText();
		if (idSTR.length() == 9) {
			if (!LoginUser.loginUser.getId().equals(idSTR)) {
				Boolean f = searchUser(idSTR);

				if (!f) {
					sendErrorMSG(" User's ID does not exist, please try agine");
					return;
				}
			}

			// if (LoginUser.loginUser.getId() != "null") {
			if (LoginUser.loginUser.getLoggedStatus().equals(LoginUser.Locked)) {
				sendErrorMSG("User is locked, please contact you'r administrator");
				return;
			} else if (LoginUser.loginUser.getLoggedStatus().equals(LoginUser.Loged)) {
				sendErrorMSG("User is allready login");
				return;
			} else {
				if (LoginUser.loginUser.getPassword().equals(PasswordTXT.getText())) {
					LoginUser.loginUser.setloginLockCounter(0);
					LoginUser.loginUser.setLoggedStatus(LoginUser.Loged);
					forFunc.clear();
					forFunc.add("updateUser");
					forFunc.add(LoginUser.loginUser.getloginLockCounter().toString());
					forFunc.add(LoginUser.Loged.toString());
					myMain.getConnection().getClient().handleMessageFromClientUI(forFunc);
					try {
						myMain.getMange().changeScene(myMain.getMange().initializationScreens(-101));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				} // end of if

				else {
					LoginUser.loginUser.setloginLockCounter(LoginUser.loginUser.getloginLockCounter() + 1);
					// updateDB
					switch (LoginUser.loginUser.getloginLockCounter()) {
					case 1:
						sendErrorMSG(" Wrong password, you have left more 2 tries");
						forFunc.clear();
						forFunc.add("updateUser");
						forFunc.add(LoginUser.loginUser.getloginLockCounter().toString());
						forFunc.add(LoginUser.logOut.toString());
						myMain.getConnection().getClient().handleMessageFromClientUI(forFunc);
						break;
					case 2:
						sendErrorMSG(" Wrong password, you have left 1 more try");
						forFunc.clear();
						forFunc.add("updateUser");
						forFunc.add(LoginUser.loginUser.getloginLockCounter().toString());
						forFunc.add(LoginUser.logOut.toString());
						myMain.getConnection().getClient().handleMessageFromClientUI(forFunc);
						break;
					case 3:
						sendErrorMSG(
								" Wrong password 3 times, User is locked, please contact you'r administrator");
						forFunc.clear();
						forFunc.add("updateUser");
						forFunc.add(LoginUser.loginUser.getloginLockCounter().toString());
						forFunc.add(LoginUser.Locked.toString());
						myMain.getConnection().getClient().handleMessageFromClientUI(forFunc);
						break;
					default:
						sendErrorMSG("Programming  user.getloginLockCounter() is NOT 1->3");
					}// end of switch
					/* update DB */
					return;
				}

			} // end of if (user != null)
		} // end of if(idSTR.length()==9)
		else
			sendErrorMSG("The ID need to be 9 digits, please try agine");
		return;
	}// end of login method

	public void sendErrorMSG(String msg){
//		ActivityLogTXT.setText(msg);
	
		Alert alert = new Alert(AlertType.ERROR,msg, ButtonType.CANCEL);
		alert.showAndWait();
	}
	public Boolean searchUser(String id) throws IOException {

/*		ArrayList<LoginUser> dbARR = new ArrayList<LoginUser>();
		dbARR.add(new LoginUser("123456789", "Ash", "Ketchum", "Red",0 , LoginUser.StudentPER, LoginUser.logOut));
		dbARR.add(new LoginUser("987654321", "Misty", "Misty's Last Name", "Green",0 ,36,LoginUser.logOut));
		dbARR.add(new LoginUser("098765432", "Brock", "Brock's Last Name", "Blue", 0, LoginUser.TeacherPER, LoginUser.logOut));
*/
	/*	for (LoginUser dbUser : dbARR)
			if (dbUser.getId().equals(id)){
				LoginUser.loginUser.copy(dbUser);
				return true;*/

		forFunc.clear();
		forFunc.add("searchUser");
		forFunc.add(id);
		myMain.getConnection().getClient().handleMessageFromClientUI(forFunc);
		Object dbUserOBJ =  myMain.getConnection().getMessage();
		LoginUser dbUser=(LoginUser)dbUserOBJ;
		if (dbUser == null)
			return false;
		if (dbUser.getId().equals(id))
			LoginUser.loginUser.copy(dbUser);
		return true;

	}// end of searchUser method

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}// end of LoginController
