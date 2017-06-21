package ClientGui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import projectsalmon.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CoursesTabController implements Initializable
{
	Main myMain = Main.getInstance();
	
	@FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML
    private ListView<Course> coursesMenu;
    
    @FXML
    private Button closeBtn;
    
    private ObservableList<Course> data;

    private ArrayList<Course> courses;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//courses = 
		data = FXCollections.observableArrayList();
        coursesMenu.setItems(data);
	}
	@FXML
    void closeTab(ActionEvent event)
	{
		((TabPane)(pane.getParent().getParent())).getTabs().remove(Teacher.getTab());
    }
	 @FXML
	    void openCourse(ActionEvent event)
	 {
		 
		 
	 }
    
}
