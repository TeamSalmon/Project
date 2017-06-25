package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ServerClient.ClientConsole;
import projectsalmon.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SingleCourseTabController implements Initializable
{
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
    private String courseNum;
    private String courseName;
    private ArrayList<Assignment> assignments;
    private ObservableList<Assignment> data;
	private static Tab singleAssignmentTab;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		current = manager.getContainer().getTabs().get( manager.getContainer().getTabs().size()-1);
		current.setText((String)manager.getLatestSelection());
		courseNum = ((String)(manager.getLatestSelection())).split(" ")[1];
		courseName = ((String)(manager.getLatestSelection())).split(" ")[0];
		
		/*
		ArrayList<String> askDB = new ArrayList<String>();
		askDB.add(myMain.getUser().getId());
		//add more parameters 
		
		try
		{
			
		}
		catch(IOException e)
		{
			ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
		}
		
		data = FXCollections.observableArrayList();
		
		for(Course c : courses)
			data.add(c);
		
        coursesList.setItems(data);*/
		data = FXCollections.observableArrayList();
        assignmentsList.setItems(data);
		manager.getContainer().getSelectionModel().select(current);
	}
	@FXML
	void defineNewAssignment(ActionEvent event)
	{
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefineAssignment.fxml"));     
			DefineAssignmentController controller = new DefineAssignmentController(courseNum,courseName,this);
			fxmlLoader.setController(controller);
			Parent root = (Parent)fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
	        stage.setTitle("new assignment");
	        	  
	        stage.show();
	        }
	        catch(IOException e){e.printStackTrace();}
	}
	@FXML
	void openAssignment(Event event)
	{
		manager.setLatestSelection(assignmentsList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignmentTabs.fxml"));
    	AssignmentController controller = new AssignmentController(assignmentsList.getSelectionModel().getSelectedItem(),this);
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
		data.add(assignment);
		assignmentsList.setItems(data);
	}
	public void updateAssignment(Assignment assignment)
	{
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
		//remove assignment from db
		for(Assignment a : data)
			if(a.getAssignmntId().equals(assignment.getAssignmntId()))
			{
				data.remove(a);
				break;
			}
		assignmentsList.setItems(data);
	}
}
