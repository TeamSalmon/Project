package ClientGui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projectsalmon.Assignment;
import projectsalmon.Parent;

public class SubmissionController implements Initializable
{
    @FXML
    private Button browseBtn;
    @FXML
    private TextArea commentsField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private Label lateLabel;
    @FXML
    private Label title;
    @FXML
    private TextField uploadField;
    @FXML
    private Button saveBtn;
    private StudentSingleCourseTabController parentController;
    private Assignment assignment;
    private String submissionFilePath;
    private TabManager manager;
    private Tab current;
    private Main myMain;

    public SubmissionController(Assignment assignment, StudentSingleCourseTabController parentController)
    {
    	this.assignment = assignment;
    	this.parentController = parentController;
    }
    @FXML
    void browse(ActionEvent event)
    {
    	/**
    	 * Using fileChooser in order to upload a file
    	 */
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage s = new Stage();
    	File file = fileChooser.showOpenDialog(s);
    	submissionFilePath = file.getPath();
    	uploadField.setText(submissionFilePath);
    }
    @FXML
    void cancel(ActionEvent event)
    {
    	manager.getContainer().getTabs().remove(current);
    }
    @FXML
    void saveChanges(ActionEvent event)
    {
    	assignment.setFile(uploadField.getText());
    	
    }
    @FXML
    void close(ActionEvent event)
    {
    	manager.getContainer().getTabs().remove(current);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		myMain = Main.getInstance();
		if(myMain.getUser() instanceof Parent)
			manager.setEditable(false);
		else
			manager.setEditable(true);
		
		//uploadField.setText();
		//ADD MORE CODE!!!
		
	}
}