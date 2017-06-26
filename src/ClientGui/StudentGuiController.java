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
	private static Tab personalFileTab;
	private ObservableList<String> data;
	private ArrayList<Course> courses;
	private Semester currentSemester;
	private Semester presentedSemester;
	private ObservableList<Semester> semesterList;

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
    void openPersonalFile(ActionEvent event)
    {
    	manager.setLatestSelection(coursesList.getSelectionModel().getSelectedItem());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentPersonalFile.fxml"));
	    personalFileTab = new Tab("Personal file");
	    manager.getContainer().getTabs().add(personalFileTab);
	    try {
			singleCourseTab.setContent(loader.load());
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
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		manager.setContainer(container);
		manager.setEditable(true);
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());
		
		ArrayList arrsend = new ArrayList<String>();
		arrsend.add("getCurrentSemester");
		try {
			myMain.con.getClient().handleMessageFromClientUI(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//currentSemester = myMain.con.get;
		
		presentedSemester = currentSemester;
		semesterList = FXCollections.observableArrayList();
		//semesterList.add(new Semester("2016", 'b'));
		semesterList.add(currentSemester);
		semesterChoice.setItems(semesterList);
		
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
		//Course course = new Course("123", "Algebra",new TeachingUnit("9", "Math"),"", 8, "Algebra");
		//Course course1 = new Course("1", "Atam",new TeachingUnit("9", "Math"),"", 8, "Atam");
		//data.add(course.getName()+ " " + course.getCourseNumber());
		//data.add(course1.getName()+ " " + course1.getCourseNumber());
		
	    coursesList.setItems(data);
	}
}
