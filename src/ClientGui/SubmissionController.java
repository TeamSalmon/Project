package ClientGui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import projectsalmon.Parent;
import projectsalmon.StudentAssignment;

public class SubmissionController implements Initializable
{
    @FXML
    private Label gradeLabel;
    @FXML
    private Button browseBtn;
    @FXML
    private TextArea commentsField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label lateLabel;
    @FXML
    private Label title;
    @FXML
    private TextField uploadField;
    @FXML
    private Hyperlink evaluationFormLink;
    @FXML
    private Button saveBtn;
    private Assignment assignment;
    private StudentAssignment studentAssignment;
    private String submissionFilePath;
    private TabManager manager;
    private Tab current;
    private Main myMain;

    /**
	 * Using fileChooser in order to upload a file
	 */
    @FXML
    void browse(ActionEvent event)
    {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage s = new Stage();
    	File file = fileChooser.showOpenDialog(s);
    	submissionFilePath = file.getPath();
    	uploadField.setText(submissionFilePath);
    }
    @FXML
    void openEvaluationForm(ActionEvent event)
    {
    	
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
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		myMain = Main.getInstance();
		if(myMain.getUser() instanceof Parent)
			manager.setEditable(false);
		else
			manager.setEditable(true);
		current = manager.getContainer().getTabs().get(manager.getContainer().getTabs().size()-1);
		assignment = (Assignment)manager.getLatestSelection();
		title.setText(assignment.getName());
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getStudentAssignment");
		arrsend.add(myMain.getUser().getId());
		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con){
    		try {
				Main.con.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<String> answer = new ArrayList<String>();
		answer = (ArrayList<String>)Main.con.getMessage();
		if(answer!=null)
		{
			studentAssignment = new StudentAssignment(answer.get(1),assignment);
			studentAssignment.setSubmission(new File(answer.get(2)));
			studentAssignment.setGrade(Integer.parseInt(answer.get(3)));
			studentAssignment.setComments(answer.get(4));
			studentAssignment.setEvaluationForm(new File(answer.get(5)));
			studentAssignment.setLateFlag(Boolean.parseBoolean(answer.get(6)));
			uploadField.setText(studentAssignment.getSubmission().getAbsolutePath());
			gradeLabel.setText("Grade - " + studentAssignment.getGrade());
			commentsField.setText(studentAssignment.getComments());
			if(studentAssignment.getLateFlag())
				lateLabel.setVisible(true);
			else
				lateLabel.setVisible(false);
		}
	}
}