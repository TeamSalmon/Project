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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import projectsalmon.Course;
import projectsalmon.Semester;
import projectsalmon.StudentsClass;

public class ViewClassesController implements Initializable
{
    @FXML
    private ListView<StudentsClass> classesList;
    @FXML
    private ComboBox<Semester> semesterChoice;
    @FXML
    private Button goBtn;
    @FXML
    private AnchorPane container;
    private TabManager manager;
    private Main myMain;
    private Tab current;
    private Semester presentedSemester;
    private ObservableList<Semester> semestersList;
    private ObservableList<StudentsClass> observableClassesList;
    
    @FXML
    void openClass(MouseEvent event)
    {
    	manager.setLatestSelection(classesList.getSelectionModel().getSelectedItem());
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassTab.fxml"));
        Tab viewClassTab = new Tab("View classes");
        manager.getContainer().getTabs().add(viewClassTab);
        try {viewClassTab.setContent(loader.load());} 
        catch (IOException e){e.printStackTrace();}	
    }
    @SuppressWarnings("unchecked")
	@FXML
    void changeSemester(ActionEvent event)
    {
    	presentedSemester = semesterChoice.getSelectionModel().getSelectedItem();
    	ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getClassesInSemester");
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e){e.printStackTrace();}
    	synchronized (Main.con)
    	{
    		try {Main.con.wait();} catch (InterruptedException e){e.printStackTrace();}
		}
    	ArrayList<ArrayList<String>>answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		ArrayList<StudentsClass> classes = new ArrayList<StudentsClass>();
		if(answer2!=null)
			for(ArrayList<String> sclass : answer2)
			{
				classes.add(new StudentsClass(sclass.get(0),sclass.get(1),sclass.get(2)));
			}
		
		observableClassesList = FXCollections.observableArrayList();
		for(StudentsClass s : classes)
			observableClassesList.add(s);
		classesList.setItems(observableClassesList);
    }
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		myMain = Main.getInstance();
		manager = TabManager.getInstance();
		current = manager.getContainer().getTabs().get( manager.getContainer().getTabs().size()-1);
		current.setText("View classes");
		presentedSemester = manager.getCurrentSemester();
		
		//get all semesters
		ArrayList<String> arrsend = new ArrayList<String>();
		arrsend.add("getAllSemesters");
		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con){
    		try {Main.con.wait();} catch (InterruptedException e){e.printStackTrace();}
		}
		ArrayList<ArrayList<String>> answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		if(answer2!=null)
			for(ArrayList<String> semester : answer2)
			{
				semesters.add(new Semester(semester.get(0),semester.get(1)));
			}
		
		semestersList = FXCollections.observableArrayList();
		for(Semester s : semesters)
			semestersList.add(s);
		semesterChoice.setItems(semestersList);
			
		//Get the classes for the semester:
		arrsend = new ArrayList<String>();
		arrsend.add("getClassesInSemester");
		arrsend.add(presentedSemester.getYear());
		arrsend.add(presentedSemester.getSemesterNumber());
		try {
			Main.con.sendToServer(arrsend);
		} catch (IOException e){e.printStackTrace();}
    	synchronized (Main.con)
    	{
    		try {Main.con.wait();} catch (InterruptedException e){e.printStackTrace();}
		}
		answer2 = new ArrayList<ArrayList<String>>();
		answer2 = (ArrayList<ArrayList<String>>)Main.con.getMessage();
		ArrayList<StudentsClass> classes = new ArrayList<StudentsClass>();
		if(answer2!=null)
			for(ArrayList<String> sclass : answer2)
			{
				classes.add(new StudentsClass(sclass.get(0),sclass.get(1),sclass.get(2)));
			}
		
		observableClassesList = FXCollections.observableArrayList();
		for(StudentsClass s : classes)
			observableClassesList.add(s);
		classesList.setItems(observableClassesList);
	}
}
