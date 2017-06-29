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
	void defineNewAssignment(ActionEvent event)
	{
		/**
		 * Given that the information presented belongs to the current semester,
		 * the teacher is given the option to add a new assignment to the course.
		 * 
		 * Since the defining of the new assignment occurs in a new window, in order to be able to
		 * communicate with the new window, we create an instance of the window's controller,
		 * and transfer information throw it's constructor.
		 */
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefineAssignment.fxml"));     
			DefineAssignmentController controller = new DefineAssignmentController(course,this);
			fxmlLoader.setController(controller);
			Parent root = (Parent)fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
	        stage.setTitle("New assignment");
	        	  
	        stage.show();
	        }catch(IOException e){e.printStackTrace();}
	}
	@FXML
	void openAssignment(Event event)
	{
		/**
		 * When clicking on an assignment, it's details are presented in a new tab.
		 * In the new tab, the detail are only editable if the semester is the current one
		 */
		manager.setLatestSelection(assignmentsList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignmentTabs.fxml"));
    	TeacherAssignmentController controller = new TeacherAssignmentController(assignmentsList.getSelectionModel().getSelectedItem(),this);
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

	public void addAssignment(Assignment assignment)
	{
		/**
		 * This method is used by the "DefineNewAssignment" controller in order to add the newly defined assignment
		 * to the list.
		 */
		data.add(assignment);
		assignmentsList.setItems(data);
	}
	public void updateAssignment(Assignment assignment)
	{
		/**
		 * This method is used by the "EditAssignment" controller in order to update a changed assignment in the list.
		 */
		for(Assignment a : data)
			if(a.getAssignmntId().equals(assignment.getAssignmntId()))
			{
				a = assignment;
				break;
			}
		assignmentsList.setItems(null);
		assignmentsList.setItems(data);
	}
	public void deleteAssignment(Assignment assignment)
	{
		/**
		 * This method is used by the "EditAssignment" controller in order to remove an assignment in the list.
		 */
		for(Assignment a : data)
			if(a.getAssignmntId().equals(assignment.getAssignmntId()))
			{
				data.remove(a);
				break;
			}
		assignmentsList.setItems(data);
	}
}
