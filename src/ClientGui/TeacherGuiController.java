/**
 * Sample Skeleton for 'TeacherGui.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import ServerClient.ClientConsole;
import projectsalmon.*;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TeacherGuiController implements Initializable{

	Main myMain = Main.getInstance();
	TabManager manager = TabManager.getInstance();
	
    @FXML
    private Button scheduleBtn;
	@FXML
    private TabPane container;
	@FXML
	private ListView<String> coursesList;
    @FXML
    private Tab mainTab;
    @FXML
    private ComboBox<Semester> semesterChoice;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button goBtn;
    private static Tab singleCourseTab;
    private ObservableList<String> data;
    private ArrayList<Course> courses;
    private ArrayList<Semester> semesters;
    private Semester currentSemester;
    private Semester presentedSemester;
	private ObservableList<Semester> semesterList;
	private Object object;

    @FXML
    void changeSemester(ActionEvent event)
    {        
    	presentedSemester = semesterChoice.getSelectionModel().getSelectedItem();
    	
    	//get courses according to chosen semester
    	ArrayList<String> askDB = new ArrayList<String>();
		askDB.add(myMain.getUser().getId());
		//add more parameters 
		
		/*try
		{
			courses = (ArrayList<Course>)myMain.con.getClient().handleMessageFromClientUI(askDB);
		}
		catch(IOException e)
		{
			ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
		}*/
    	data = FXCollections.observableArrayList();
		for(Course c : courses)
			data.add(c.getName()+ " " + c.getCourseNumber());
        coursesList.setItems(data);
        
        if(presentedSemester == currentSemester)
        	manager.setEditable(true);
        else
        	manager.setEditable(false);
    }
    @FXML
    void openSchedule(ActionEvent event)
    {
    	manager.setLatestSelection(coursesList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleGui.fxml"));
        singleCourseTab = new Tab("Schedule");
        manager.getContainer().getTabs().add(singleCourseTab);
        try {
			singleCourseTab.setContent(loader.load());
		} catch (IOException e) {e.printStackTrace();}  
    }
    @FXML
    void openSingleCourseTab(Event event)
    {
    	manager.setLatestSelection(coursesList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherSingleCourseTab.fxml"));
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
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());
		
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("CurrentSemester");
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		myMain.con.getAnswer(object);
		currentSemester = (Semester)object;
		
		presentedSemester = currentSemester;
		
		arrsend = new ArrayList<String>();
		arrsend.add("getSemesters");
		arrsend.add(myMain.getUser().getId());
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		myMain.con.getAnswer(object);
		semesters = (ArrayList<Semester>)object;
		
		semesterList = FXCollections.observableArrayList();
		if(semesters!=null)
		{
		for(Semester s : semesters)
			semesterList.add(s);
		semesterChoice.setItems(semesterList);
		}
		/*
		arrsend = new ArrayList<String>();
		currentSemester.setYear("2017");
		currentSemester.setSemesterNumber("A");
		arrsend.add("courseByTeacher");
		arrsend.add(myMain.getUser().getId());
		arrsend.add(currentSemester.getSemesterNumber());
		
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		myMain.con.getAnswer(courses);
		
		data = FXCollections.observableArrayList();
		for(Course c : courses)
			data.add(c.getName());
		
        coursesList.setItems(data);*/
	}
}
