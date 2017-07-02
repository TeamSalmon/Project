/**
 * Sample Skeleton for 'secondStage.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import java.util.ArrayList;

import ServerClient.ClientConsole;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projectsalmon.Course;
import projectsalmon.TeachingUnit;

public class Stage2Controller {
	String teachId,unitUpdateval;
	Main	myMain=	Main.getInstance();
    @FXML // fx:id="SearchPT"
    private Button SearchPT; // Value injected by FXMLLoader

    @FXML // fx:id="InfoId"
    private TextField InfoId; // Value injected by FXMLLoader

    @FXML // fx:id="InfoLog"
    private TextField InfoLog; // Value injected by FXMLLoader

    @FXML // fx:id="InfoExitPT"
    private Button InfoExitPT; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateID"
    private TextField UpdateID; // Value injected by FXMLLoader

    @FXML // fx:id="UnitUpdate"
    private TextField UnitUpdate; // Value injected by FXMLLoader

    @FXML // fx:id="UpdatePT"
    private Button UpdatePT; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateLog"
    private TextField UpdateLog; // Value injected by FXMLLoader

    @SuppressWarnings("unchecked")
	@FXML
    void SearchTeacher(ActionEvent event) throws IOException, InterruptedException 
    {
    	
    	
    	
    	
    	
  		
		ArrayList<TeachingUnit> arrget  =  new ArrayList<TeachingUnit>();
		 ArrayList<String> arrsend  =  new ArrayList<String>();
		 arrsend.add("coursesByID");
		
		
		
			Main.con.sendToServer(arrsend);
	
		
	    	synchronized (Main.con) {
	    		
	    		Main.con.wait();
			}
	    	
		//TeachingUnit bla=new TeachingUnit();
		//bla=(TeachingUnit)Main.con.getMessage();
		InfoLog.setText((String)Main.con.getMessage());
	
		//arrget=(ArrayList<TeachingUnit>) Main.con.getMessage();
		
		//InfoLog.setText(arrget.get(0).getName());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//InfoLog.setText(arrget.get(9).getName());
		/*
    	teachId=InfoId.getText();
    	if(teachId.length()<1)
    	{
    		InfoLog.setText("please Enter Teacher ID");
    	}
    	else
    	{
    		 myMain.getConnection();
			ClientConsole.setLog(InfoLog);	
    		//myMain.cc.log=;
    		myMain.getinfo(teachId);
    		
    	}
    	*/
 	}
    

    @FXML
    void Stage2exit(ActionEvent event) throws IOException 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();

    	//myMain.getMange().changesence(0);
    	//System.exit(1);
    }

    @FXML
    void UpdateTeacing(ActionEvent event) 
    {
    	unitUpdateval=UnitUpdate.getText();
    	teachId=UpdateID.getText();
    	if(teachId.length()<1 || unitUpdateval.length()<1)
    	{
    		UpdateLog.setText("please enter Parmetres");
    	}
    	else
    	{
    		 myMain.getConnection();
			ClientConsole.setLog(UpdateLog);
    		
    		//myMain.UpdateTeacing(teachId, unitUpdateval);
    	}
    	
    }

}
