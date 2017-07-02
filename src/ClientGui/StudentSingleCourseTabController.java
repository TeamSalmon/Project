package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import projectsalmon.Assignment;
import projectsalmon.Course;

/**
 * Controller of the window presenting a single course information, such as assignments defined in the specific course
 */
public class StudentSingleCourseTabController implements Initializable
{
	Main myMain = Main.getInstance();
	TabManager manager = TabManager.getInstance();
	
    @FXML
    private ListView<Assignment> assignmentsList;
    @FXML
    private AnchorPane pane;
    private Tab current;
    private ArrayList<Assignment> assignments;
    private ObservableList<Assignment> data;
	private Tab singleAssignmentTab;
	private Course course;
	

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		current = manager.getContainer().getTabs().get( manager.getContainer().getTabs().size()-1);
		current.setText(((Course)manager.getLatestSelection()).toString());
		course = (Course)manager.getLatestSelection();
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getCourseAssignments");
		arrsend.add(course.getCourseNumber());
		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con) {
    		
    		try {
				Main.con.wait();
			} catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	ArrayList<ArrayList<String>> answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		assignments = new ArrayList<Assignment>();
		if(answer2!=null)
			for(ArrayList<String> assignment : answer2)
			{
				assignments.add(new Assignment(assignment.get(0),assignment.get(1),assignment.get(2),assignment.get(3),assignment.get(5)));
			}
		
		data = FXCollections.observableArrayList();
		for(Assignment a : assignments)
			data.add(a);
        assignmentsList.setItems(data);
        manager.getContainer().getSelectionModel().select(current);
	}
	@FXML
	void openAssignment(MouseEvent event)
	{
		/**
		 * When clicking on an assignment, it's details are presented in a new tab.
		 * In the new tab, the detail are only editable if the semester is the current one
		 */
		if(assignmentsList.getSelectionModel().getSelectedItem()!=null)
		{
			manager.setLatestSelection(assignmentsList.getSelectionModel().getSelectedItem());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentSubmissionTab.fxml"));
	        singleAssignmentTab = new Tab("View assignment");
	        manager.getContainer().getTabs().add(singleAssignmentTab);
	        try {
				singleAssignmentTab.setContent(loader.load());
			} catch (IOException e){e.printStackTrace();}
		}
	}
}
