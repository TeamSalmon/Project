/**
 * Sample Skeleton for 'secondStage.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import ServerClient.ClientConsole;
import controllers.StudentsClassController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class DefineClass1Controller {
	
	String grade;
	Main myMain = Main.getInstance();
	
	@FXML // fx:id="tabpaneFX"
    private TabPane tabpaneFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="anchorFX"
    private AnchorPane anchorFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="tab1FX"
    private Tab tab1FX; // Value injected by FXMLLoader
	
	@FXML // fx:id="requestFX"
    private Label requestFX; // Value injected by FXMLLoader
	
	@FXML // fx:id="continuePT"
    private Button continuePT; // Value injected by FXMLLoader

    @FXML // fx:id="grade"
    private TextField gradeFX; // Value injected by FXMLLoader

    @FXML // fx:id="InfoLog"
    private TextField infoLog; // Value injected by FXMLLoader

    @FXML // fx:id="exitPT"
    private Button exitPT; // Value injected by FXMLLoader
   
    
    @FXML void nextFrame (ActionEvent event) throws IOException 
    {
    	grade = gradeFX.getText();
    	if(grade.length()<1)
    	{
    		requestFX.setText("Enter a valid garde, please.");
    	}
    	else
    	{
    		//myMain.getConnection();
			//ClientConsole.setLog(infoLog);
    		//myMain.cc.log=;
    		//myMain.getinfo(grade);
    		
    		StudentsClassController.setNewGrade(grade);	
    	
    		((Node)event.getSource()).getScene().getWindow().hide();
    		myMain.getMange().initializationScreens(9);
    		myMain.getMange().changesence(2);
    	
    	}
    }
    
    
    @FXML void exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	 myMain.getMange().changesence(0);
    	//System.exit(1);
    }

}
