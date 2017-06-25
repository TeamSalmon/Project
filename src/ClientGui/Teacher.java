/**
 * Sample Skeleton for 'TeacherGui.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Teacher implements Initializable{

	Main myMain = Main.getInstance();
	
	  @FXML // fx:id="container"
	    private TabPane container; // Value injected by FXMLLoader

	    @FXML // fx:id="coursesBtn"
	    private Button coursesBtn; // Value injected by FXMLLoader

	    @FXML // fx:id="firstTab"
	    private Tab firstTab; // Value injected by FXMLLoade
	    
	    private static Tab coursesTab;

    @FXML
    void openCourses(ActionEvent event)
    {		 
    	if(!container.getTabs().contains(coursesTab))
    	{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursesTab.fxml"));
        coursesTab = new Tab("Courses");
        try {
			coursesTab.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        container.getTabs().add(coursesTab);
    	}   
    	else {
    		container.getSelectionModel().select(coursesTab);
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//firstTab.setText(myMain.getUser().getName());	
	}
	public static Tab getTab(){return coursesTab;}

}
