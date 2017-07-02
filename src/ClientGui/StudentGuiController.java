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
import javafx.scene.input.MouseEvent;
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
	private Tab singleCourseTab;
	private Tab personalFileTab;
	private ObservableList<Course> data;
	private ArrayList<Course> courses;
	private Semester currentSemester;
	private Semester presentedSemester;
	private ObservableList<Semester> semesterList;
	private ArrayList<Semester>semesters;

	@SuppressWarnings("unchecked")
	@FXML
	void changeSemester(ActionEvent event)
    {        
		/**
    	 * A teacher has the option to change the semester of which the information is presented to him
    	 * The default semester for presentation is the current one
    	 * According to the demand, a teacher is able to edit information of the current semester
    	 * Once a teacher goes on to a different semester than the current one, the information becomes uneditable 
    	 */
    	if(semesterChoice.getSelectionModel().getSelectedItem()!=null)
    	{
    	presentedSemester = semesterChoice.getSelectionModel().getSelectedItem();
    	
    	ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("courseByStudent");
		arrsend.add(myMain.getUser().getId());
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
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
    	ArrayList<ArrayList<String>> answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		courses = new ArrayList<Course>();
		if(answer2!=null)
			for(ArrayList<String> course : answer2)
			{
				courses.add(new Course(course.get(0),course.get(1),course.get(2),Float.parseFloat(course.get(3)),course.get(4)));
			}
		
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
	    if(presentedSemester == currentSemester)
	    	manager.setEditable(true);
	    else
	    	manager.setEditable(false);
	}
    @FXML
    void openSingleCourseTab(MouseEvent event)
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
    @FXML
    void openPersonalFile(ActionEvent event)
	{
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
		manager.setEditable(true);
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());
		
		// Since the default semester for presenting is the current one, we have to get it from the DB:
		 
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("CurrentSemester");
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
		currentSemester = new Semester(answer.get(0), answer.get(1));
		presentedSemester = currentSemester;
		manager.setCurrentSemester(currentSemester);
		
		
		//Getting from the DB all relevant semesters to the teacher (semesters in which he/she was active in the system):
		
		arrsend = new ArrayList<String>();
		arrsend.add("getSemesters");
		arrsend.add(myMain.getUser().getId());
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
    	ArrayList<ArrayList<String>> answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		semesters = new ArrayList<Semester>();
		if(answer2!=null)
			for(ArrayList<String> semester : answer2)
			{
				semesters.add(new Semester(semester.get(0),semester.get(1)));
			}
		
		semesterList = FXCollections.observableArrayList();
		for(Semester s : semesters)
			semesterList.add(s);
		semesterChoice.setItems(semesterList);

		//Getting the information matching the semester:
		
		arrsend = new ArrayList<String>();
		arrsend.add("courseByStudent");
		arrsend.add(myMain.getUser().getId());
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
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
    	answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		courses = new ArrayList<Course>();
		if(answer2!=null)
			for(ArrayList<String> course : answer2)
			{
				courses.add(new Course(course.get(0),course.get(1),course.get(2),Float.parseFloat(course.get(3)),course.get(4)));
			}
		
		data = FXCollections.observableArrayList();
		if(courses!=null)
		{
		for(Course c : courses)
			data.add(c);
		
        coursesList.setItems(data);
		}
	}
}
