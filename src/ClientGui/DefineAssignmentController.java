package ClientGui;

import java.io.File;
import java.net.URL;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projectsalmon.Assignment;

public class DefineAssignmentController implements Initializable
{
	@FXML
	private Label nameError;
	@FXML
	private Label percentageError;
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
    private TextField precentageField;
    @FXML
    private TextField uploadField;
    @FXML
    private Button doneBtn;
    @FXML
    private DatePicker deadlineField;
    @FXML
    private Button cancelBtn;
    private String assignmentFilePath;
    private Assignment assignment;
    private String courseNum;
    private String courseName;
    private SingleCourseTabController parentController;

    public DefineAssignmentController(String courseNum, String courseName, SingleCourseTabController parentController)
    {
    	this.courseName = courseName;
    	this.courseNum = courseNum;
    	this.parentController = parentController;
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
    	File file = new File(assignmentFilePath);
    	assignment = new Assignment("1",courseNum,nameField.getText(),cal,Integer.parseInt(precentageField.getText()),instructionsField.getText(),file);
    	//call controllers to make sure everything is ok
    	//save the assignment in db
    	parentController.addAssignment(assignment);
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
		title.setText("Define assignment in " + courseName);
	}
    @FXML
    void deleteAssignment(ActionEvent event){/*Nothing to see here. move on.*/}
    
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
    		deadlineError.setVisible(false);
    	try{
    	if (precentageField.getText() == null || precentageField.getText().trim().isEmpty() || Integer.parseInt(precentageField.getText())<0 || Integer.parseInt(precentageField.getText())>100)
    	{
    	     percentageError.setVisible(true);
    	     flag = false;
    	}
    	else
    		percentageError.setVisible(false);
    	}
    	catch(NumberFormatException e){percentageError.setVisible(true); flag = false;}
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
