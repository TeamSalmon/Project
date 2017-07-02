/**
 * Sample Skeleton for 'reportMenu.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.reportController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class reportMenuController implements Initializable {

	Main myMain = Main.getInstance();
	String xname, searchKey;
	int semstersNum;
	ObservableList<String> list;
	ArrayList<String> askInfo = new ArrayList<String>();
    @FXML // fx:id="an1"
    private AnchorPane an1; // Value injected by FXMLLoader
    @FXML // fx:id="backPT"
    private Button backPT; // Value injected by FXMLLoader
    @FXML // fx:id="classID3"
    private TextField classID3; // Value injected by FXMLLoader

    @FXML // fx:id="calcPT3"
    private Button calcPT3; // Value injected by FXMLLoader

    @FXML // fx:id="and2"
    private AnchorPane and2; // Value injected by FXMLLoader

    @FXML // fx:id="calcPT2"
    private Button calcPT2; // Value injected by FXMLLoader

    @FXML // fx:id="calcPT1"
    private Button calcPT1; // Value injected by FXMLLoader

    @FXML // fx:id="an3"
    private AnchorPane an3; // Value injected by FXMLLoader

    @FXML // fx:id="classID2"
    private TextField classID2; // Value injected by FXMLLoader

    @FXML // fx:id="comboR1"
    private ComboBox<String> comboR1; // Value injected by FXMLLoader

    @FXML // fx:id="teachidPT1"
    private TextField teachidPT1; // Value injected by FXMLLoader

    @FXML // fx:id="comboR3"
    private ComboBox<String> comboR3; // Value injected by FXMLLoader

    @FXML // fx:id="comboR2"
    private ComboBox<String> comboR2; // Value injected by FXMLLoader

    @FXML
    void calcReport1(ActionEvent event) throws IOException 
    {
    	
    	
    	xname="Class Number";
    	searchKey=teachidPT1.getText();//get teacher id for search
    	semstersNum=Integer.parseInt(comboR1.getValue());
    	Main.repCon=new reportController();
    	Main.repCon.makeReport(semstersNum,"report1",searchKey);
    	((Node)event.getSource()).getScene().getWindow().hide();
    
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(2));
    }

    @FXML
    void calcReport2(ActionEvent event) throws IOException 
    {
    	xname="Teacher Number";
    	searchKey=classID2.getText();//get id class for search
    	semstersNum=Integer.parseInt(comboR2.getValue());
    	Main.repCon=new reportController();
    	Main.repCon.makeReport(semstersNum,"report2",searchKey);
    	((Node)event.getSource()).getScene().getWindow().hide();
    
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(2));		
				
    }

    @FXML
    void calcReport3(ActionEvent event) throws IOException 
    {
    	xname="Course Name";
    	searchKey=classID3.getText();//get id class for search
    	semstersNum=Integer.parseInt(comboR3.getValue());
    	Main.repCon=new reportController();
    	Main.repCon.makeReport(semstersNum,"report3",searchKey);
    	((Node)event.getSource()).getScene().getWindow().hide();
    
		myMain.getMange().changeScene(myMain.getMange().initializationScreens(2));
    }

    
    
    @FXML
    void goBack(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	 myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
    } 
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<String> al = new ArrayList<String>();	
		for(int i=0;i<6;i++)
		{
			al.add(""+i);
		}
		
		list = FXCollections.observableArrayList(al);
		comboR1.setItems(list);
		comboR2.setItems(list);
		comboR3.setItems(list);
		
		
		
	}
}
