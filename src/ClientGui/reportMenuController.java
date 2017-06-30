/**
 * Sample Skeleton for 'reportMenu.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import projectsalmon.*;


public class reportMenuController implements Initializable
{
    @FXML
    private AnchorPane an32;
    @FXML
    private ComboBox<Semester> comboR31;
    @FXML
    private ListView<Course> coursesList;
    @FXML
    private AnchorPane an31;
    @FXML
    private Button closeBtn;
    @FXML
    private AnchorPane an3;
    @FXML
    private Button go1;
    @FXML
    private ListView<Teacher> teachersList1;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private Label title;
    @FXML
    private ComboBox<Semester> comboR3;
    @FXML
    private Button closeBtn1;
    @FXML
    private ComboBox<Semester> semesterChoice1;
    @FXML
    private Tab classList3;
    @FXML
    private ListView<StudentsClass> classList2;
    private Main myMain = Main.getInstance();
	private String xname;
	private String searchKey;
	private String semstersNum;
	private ObservableList<String> list;
	private TabManager manager;

    @FXML
    void closeTab(ActionEvent event)
    {
    	
    }
    @FXML
    void chageSemester1(ActionEvent event)
    {
    	
    }
    @FXML
    void calcReport1(ActionEvent event) throws IOException 
    {
    	xname="Class Number";
    	searchKey=teachidPT1.getText();//get teacher id for search
    	semstersNum=comboR1.getValue();
    	//myMain.setReport=

    }
    @FXML
    void calcReport2(ActionEvent event) 
    {
    	xname="Teacher Number";
    	searchKey=classID2.getText();//get id class for search
    	semstersNum=comboR2.getValue();
    	//myMain.setReport=
    }

    @FXML
    void calcReport3(ActionEvent event) 
    {
    	xname="Course Name";
    	searchKey=classID3.getText();//get id class for search
    	semstersNum= comboR3.getValue();
    	//myMain.setReport=
    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
    	
    	//report1:
    	
	}
}
