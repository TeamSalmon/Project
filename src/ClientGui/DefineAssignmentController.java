package ClientGui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
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
import projectsalmon.Course;
import projectsalmon.Semester;

public class DefineAssignmentController implements Initializable
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
    private String assignmentFilePath;
    private Assignment assignment;
    private Course course;
    private TeacherSingleCourseTabController parentController;

    public DefineAssignmentController(Course course, TeacherSingleCourseTabController parentController)
    {
    	/**
    	 * Constructor used to get information from a previous window controller
    	 */
    	this.course = course;
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
    	assignmentFilePath = file.getPath();
    	uploadField.setText(assignmentFilePath);
    }
    @SuppressWarnings("unchecked")
	@FXML
    void done(ActionEvent event)
    {
    	/**
    	 * Getting all values from the window's fields and creating the new assignment to be added:
    	 */
    	if(checkFields())
    	{
    	Date date = Date.from(deadlineField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("createAssignment");
		arrsend.add(course.getCourseNumber());
		arrsend.add(nameField.getText());
		arrsend.add(df.format(date));
		arrsend.add(instructionsField.getText());
		arrsend.add(assignmentFilePath);
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
		ArrayList<String> answer = new ArrayList<String>();
		answer = (ArrayList<String>)Main.con.getMessage();
		assignment = new Assignment(answer.get(0),course.getCourseNumber(),nameField.getText(),cal,instructionsField.getText(),new File(assignmentFilePath));
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
		title.setText("Define assignment in " + course.getName());
	}
    @FXML
    void deleteAssignment(ActionEvent event){/*Nothing to see here. move on.*/}
    
    public boolean checkFields()
    {
    	/**
    	 * Method used to make sure all received values are valid
    	 */
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

