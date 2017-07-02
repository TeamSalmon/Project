package ClientGui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import projectsalmon.Course;
import projectsalmon.Student;

public class ViewClassTabsController implements Initializable
{
    @FXML
    private AnchorPane container;
    @FXML
    private ListView<Course> coursesList;
    @FXML
    private ListView<Student> studentsList;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}
}
