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

public class StudentSingleCourseTabController implements Initializable
{
	/**
	 * Controller of the window presenting a single course information, such as assignments defined in the specific course
	 */
	Main myMain = Main.getInstance();
	TabManager manager = TabManager.getInstance();
	
    @FXML
    private ListView<Assignment> assignmentsList;
    @FXML
    private Button closeBtn;
    @FXML
    private Button newAssignmentBtn;
    @FXML
    private AnchorPane pane;
    private Tab current;
    private ArrayList<Assignment> assignments;
    private ObservableList<Assignment> data;
	private static Tab singleAssignmentTab;
	private Course course;
	

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		current = manager.getContainer().getTabs().get( manager.getContainer().getTabs().size()-1);
		current.setText(((Course)manager.getLatestSelection()).toString());
		course = (Course)manager.getLatestSelection();
		newAssignmentBtn.setVisible(false);
		Main.getTheStage().setOnCloseRequest(new EventHandler<WindowEvent>()
		{public void handle(WindowEvent we){System.out.println("Stage is closing");}});
		
		/**
		 * Getting all assignments defined in the current course:
		 */
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getCourseAssignments");
		arrsend.add(course.getCourseNumber());
		
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		assignments = (ArrayList<Assignment>)myMain.con.getMessage();
		
		data = FXCollections.observableArrayList();
		for(Assignment a : assignments)
			data.add(a);
        assignmentsList.setItems(data);
	}
	@FXML
	void openAssignment(MouseEvent event)
	{
		/**
		 * When clicking on an assignment, it's details are presented in a new tab.
		 * In the new tab, the detail are only editable if the semester is the current one
		 */
		manager.setLatestSelection(assignmentsList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SubmissionTab.fxml"));
    	SubmissionController controller = new SubmissionController(assignmentsList.getSelectionModel().getSelectedItem(),this);
    	loader.setController(controller);
        singleAssignmentTab = new Tab("View assignment");
        manager.getContainer().getTabs().add(singleAssignmentTab);
        try {
			singleAssignmentTab.setContent(loader.load());
		} catch (IOException e){e.printStackTrace();}
	}
	@FXML
    void closeTab(ActionEvent event)
	{
		manager.getContainer().getTabs().remove(current);
    }
}