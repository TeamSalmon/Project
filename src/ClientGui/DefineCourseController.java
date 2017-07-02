package ClientGui;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import ServerClient.ClientConsole;
import controllers.AdminController;
import controllers.SecretaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import projectsalmon.*;
import javafx.fxml.Initializable;

/**
 * This GUI controller is responsible for the stage of
 * the the system administrator's "Define Course" functionality.
 * In this screen the user provides information and attempts to 
 * create a new course in the system.  
 *
 * @see AdminController
 * @author Elia
 */
public class DefineCourseController {

	@FXML
	private TextField nametxtFX;

	@FXML
	private TextField teachingtxtFX;

	@FXML
	private Label teachingUnitFX;

	@FXML
	private Label requestFX2;
	

	@FXML
	private Label requestFX3;

	@FXML
	private Label requestFX1;

	@FXML
	private Button exitPT;

	@FXML
	private Label preconditionsFX;

	@FXML
	private Label weeklyHoursFX;

	@FXML
	private Button mainMenuFX;
	
	@FXML
	private TextField preconditionstxtFX;

	@FXML
	private Pane paneFX;

	@FXML
	private Button continuePT;

	@FXML
	private TextField hourstxtFX;

	@FXML
	private Label courseNameFX;

	
	
	/**
	 * Makes sure that all the required fields were filled and
	 * provides the given parameters to AdminController  
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void nextFrame(ActionEvent event) throws IOException {

		if (nametxtFX.getText().equals("") || hourstxtFX.getText().equals("") || teachingtxtFX.getText().equals("")) 
		{
			requestFX1.setText("Please fill all the fields and try again.");
		}
		else
		{
			
			String result = AdminController.setNewCourse(nametxtFX.getText(), hourstxtFX.getText(), teachingtxtFX.getText(),preconditionstxtFX.getText());
			if (result == "teaching unit")
				requestFX1.setText("The chosen teaching unit doesn't exist.");

			else 
			{
				if(result == null)
				{
					nametxtFX.setVisible(false);
					teachingtxtFX.setVisible(false);
					teachingUnitFX.setVisible(false);
					requestFX2.setVisible(false);
					exitPT.setVisible(false);
					preconditionsFX.setVisible(false);
					weeklyHoursFX.setVisible(false);
					preconditionstxtFX.setVisible(false);
					continuePT.setVisible(false);
					hourstxtFX.setVisible(false);
					courseNameFX.setVisible(false);

					requestFX1.setText("Couldn't define new course (DB error");
					mainMenuFX.setVisible(true);
				}
				else{
					if (result == "preconditions")

						requestFX1.setText("The chosen precondition courses don't exist.");

					else {
						// continue regularly
						nametxtFX.setVisible(false);
						teachingtxtFX.setVisible(false);
						teachingUnitFX.setVisible(false);
						requestFX2.setVisible(false);
						exitPT.setVisible(false);
						preconditionsFX.setVisible(false);
						weeklyHoursFX.setVisible(false);
						preconditionstxtFX.setVisible(false);
						continuePT.setVisible(false);
						hourstxtFX.setVisible(false);
						courseNameFX.setVisible(false);

						requestFX1.setText("Course was created successfully.");
						mainMenuFX.setVisible(true);
					}
				}
			}
		}
	}

	
	 /**
     *  Return to the main menu of the user.
     */
	@FXML
	void exit(ActionEvent event) throws IOException 
	{
		((Node) event.getSource()).getScene().getWindow().hide();
		SecretaryController.assignClassToCourseEXIT(1);
	}

	}

