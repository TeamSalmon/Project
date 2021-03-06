package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class guiMange 
{
	static ArrayList<Scene> al = new ArrayList<Scene>();	
	private static int CorenSence=0;
	private static int lastSence;
    public static final String MPAGE = "ClientXml.fxml";
    public static final String Page1 = "SecondStage.fxml";
    public static final String Page2 = "halo.fxml";	
    public static final String Page3 = "TeacherMainGui.fxml";
    public static final String Page4 = "reportMenu.fxml";
    public static final String Page5 = "assignSingleStudentToCourseRequestGUI.fxml";
    public static final String Page6 = "removeStudentFromCourseGUI.fxml";
    public static final String Page7 = "defineClass1.fxml";
    public static final String Page8 = "defineClass2.fxml";
    public static final String Page9 = "AssignClassToCourse1.fxml";
    public static final String Page10 = "AssignClassToCourse2.fxml";
    public static final String Page11 = "AssignClassToCourse3.fxml";
    public static final String Page12 = "StudentGUI.fxml";
    public static final String Page15 = "defineCourse.fxml";
    public static final String Page16 = "blockOrUnblockUser.fxml";
    public static final String Page17 = "blockOrUnblockParent.fxml";
    public static final String PageMinus108 = "systemAdminMenu.fxml";

    public static final String CSS = "test.css";
	public static final String PageMinus100 = "LoginGUI.fxml";
	public static final String PageMinus101 ="LoginPermissionGUI.fxml";
    public static final String Page102 = "ManagerGui.fxml";
	public static final String Page20 ="AppointTeacherToCourseGUI.fxml";
	public static final String Page21 ="RemovetTeacherFromCourseGUI.fxml";
	
    public static ObjectStack myStack = new ObjectStack (10);

	
    public void setMainStage() throws IOException
    {
    	URL url = getClass().getResource(MPAGE);
	    AnchorPane pane = FXMLLoader.load(url);
	    pane.setId("test");
	    Scene scene = new Scene( pane );
	    scene.getStylesheets().add(this.getClass().getResource(CSS).toExternalForm());

	    myStack.push(scene);
    	
    }
	
	
    public Scene initializationScreens(int i) throws IOException
    {
    	String xmlNum = null;
    	switch(i)
    	{
    	case 1:
    		xmlNum=Page1;
    		break;
    		
    	case 2:
    		xmlNum=Page2;    		
    		break;
    		
    	case 3:
    		xmlNum = Page3;
    		break;
    		
    	case 4:
    		xmlNum = Page4;
    		break;
    		
    	case 5:
    		xmlNum = Page5;
    		break;

    	case 6:
    		xmlNum = Page6;
    		break;
    	
    	case 7:
    		xmlNum = Page7;
    		break;
    	
    	case 8:
    		xmlNum = Page8;
    		break;
    		
    	case 9:
    		xmlNum = Page9;
    		break;
    		
    	case 10:
    		xmlNum = Page10;
    		break;
    		
    	case 11:
    		xmlNum = Page11;
    		break;
    		
    	case 12:
    		xmlNum = Page12;
    		break;	
    	
    	case 15:
    		xmlNum = Page15;
    		break;	
    		
    	case 16:
    		xmlNum = Page16;
    		break;	
    		
    	case 17:
    		xmlNum = Page17;
    		break;	
    	case -100:
        	xmlNum = PageMinus100;
        	break;		
        	
    	case -101:
        	xmlNum = PageMinus101;
        	break;	
        	
    	case -108:
        	xmlNum = PageMinus108;
        	break;	
        	

    	case 102:
    		xmlNum = Page102;
    		break;
    		
    	case 20:
    		xmlNum = Page20;
    		break;	
    		
    	case 21:
    		xmlNum = Page21;
    		break;
    		
    	default:
    		break;
    	}
		Parent root;
		root = FXMLLoader.load(getClass().getResource(xmlNum));
		Scene scene = new Scene( root );	
		return scene;
    }
	
    /**
	   * the method change the sence
	   * @param Scene sceneUP.-the sence to show.
	   * @return none.
	   * @throws  IOException if  failed.
	   * @author tamir. 
	   */
    public void changeScene(Scene sceneUP) throws IOException
    {
    	
    	myStack.push(Main.getTheStage().getScene());
     
        Main.getTheStage().setScene(sceneUP);
        
        Main.getTheStage().show();
    	}
    	
    /**
	   * the method same as changeScene but dont push the corrent stage(for reports)
	   * @param Scene sceneUP.-the sence to show.
	   * @return none.
	   * @throws  IOException if  failed.
	   * @author tamir. 
	   */
  public void changeScenereport(Scene sceneUP) throws IOException
  {
  	
  	
   
      Main.getTheStage().setScene(sceneUP);
      
      Main.getTheStage().show();
  	}


	public static int getLastSence() { 

		return lastSence;
	}


	public static void setLastSence(int lastSence) {
		guiMange.lastSence = lastSence;
	}
	
}
