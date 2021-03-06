package ClientGui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projectsalmon.Assignment;

public class EditAssignmentController implements Initializable
{
	@FXML
	private Label nameError;
	@FXML
	private Label deadlineError;
	@FXML
	private Label fileError;
	@FXML
    private TextArea instructionsField;
    @FXML
    private Button browseBtn;
    @FXML
    private Label title;
    @FXML
    private TextField nameField;
    @FXML
    private TextField uploadField;
    @FXML
    private Button doneBtn;
    @FXML
    private DatePicker deadlineField;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button deleteAssignmentBtn;
    private String assignmentFilePath;
    private Assignment assignment;
    private TeacherAssignmentController parentController;

    public EditAssignmentController(Assignment assignment, TeacherAssignmentController parentController)
    {
    	this.assignment = assignment;
    	this.parentController = parentController;
    }
    @FXML
    void deleteAssignment(ActionEvent event)
    {
    	ArrayList<String> arrsend = new ArrayList<String>();
    	arrsend.add("deleteAssignment");
    	arrsend.add(assignment.getAssignmntId());
    	try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con){try {Main.con.wait();} catch (InterruptedException e){e.printStackTrace();}
		}
    	parentController.deleteAssignment(assignment);
    	Stage stage = (Stage)cancelBtn.getScene().getWindow();
   	    stage.close();
    }
    @FXML
    void browse(ActionEvent event)
    {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage s = new Stage();
    	File file = fileChooser.showOpenDialog(s);
    	assignmentFilePath = file.getPath();
    	uploadField.setText(assignmentFilePath);
    }
    @FXML
    void done(ActionEvent event)
    {
    	if(checkFields())
    	{
    	Date date = Date.from(deadlineField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	assignment.setName(nameField.getText());
    	assignment.setDeadline(cal);
    	assignment.setInstructions(instructionsField.getText());
    	assignment.setFile(uploadField.getText());
    	
    	ArrayList<String> arrsend = new ArrayList<String>();
    	arrsend.add("editAssignment");
    	arrsend.add(assignment.getAssignmntId());
    	arrsend.add(assignment.getCourse());
    	arrsend.add(assignment.getName());
    	arrsend.add(assignment.getDeadlineAsString());
    	arrsend.add(assignment.getInstructions());
    	arrsend.add(assignment.getfile().getAbsolutePath());
    	try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con) {
    		
    		try {
				Main.con.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	parentController.updateAssignment(assignment);
    	Stage stage = (Stage)doneBtn.getScene().getWindow();
   	    stage.close();
    	}
    }
    @FXML
    void cancel(ActionEvent event)
    {
    	 Stage stage = (Stage)cancelBtn.getScene().getWindow();
    	 stage.close();
    }
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		title.setText("Edit " + assignment.getName());
		nameField.setText(assignment.getName());
		deadlineField.setValue(assignment.getDeadline().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		instructionsField.setText(assignment.getInstructions());
		deleteAssignmentBtn.setVisible(true);
		//uploadField.setText(assignment.getfile().getPath());
	}
	public boolean checkFields()
    {
    	boolean flag = true;
    	
    	if (nameField.getText() == null || nameField.getText().trim().isEmpty())
    	{
    	     nameError.setVisible(true);
    	     flag = false;
    	}
    	else
    		nameError.setVisible(false);
    	if (deadlineField.getValue() == null)
    	{
    	     deadlineError.setVisible(true);
    	     flag = false;
    	}
    	else
    	if (instructionsField.getText() == null || instructionsField.getText().trim().isEmpty())
    	{
    		instructionsField.setText("");
    	}
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
