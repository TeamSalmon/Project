package ClientGui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectsalmon.Assignment;
import projectsalmon.Semester;
import projectsalmon.StudentAssignment;

public class TeacherAssignmentController implements Initializable
{
    @FXML
    private ListView<StudentAssignment> submissionsList;
    @FXML
    private TextArea instructionsField;
    @FXML
    private AnchorPane firstTabPane;
    @FXML
    private Hyperlink fileLink;
    @FXML
    private Button editBtn;
    @FXML
    private Tab secondTab;
    @FXML
    private Label deadlineLabel;
    @FXML
    private Tab firstTab;
    @FXML
    private AnchorPane secondTabPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label precentageLabel;
    @FXML
    private Button closeBtn;
    private Tab current;
    private Assignment assignment;
    private TeacherSingleCourseTabController parentController;
    private boolean editable;
    private TabManager manager;
    private ObservableList<StudentAssignment> data;
    private static Tab singleSubmissionTab;
    private Main myMain = Main.getInstance();
    private ArrayList<StudentAssignment> submissions;

    public TeacherAssignmentController(Assignment assignment, TeacherSingleCourseTabController parentController)
    {
    	this.assignment = assignment;
    	this.parentController = parentController;
    }
    @FXML
    void close(ActionEvent event)
    {
    	manager.getContainer().getTabs().remove(current);
    }
    @FXML
    void openFile(ActionEvent event)
    {
    	Desktop dt = Desktop.getDesktop();
        try {
			dt.open(assignment.getfile());
		} catch (IOException e){e.printStackTrace();}
    }
    
    @FXML
    void openEditWindow(ActionEvent event)
    {
    	try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefineAssignment.fxml"));     
			EditAssignmentController controller = new EditAssignmentController(assignment,this);
			fxmlLoader.setController(controller);
			Parent root = (Parent)fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Edit assignment");
	       	  
			stage.show();
		}
		catch(IOException e){e.printStackTrace();}
    }

	public void deleteAssignment(Assignment assignment)
	{
		parentController.deleteAssignment(assignment);
	}

	public void updateAssignment(Assignment assignment)
	{
		this.assignment = assignment;
		nameLabel.setText(assignment.getName());
		deadlineLabel.setText("Deadline - " + assignment.getDeadlineAsString());
		instructionsField.setText(assignment.getInstructions());
		fileLink.setText("Open assignment file");
		parentController.updateAssignment(assignment);
	}
    @FXML
    void showSubmission(Event event)
    {
    	manager.setLatestSelection(submissionsList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SingleSubmissionGui.fxml"));
        singleSubmissionTab = new Tab("Submission");
        tabPane.getTabs().add(singleSubmissionTab);
        try {
			singleSubmissionTab.setContent(loader.load());
		} catch (IOException e){e.printStackTrace();}
    }
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		manager.setSubContainer(tabPane);
		current = manager.getContainer().getTabs().get( manager.getContainer().getTabs().size()-1);
		this.editable = manager.getEditable();
		if(!editable)
			editBtn.setVisible(false);
		else
			editBtn.setVisible(true);
		
		nameLabel.setText(assignment.getName());
		deadlineLabel.setText("Deadline - " + assignment.getDeadlineAsString());
		instructionsField.setText(assignment.getInstructions());
		fileLink.setText("Open assignment file");
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getSubmissions");
		arrsend.add(assignment.getAssignmntId());
		try {
			myMain.con.getClient().handleMessageFromClientUI((Object)arrsend);
		} catch (IOException e){e.printStackTrace();}
		submissions = (ArrayList<StudentAssignment>)myMain.con.getMessage();

		for(StudentAssignment sa : submissions)
		{
			sa.setAssignment(assignment);
			data.add(sa);
		}
        submissionsList.setItems(data);
        
        manager.getContainer().getSelectionModel().select(current);
	}
}