/**
 * Sample Skeleton for 'TeacherGui.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import projectsalmon.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
/**
 * this controller is responsible for the first screen a teacher sees after logging in
 * @author inbar
 */
public class TeacherGuiController implements Initializable{

	Main myMain = Main.getInstance();
	TabManager manager = TabManager.getInstance();
	
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
    private Semester currentSemester;
    private Semester presentedSemester;
	private ObservableList<Semester> semesterList;
	private ObservableList<Course> data;
    private ArrayList<Course> courses;
    private ArrayList<Semester> semesters;

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
    	presentedSemester = semesterChoice.getSelectionModel().getSelectedItem();
    	
    	/**
    	 * Getting information according to the chosen semester from the DB:
    	 */
    	ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("courseByTeacher");
		arrsend.add(myMain.getUser().getId());
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
		
		myMain.con.handleMessageFromClientUI(arrsend);
		courses = (ArrayList<Course>)myMain.con.getMessage();
		
		/**
		 * Presenting the information:
		 */
		data = FXCollections.observableArrayList();
		if(courses!=null)
		{
		for(Course c : courses)
			data.add(c);
		
        coursesList.setItems(data);
		}
		/**
		 * Making sure the information is only editable for the current semester:
		 */
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
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherSingleCourseTab.fxml"));
        singleCourseTab = new Tab(((Course)(coursesList.getSelectionModel().getSelectedItem())).getName());
        manager.getContainer().getTabs().add(singleCourseTab);
        try {singleCourseTab.setContent(loader.load());} 
        catch (IOException e) {e.printStackTrace();}  
    	}
    }
    
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		manager.setContainer(container);
		manager.setEditable(true);
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());
<<<<<<< HEAD

		/**
		 * Since the default semester for presenting is the current one, we have to get it from the DB:
		 */
=======
		
		// Since the default semester for presenting is the current one, we have to get it from the DB:
		 
>>>>>>> working-ArrayList-String-motherfucker
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("CurrentSemester");
		myMain.con.handleMessageFromClientUI(arrsend);
		currentSemester = (Semester)myMain.con.getMessage();
		presentedSemester = currentSemester;
		
		
		//Getting from the DB all relevant semesters to the teacher (semesters in which he/she was active in the system):
		
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
		
<<<<<<< HEAD
		// Getting the information matching the semester:
=======
		 //Getting the information matching the semester:
>>>>>>> working-ArrayList-String-motherfucker
		
		arrsend = new ArrayList<String>();
		arrsend.add("courseByTeacher");
		arrsend.add(myMain.getUser().getId());
		arrsend.add(presentedSemester.getYear());
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
