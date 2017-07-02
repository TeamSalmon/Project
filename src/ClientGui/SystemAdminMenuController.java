package ClientGui;

import java.io.IOException;
import controllers.AdminController;
import controllers.SecretaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * This GUI controller is responsible for the menu of
 * the the system administrator's functionalites.
 *
 * @see AdminController
 * @author Elia
 */
public class SystemAdminMenuController {

	
	Main myMain = Main.getInstance();
	
	@FXML
	private Button blockPT;

	@FXML
	private Button newcoursePT;
	
	@FXML
	private Pane paneFX;

	@FXML
	private Button exitPT;

	@FXML
	private Label requestFX;

	
	@FXML void blocking(ActionEvent event) throws IOException 
    {
		((Node)event.getSource()).getScene().getWindow().hide();
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(16));
    }
	

	@FXML void newcourse(ActionEvent event) throws IOException 
    {
		((Node)event.getSource()).getScene().getWindow().hide();
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(15));
    }

    /**
     *  Return to the main menu of the user.
     */
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.assignClassToCourseEXIT(1);
    }

}
