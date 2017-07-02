

package entities;


import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


public class CreateClassController implements Serializable{
	
	// Continue to next screen
	@FXML private Label gradeLabel;
	
	// Select a grade for the new class
	@FXML private ComboBox<String> grade;
	
	// Continue to next screen
	@FXML private Button ok;
	
	
	public void comboBoxUpdated()
	{
		gradeLabel.setText( "Selected grade:	" + grade.getValue().toString() );

	}
	

	@FXML void run(ActionEvent event) throws IOException   
	{
		gradeLabel.setText("Please select a grade level for the new class");
	
		grade.getItems().addAll("9th grade", "10th grade", "11th grade", "12th grade", "Mixed");

	}
	
}
