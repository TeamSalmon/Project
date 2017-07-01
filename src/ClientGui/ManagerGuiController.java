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
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewClassesGui.fxml"));
        Tab viewClassesTab = new Tab("View classes");
        manager.getContainer().getTabs().add(viewClassesTab);
        try {viewClassesTab.setContent(loader.load());} 
        catch (IOException e){e.printStackTrace();}
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
        Tab reportTabs = new Tab("Statistical reports");
        manager.getContainer().getTabs().add(reportTabs);
        try {reportTabs.setContent(loader.load());} 
        catch (IOException e){e.printStackTrace();}
    }
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		manager = TabManager.getInstance();
		manager.setContainer(container);
		myMain = Main.getInstance();
		mainTab.setText(myMain.getUser().getFirst_name()+" " + myMain.getUser().getLast_name());

		//Since the default semester for presenting is the current one, we have to get it from the DB:
		 
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
		presentedSemester = new Semester(answer.get(0), answer.get(1));
		manager.setCurrentSemester(presentedSemester);
		
		//Get semesters:
		arrsend = new ArrayList<String>();
		arrsend.add("getSemesters");
		arrsend.add(myMain.getUser().getId());
		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con)
    	{
    		try {Main.con.wait();} catch (InterruptedException e){e.printStackTrace();}
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
	}
}