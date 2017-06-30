package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import projectsalmon.Student;
	
public class ParentGuiController implements Initializable
{
	@FXML
    private TabPane container;
    @FXML
    private ListView<Course> coursesList;
    @FXML
    private Tab mainTab;
    @FXML
    private ComboBox<Student> childChoice;
    @FXML
    private ComboBox<Semester> semesterChoice;
    @FXML
    private Button goBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button personalFileBtn;
    private Main myMain;
    private TabManager manager;
    private Semester presentedSemester;
	private ObservableList<Semester> semesterList;
	private ObservableList<Course> data;
    private ArrayList<Course> courses;
    private ArrayList<Semester> semesters;
    private ArrayList<Student> children;
    private ObservableList<Student> cdata;
    private Student presentedChild;
    private Tab personalFileTab;
    private Tab singleCourseTab;

    @FXML
    void openSingleCourseTab(ActionEvent event)
    {
    	/**
    	 * Once a course was chosen from the courses list, a new tab opens- containing information about said course:
    	 */
    	if(coursesList.getSelectionModel().getSelectedItem()!=null)
    	{
    	manager.setLatestSelection(coursesList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentSingleCourseTab.fxml"));
        singleCourseTab = new Tab(((Course)(coursesList.getSelectionModel().getSelectedItem())).getName());
        manager.getContainer().getTabs().add(singleCourseTab);
        try {singleCourseTab.setContent(loader.load());} 
        catch (IOException e) {e.printStackTrace();}  
    	}
    }
    @SuppressWarnings("unchecked")
	@FXML
    void changeSemesterOrChild(ActionEvent event)
    {
    	/**
    	 * A teacher has the option to change the semester of which the information is presented to him
    	 * The default semester for presentation is the current one
    	 * According to the demand, a teacher is able to edit information of the current semester
    	 * Once a teacher goes on to a different semester than the current one, the information becomes uneditable 
    	 */
    	presentedSemester = semesterChoice.getSelectionModel().getSelectedItem();
    	presentedChild = childChoice.getSelectionModel().getSelectedItem();
    	
    	//Getting information according to the chosen semester and child from the DB:
    	 
    	ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("courseByStudent");
		arrsend.add(presentedChild.getId());
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
		
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);} 
		catch (IOException e){e.printStackTrace();}
		courses = (ArrayList<Course>)myMain.con.getMessage();
		
		//Presenting the information:
		 
		data = FXCollections.observableArrayList();
		if(courses!=null)
		{
		for(Course c : courses)
			data.add(c);
		
        coursesList.setItems(data);
		}
    }
    @FXML
    void openPersonalFile(ActionEvent event)
    {
    	manager.setLatestSelection(presentedChild);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentPersonalFile.fxml"));
        personalFileTab = new Tab("Personal file");
        manager.getContainer().getTabs().add(personalFileTab);
        try {personalFileTab.setContent(loader.load());} 
        catch (IOException e) {e.printStackTrace();}
    }
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		manager.setContainer(container);
		manager.setEditable(false);
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());
		
		// Since the default semester for presenting is the current one, we have to get it from the DB:
		 
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getChildren");
		arrsend.add(myMain.getUser().getId());
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		children = (ArrayList<Student>)myMain.con.getMessage();
		
		presentedChild = children.get(0);
		cdata = FXCollections.observableArrayList();
		for(Student s : children)
			cdata.add(s);
		childChoice.setItems(cdata);
		
		arrsend = new ArrayList<String>();
		arrsend.add("CurrentSemester");
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		presentedSemester = (Semester)myMain.con.getMessage();		
		
		//Getting from the DB all relevant semesters to the child (semesters in which he/she was active in the system):
		
		arrsend = new ArrayList<String>();
		arrsend.add("getSemesters");
		arrsend.add(presentedChild.getId());
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e){e.printStackTrace();}
		semesters = (ArrayList<Semester>)myMain.con.getMessage();
		
		semesterList = FXCollections.observableArrayList();
		if(semesters!=null)
		{
		for(Semester s : semesters)
			semesterList.add(s);
		semesterChoice.setItems(semesterList);
		}
		
		 //Getting the information matching the semester:
		
		arrsend = new ArrayList<String>();
		arrsend.add("courseByStudent");
		arrsend.add(presentedChild.getId());
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
		
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);} 
		catch (IOException e){e.printStackTrace();}
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
