package ClientGui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projectsalmon.Assignment;
import projectsalmon.StudentAssignment;

public class TeacherSubmissionController implements Initializable
{
	@FXML
	private Button browseBtn;
	@FXML
	private TextArea commentsField;
	@FXML
	private Hyperlink submissionLink;
	@FXML
	private Button cancelBtn;
	@FXML
	private Button closeBtn;
	@FXML
	private Label lateLabel;
	@FXML
	private Label studentsName;
	@FXML
	private Label title;
	@FXML
	private TextField uploadField;
	@FXML
	private TextField gradeField;
	@FXML
	private Button saveBtn;
    private TabManager manager;
    private static Tab current;
    private StudentAssignment studentAssignment;
    private String evaluationFormPath;

    @FXML
    void openSubmissionFile(ActionEvent event)
    {
    	
    }
    @FXML
    void browse(ActionEvent event)
    {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage s = new Stage();
    	File file = fileChooser.showOpenDialog(s);
    	evaluationFormPath = file.getPath();
    	uploadField.setText(evaluationFormPath);
    }
    @FXML
    void cancel(ActionEvent event)
    {
    	manager.getSubContainer().getTabs().remove(current);
    }
    @FXML
    void saveChanges(ActionEvent event)
    {
    	
    }
    @FXML
    void close(ActionEvent event)
    {
    	manager.getSubContainer().getTabs().remove(current);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		current = manager.getSubContainer().getTabs().get(manager.getSubContainer().getTabs().size()-1);
		studentAssignment = (StudentAssignment)manager.getLatestSelection();
		
		current.getTabPane().getSelectionModel().select(current);
	}
}
