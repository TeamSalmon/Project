package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import projectsalmon.Course;
import projectsalmon.Semester;

public class StudentGuiController implements Initializable
{
	Main myMain = Main.getInstance();
	TabManager manager = TabManager.getInstance();

	@FXML
	private Button personalFileBtn;
	@FXML
	private TabPane container;
	@FXML
	private ListView<Course> coursesList;
	@FXML
	private Tab mainTab;
	@FXML
	private ComboBox<Semester> semesterChoice;
	@FXML
	private AnchorPane pane;
	@FXML
	private Button goBtn;
	private static Tab singleCourseTab;
	private static Tab personalFileTab;
	private ObservableList<Course> data;
	private ArrayList<Course> courses;
	private Semester currentSemester;
	private Semester presentedSemester;
	private ObservableList<Semester> semesterList;
	private ArrayList<Semester>semesters;

	@FXML
	void changeSemester(ActionEvent event)
	{        
		presentedSemester = semesterChoice.getSelectionModel().getSelectedItem();
		
		//get courses according to chosen semester
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add(myMain.getUser().getId());
		myMain.con.handleMessageFromClientUI(arrsend);
		courses = (ArrayList<Course>)myMain.con.getMessage();
		
		data = FXCollections.observableArrayList();
		if(courses!=null)
		{
		for(Course c : courses)
			data.add(c);
		
	    coursesList.setItems(data);
		}
		
	    
	    if(presentedSemester == currentSemester)
	    	manager.setEditable(true);
	    else
	    	manager.setEditable(false);
	}
    @FXML
    void openPersonalFile(ActionEvent event)
    {
    	manager.setLatestSelection(coursesList.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentPersonalFile.fxml"));
	    personalFileTab = new Tab("Personal file");
	    manager.getContainer().getTabs().add(personalFileTab);
	    try {
	    	personalFileTab .setContent(loader.load());
		} catch (IOException e) {e.printStackTrace();} 
    }
	@FXML
	void openSingleCourseTab(Event event)
	{
		manager.setLatestSelection(coursesList.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentSingleCourseTab.fxml"));
	    singleCourseTab = new Tab("new");
	    manager.getContainer().getTabs().add(singleCourseTab);
	    try {
			singleCourseTab.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		manager.setContainer(container);
		manager.setEditable(true);
		mainTab.setText(myMain.getUser().getFirst_name() + " " + myMain.getUser().getLast_name());
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getCurrentSemester");
		myMain.con.handleMessageFromClientUI(arrsend);
		currentSemester = (Semester)myMain.con.getMessage();
		presentedSemester = currentSemester;
		
		semesterList = FXCollections.observableArrayList();
		
		arrsend = new ArrayList<String>();
		arrsend.add("getSemesters");
		arrsend.add(myMain.getUser().getId());
		myMain.con.handleMessageFromClientUI(arrsend);
		semesters = (ArrayList<Semester>)myMain.con.getMessage();
		
		semesterList = FXCollections.observableArrayList();
		if(semesters!=null)
		{
		for(Semester s : semesters)
			semesterList.add(s);
		semesterChoice.setItems(semesterList);
		}
		
		arrsend = new ArrayList<String>();
		arrsend.add("courseByStudent");
		arrsend.add(myMain.getUser().getId());
		arrsend.add(currentSemester.getSemesterNumber());
		myMain.con.handleMessageFromClientUI(arrsend);
		courses = (ArrayList<Course>)myMain.con.getMessage();
		
		data = FXCollections.observableArrayList();
		if(courses!=null)
		{
		for(Course c : courses)
			data.add(c);
		
	    coursesList.setItems(data);
		}
	}
}
