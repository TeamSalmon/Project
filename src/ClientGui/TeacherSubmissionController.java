package ClientGui;

import java.awt.Desktop;
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
import projectsalmon.Semester;
import projectsalmon.Student;
import projectsalmon.StudentAssignment;

public class TeacherSubmissionController implements Initializable
{
    @FXML
    private Label gradeError;
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
    @FXML
    private Label fileError;
    private TabManager manager;
    private static Tab current;
    private StudentAssignment studentAssignment;
    private Student student;
    private String evaluationFormPath;
	private Main myMain;

    @FXML
    void openSubmissionFile(ActionEvent event)
    {
    	Desktop dt = Desktop.getDesktop();
        try {
			dt.open(studentAssignment.getSubmission());
		} catch (IOException e){e.printStackTrace();}
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
    	if(checkFields())
    	{
    		studentAssignment.setGrade(Integer.parseInt(gradeField.getText()));
        	if(commentsField.getText()!=null)
        		studentAssignment.setComments(commentsField.getText());
        	else 
        		studentAssignment.setComments("");
        	studentAssignment.setEvaluationForm(new File(uploadField.getText()));
    	}
    }
    @FXML
    void close(ActionEvent event)
    {
    	manager.getSubContainer().getTabs().remove(current);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		myMain = Main.getInstance();
		manager = TabManager.getInstance();
		current = manager.getSubContainer().getTabs().get(manager.getSubContainer().getTabs().size()-1);
		studentAssignment = (StudentAssignment)manager.getLatestSelection();
		
		current.getTabPane().getSelectionModel().select(current);
		
		title.setText(studentAssignment.getAssignment().getName());
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("searchStudentID");
		arrsend.add(studentAssignment.getStudentId());
		try {
			myMain.con.getClient().handleMessageFromClientUI((Object)arrsend);
		} catch (IOException e){e.printStackTrace();}
		student = (Student)myMain.con.getMessage();

		studentsName.setText("Students name- " + student.getFirst_name() + student.getLast_name());
		if(studentAssignment.getGrade() != -1)
			gradeField.setText(Integer.toString(studentAssignment.getGrade()));
		commentsField.setText(studentAssignment.getComments());
		uploadField.setText(studentAssignment.getEvaluationForm().getPath());
		if(studentAssignment.getLateFlag())
			lateLabel.setVisible(true);
		else
			lateLabel.setVisible(false);
	}
	public boolean checkFields()
	{
		boolean flag = true;
		
		if(Integer.parseInt(gradeField.getText())<0 || Integer.parseInt(gradeField.getText())<0)
		{
			gradeError.setVisible(true);
			flag = false;
		}
		else
			gradeError.setVisible(false);
		if (uploadField.getText() == null || uploadField.getText().trim().isEmpty() || !(new File(uploadField.getText()).exists()))
    	{
    	     fileError.setVisible(true);
    	     flag = false;
    	}
    	else
    		fileError.setVisible(false);
		return flag;
	}
}
