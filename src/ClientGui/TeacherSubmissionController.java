package ClientGui;

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

    @FXML
    void openSubmissionFile(ActionEvent event) {

    }

    @FXML
    void browse(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event){    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		current = manager.getSubContainer().getTabs().get(manager.getSubContainer().getTabs().size()-1);
		studentAssignment = (StudentAssignment)manager.getLatestSelection();
		current.getTabPane().getSelectionModel().select(current);
	}
}
