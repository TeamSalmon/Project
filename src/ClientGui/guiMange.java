package ClientGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class guiMange 
{
	static ArrayList<Scene> al = new ArrayList<Scene>();	
	private static int CorenSence = 0;
	private static int lastSence;
    public static final String MPAGE = "ClientXml.fxml";
    public static final String Page1 = "secondStage.fxml";
    public static final String Page2 = "halo.fxml";
    public static final String Page3 = "TeacherGui.fxml";
    public static final String Page4 = "reportMenu.fxml";
    public static final String CSS = "test.css";
    public static final String Page6 = "defineClass1.fxml";
    public static final String Page7 = "defineClass2.fxml";
    public static final String Page8 = "AssignClassToCourse1.fxml";
    public static final String Page9 = "AssignClassToCourse2.fxml";

	
	
    public void setMainStage() throws IOException
    {
    	URL url = getClass().getResource(MPAGE);
	    AnchorPane pane = FXMLLoader.load( url );
	    pane.setId("test");
	    Scene scene = new Scene( pane );
	    scene.getStylesheets().add(this.getClass().getResource(CSS).toExternalForm());

	    al.add(scene);
    }
	
	
    public void initializationScreens(int i) throws IOException
    {
    	String xmlNum = null;
    	switch(i)
    	{
    	case 1:
    		xmlNum = Page1;
    		break;
    	
    	case 2:
    		xmlNum = Page2;

    		break;
    	case 3:
    		xmlNum = Page3;
    		break;
    		
    	case 4:
    		 xmlNum = Page4;
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
    		
    	default:
    		break;
    	}
		Parent root;
		root = FXMLLoader.load(getClass().getResource(xmlNum));
		Scene scene = new Scene( root );
		
		al.add(scene);
    }
	
	
    public void changesence(int i) throws IOException
    {
    	if(i != CorenSence || CorenSence == 0)
    	{
	    	setLastSence(CorenSence);
	    	CorenSence = i;
	        String s;
	        s = "sence" + " " + i;
	        Main.getTheStage().setScene(al.get(i));
	        Main.getTheStage().setTitle(s);
	        Main.getTheStage().show();
    	}
    	
    }


	public static int getLastSence() {
		return lastSence;
	}


	public static void setLastSence(int lastSence) {
		guiMange.lastSence = lastSence;
	}
	
}
