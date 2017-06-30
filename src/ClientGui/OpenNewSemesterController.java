package ClientGui;

import java.io.IOException;

import controllers.SecretaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class OpenNewSemesterController {

	Main myMain = Main.getInstance();

	
	
	
	
	
	
	
	
	
	
	
	 
    /**
     * Return to the main menu of the actor
     * @param event
     * @throws IOException
     */
    @FXML void exit(ActionEvent event) throws IOException
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	SecretaryController.defineClassEXIT(2);
    }
}
