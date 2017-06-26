package ClientGui;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import projectsalmon.StudentAssignment;

public class StudentPersonalFileController implements Initializable
{
    @FXML
    private ListView<StudentAssignment> assignmentsList;
    @FXML
    private Button closeBtn;
    @FXML
    private Label avgField;
    
    @FXML
    void StudentShowAssignment(ActionEvent event)
    {
    	
    }
    @FXML
    void closeTab(ActionEvent event)
    {
    	
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
			
	}
}
