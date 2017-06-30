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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import projectsalmon.Course;
import projectsalmon.Semester;

public class ManagerGuiController implements Initializable
{
    @FXML
    private TabPane container;

    @FXML
    private Tab mainTab;
    @FXML
    private Button viewTeachersBtn;
    @FXML
    private Button blockParentBtn;
    @FXML
    private Button viewTeachingUnitsBtn;
    @FXML
    private ComboBox<Semester> semesterChoice;
    @FXML
    private Button viewReportsBtn;
    @FXML
    private Button goBtn;
    @FXML
    private Button unblockParentBtn;
    @FXML
    private Button viewCourseBtn;
    @FXML
    private Button viewClassesBtn;
    private TabManager manager;
    private Main myMain;
    private ArrayList<Semester> semesters;
    private ObservableList<Semester> semesterList;
    private Semester presentedSemester;
    private Semester currentSemester;
    private Tab reportTabs;

    @FXML
    void openBlockTab(ActionEvent event)
    {
    	
    }
    @FXML
    void changeSemester(ActionEvent event)
    {
    	
    }
    @FXML
    void openUnblockBtn(ActionEvent event)
    {
    	
    }
    @FXML
    void openCoursesTab(ActionEvent event)
    {
    	
    }
    @FXML
    void openClassesTab(ActionEvent event)
    {
    	
    }
    @FXML
    void openTeachingUnitsTabs(ActionEvent event)
    {
    	
    }
    @FXML
    void openTeachersTab(ActionEvent event)
    {
    	
    }
    @FXML
    void openReportTabs(ActionEvent event)
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("reportMenu.fxml"));
        reportTabs = new Tab("Statistical reports");
        manager.getContainer().getTabs().add(reportTabs);
        try {reportTabs.setContent(loader.load());} 
        catch (IOException e){e.printStackTrace();}
    }
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager.setContainer(container);
		manager.setEditable(true);
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());

		//Since the default semester for presenting is the current one, we have to get it from the DB:
		 
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("CurrentSemester");
		myMain.con.handleMessageFromClientUI(arrsend);
		currentSemester = (Semester)myMain.con.getMessage();
		presentedSemester = currentSemester;
		
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
	}
}