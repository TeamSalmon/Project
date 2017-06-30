package ClientGui;

import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import projectsalmon.Student;
import projectsalmon.StudentAssignment;

public class StudentPersonalFileController implements Initializable
{
    @FXML
    private ListView<StudentAssignment> assignmentsList;
    @FXML
    private Button closeBtn;
    @FXML
    private Label avgField;
    private TabManager manager;
    private Main myMain;
    private ArrayList<StudentAssignment> studentAssignments;
    private ObservableList<StudentAssignment> data;
    private Student student;
    
    @FXML
    void StudentShowAssignment(MouseEvent event)
    {
    	
    }
    @FXML
    void closeTab(ActionEvent event)
    {
    	
    }
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		myMain = Main.getInstance();
		student = (Student)manager.getLatestSelection();
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getEvaluatedAssignments");
		arrsend.add(student.getId());
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		studentAssignments = (ArrayList<StudentAssignment>)myMain.con.getMessage();
		if(studentAssignments != null)
		{
		for(StudentAssignment st : studentAssignments)
			data.add(st);
		data = FXCollections.observableArrayList();
		assignmentsList.setItems(data);
		}
	}
}
