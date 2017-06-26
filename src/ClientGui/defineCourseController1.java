package ClientGui;


import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ServerClient.ClientConsole;
import controllers.AdminController;
import controllers.SecretaryController;
import controllers.StudentsClassController;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import projectsalmon.*;
import projectsalmon.StudentsClass;
import javafx.fxml.Initializable;


	public class defineCourseController1 {

		   @FXML
		    private TextField nametxtFX;

		    @FXML
		    private TextField teachingtxtFX;

		    @FXML
		    private Label teachingUnitFX;

		    @FXML
		    private Label requestFX2;

		    @FXML
		    private Label requestFX1;

		    @FXML
		    private Button exitPT;

		    @FXML
		    private Label preconditionsFX;

		    @FXML
		    private Label weeklyHoursFX;

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

	    
	    @FXML
	    void nextFrame(ActionEvent event) {

	    	if(nametxtFX.getText() == null || hourstxtFX.getText() == null || teachingtxtFX.getText() == null)
	    	{
	    		requestFX1.setText("Please fill all the fields and try again.");
	    	}
	    
	    	AdminController.setNewCourse(courseNameFX.getText(), weeklyHoursFX.getText(), teachingUnitFX.getText(), preconditionstxtFX.getText());
	    }

	    @FXML
	    void exit(ActionEvent event) throws IOException 
	    {
	    	((Node)event.getSource()).getScene().getWindow().hide();
	    	SecretaryController.assignClassToCourseEXIT(1);
	    }

	}
	
	
