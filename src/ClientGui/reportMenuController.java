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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class reportMenuController implements Initializable {

	Main	myMain=	Main.getInstance();
	String xname,searchKey;
	String semstersNum;
	ObservableList<String> list;
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

    
    
    @FXML
    void goBack(ActionEvent event) throws IOException 
    {
    	 myMain.getMange().changeScene((Scene) myMain.getMange().myStack.pop());
    } 
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<String>();	
		for(int i=0;i<6;i++)
		{
			al.add(" "+i);
		}
		
		list = FXCollections.observableArrayList(al);
		comboR1.setItems(list);
		comboR2.setItems(list);
		comboR3.setItems(list);
		}
	

}
