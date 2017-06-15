package ClientGui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ServerClient.ClientConsole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public  class Main extends Application {
	  //@Override
	private final static Main instance = new Main();
	private static Stage theStage;
	static ArrayList<Scene> al = new ArrayList<Scene>();	
	private static int CorenSence;
	private static int lastSence;
	public static Main getInstance() {
        return instance;
    }

    private ClientConsole con;
    public void setConnection(ClientConsole con)
    {
        this.con=con;
    }
    public ClientConsole getConnection() {
        return con;
    }
    public void changesence(int i) throws IOException
    {
    	if(i!=CorenSence||CorenSence==0)
    	{
    	lastSence=CorenSence;
    	CorenSence=i;
        String s;
        s="sence"+" "+i;
        theStage.setScene(al.get(i));
        theStage.setTitle(s);
        theStage.show();
    	}
    	
    }
    public void initializationScreens() throws IOException
    {
    	
    	// setting first sence
    	URL url = getClass().getResource("ClientXml.fxml");
   
	    AnchorPane pane = FXMLLoader.load( url );
	    pane.setId("test");
	    Scene scene = new Scene( pane );
	    scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());

	    theStage.setScene( scene );
	    theStage.setTitle( "Stage1" );
	    theStage.show();
	    al.add(scene);
	    //setting second sence
    	Parent root;
	    root = FXMLLoader.load(getClass().getResource("secondStage.fxml"));
	    Scene scene2 = new Scene( root );
	    al.add(scene2);
    }

	  public void start(Stage primaryStage) throws IOException 
	  {
	    // constructing our scene
		  theStage=primaryStage;
		 
		  initializationScreens();
		  lastSence=0;

	  }    

public void  getinfo(String id)//ask from server info about spespeic teacher
{
	
	 ArrayList<String> arrsend  =  new ArrayList<String>();
	 arrsend.add("getinfo");
	 arrsend.add(id);
	
	try
	{
		this.con.getClient().handleMessageFromClientUI(arrsend);
	}
	catch(IOException e)
	{
		ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
	}
	ClientConsole.getLog().setText(con.getStringOut());
}

public void UpdateTeacing(String id,String unit)//update teaching unit for specpic teacher
{
	
	 ArrayList<String> arrsend  =  new ArrayList<String>();
	 arrsend.add("update");
	 arrsend.add(id);
	 arrsend.add(unit);
	try
	{
		this.con.getClient().handleMessageFromClientUI(arrsend);
	}
	catch(IOException e)
	{
		ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
	}
	ClientConsole.getLog().setText(con.getStringOut());
}

	   public static void main(String[] args) 
	   {
		launch(args);
	   }
	 }
