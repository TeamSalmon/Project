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
 * This GUI controller is responsible for the stage of
 * the the system administrator's "Block or Unblock User" functionality.
 * In this screen the user selects a chosen user by his/her ID and a 
 * attempts to block or unblock him/her. 
 *
 * @see AdminController
 * @author Elia
 */
public class BlockOrUnblockUserController {

	
	Main myMain = Main.getInstance();
	
	@FXML
	private Button blockPT;

	@FXML
	private Button unblockPT;
	
	@FXML
	private Pane paneFX;

	@FXML
	private Button exitPT;

	@FXML
	private TextField idFX;

	@FXML
	private Label requestFX;

	
	
	/**
	 * Deals with an attempt of the user to block a specified user
	 * @param event
	 * @throws IOException
	 */
	@FXML void block(ActionEvent event) throws IOException 
	{
		String id = idFX.getText();
		
		if(id.equals(""))
			requestFX.setText("Please enter a valid ID.");
		
		else
		{
			if( AdminController.checkIfAdmin(id) == true)
				requestFX.setText("This user's status can't be changed.");
			
			else
			{
				String answer = AdminController.blockUser(id);
				
				switch (answer) 
				{
					case "The user is already blocked":
						requestFX.setText("This user is already blocked.");
						break;
		
					case "The user was blocked successfully":
						requestFX.setText(answer + ".");
						break;
		
					case "No such user":
						requestFX.setText("Couldn't recognize this ID.");
						break;
				}
			}
		}
	}	


	/**
	 * Deals with an attempt of the user to unblock a specified user
	 * @param event
	 * @throws IOException
	 */
	@FXML void unblock(ActionEvent event) throws IOException 
	{
		String id = idFX.getText();
		
		if(id.equals(""))
			requestFX.setText("Please enter a valid ID.");
		
		else
		{
			if( AdminController.checkIfAdmin(id) == true)
				requestFX.setText("This user's status can't be changed.");
			
			else
			{
				String answer = AdminController.unblockUser(id);
				
				switch (answer) 
				{
					case "The user is already unblocked":
						requestFX.setText("This user is already unblocked.");
						break;
		
					case "The user was unblocked successfully":
						requestFX.setText(answer + ".");
						break;
		
					case "No such user":
						requestFX.setText("Couldn't recognize this ID.");
						break;
				}
			}
		}
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
