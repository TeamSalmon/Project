/**
 * Sample Skeleton for 'TeacherGui.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class Teacher implements Initializable{

	Main myMain = Main.getInstance();
	
	  @FXML // fx:id="container"
	    private TabPane container; // Value injected by FXMLLoader

	    @FXML // fx:id="coursesBtn"
	    private Button coursesBtn; // Value injected by FXMLLoader

	    @FXML // fx:id="firstTab"
	    private Tab firstTab; // Value injected by FXMLLoade


    @FXML
    void openCourses(ActionEvent event)
    {		 
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursesTab.fxml"));
        Tab coursesTab = new Tab("Courses");
        try {
			coursesTab.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        container.getTabs().add(coursesTab);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//firstTab.setText(myMain.getUser().getName());	
	}

}
