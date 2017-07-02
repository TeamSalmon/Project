package ClientGui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ServerClient.ClientConsole;
import controllers.reportController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import projectsalmon.*;

import javafx.scene.layout.AnchorPane;

public  class Main extends Application {
	//@Override
	private final static Main instance = new Main();
	private static Stage theStage;
	private guiMange Mange = new guiMange();;
    public static reportController  repCon;

	private User user = (User)(new Teacher(10,"315314369","Inbar" ,"Alfasi","12345"));

	private Report myreport = new Report();

	public static Main getInstance() {
        return instance;
    }

	public static ClientConsole con;
    public void setConnection(ClientConsole con)
    {
        this.con=con;
    }
    public ClientConsole getConnection() {
        return con;
    }
    
    /**
    public void changesence(int i) throws IOException
    {
    	if(i!=CorenSence||CorenSence==0)
    	{
    	lastSence=CorenSence;
    	CorenSence=i;
        String s;
        s="sence"+" "+i;
        getTheStage().setScene(al.get(i));
        getTheStage().setTitle(s);
        getTheStage().show();
    	}
    	 
    }
    */
/**
    public void initializationScreens() throws IOException
    {
    	
    	// setting first sence
    	URL url = getClass().getResource("ClientXml.fxml");
   
	    AnchorPane pane = FXMLLoader.load( url );
	    pane.setId("test");
	    Scene scene = new Scene( pane );
	    scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());

	    getTheStage().setScene( scene );
	    getTheStage().setTitle( "Stage1" );
	    getTheStage().show();
	    al.add(scene);
	    //setting second sence
    	Parent root;
	    root = FXMLLoader.load(getClass().getResource("secondStage.fxml"));
	    Scene scene2 = new Scene( root );
	    al.add(scene2);
<<<<<<< HEAD
=======
	    //setting  third
	    
     	Parent root2;
 	    root2 = FXMLLoader.load(getClass().getResource("halo.fxml"));
 	    Scene scene3 = new Scene( root2 );
 	    al.add(scene3);
>>>>>>> origin/new_gui_mange(tamir)
    }
*/
	  public void start(Stage primaryStage) throws IOException 
	  {
	    // constructing our scene
		  setTheStage(primaryStage);
		  getMange().setMainStage();
		  
		    getTheStage().setScene((Scene) guiMange.myStack.peek() );
		    getTheStage().setTitle( "Stage1" );
		    getTheStage().show();
		

	  }    



	public static void main(String[] args) 
    {		
		launch(args);
    }
	
	public static Stage getTheStage() 
	{
		return theStage;
	}
	
	public static void setTheStage(Stage theStage) 
	{
		Main.theStage = theStage;
	}
	
	public void setMange(guiMange mange) 
	{
		Mange = mange;
	}
		
	public guiMange getMange() 
	{
		return this.Mange;
	}
	public User getUser(){return user;}
}
