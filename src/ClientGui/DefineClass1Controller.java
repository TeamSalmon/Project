
package ClientGui;

/**
 * This GUI controller is responsible for the first stage of
 * the the secretary's "Define Class" functionality.
 * In this screen the user provides the grade/level of the new class.
 *
 * @see SecretaryController
 * @see DefineClass2Controller
 * @author Elia
 */
import java.io.IOException;
import ServerClient.ClientConsole;
import controllers.AdminController;
import controllers.SecretaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class DefineClass1Controller {
	
	String grade;
	Main myMain = Main.getInstance();
  
	@FXML
    private Pane paneFX;
	
	@FXML 
    private Label requestFX; 
	
	@FXML 
    private Label requestFX1;
	
	@FXML 
    private Button continuePT; 

    @FXML
    private TextField gradeFX;

    @FXML
    private Button exitPT; 
   
    
    /**
     * Update SecretaryController with the new grade/level and move to DefineClass2Controller
     * @param event
     * @throws IOException
     */
    @FXML void nextFrame (ActionEvent event) throws IOException 
    {
    	grade = gradeFX.getText();
    	if(grade.length()<1 || !( grade.equals("10") ||  grade.equals("11") ||  grade.equals("12")))
    	{
    		requestFX.setText("Please enter a valid garde");
    	}
    	else
    	{
    		SecretaryController.setNewGrade(grade);
    	
    		((Node)event.getSource()).getScene().getWindow().hide();
			myMain.getMange().changeScene(myMain.getMange().initializationScreens(8));
    	
    	}
    }
    
    
    /**
     * Return to the main menu of the actor
     * @param event
     * @throws IOException
     */
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.defineClassEXIT(1);
    }
}
