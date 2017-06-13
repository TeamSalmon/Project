package ClientGui;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ServerClient.ClientConsole;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class Main extends Application {
	  //@Override
	private final static Main instance = new Main();
	private Stage theStage;


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



	  public void start(Stage primaryStage) throws IOException 
	  {
	    // constructing our scene
		  theStage=primaryStage;
	    URL url = getClass().getResource("ClientXml.fxml");
	    AnchorPane pane = FXMLLoader.load( url );
	    Scene scene = new Scene( pane );
	    // setting the stage
	    primaryStage.setScene( scene );
	    primaryStage.setTitle( "Stage1" );
	    primaryStage.show();
	    
	  }    

public void  getinfo(String id)//ask from server info about spespic teacher
{
	String anw = null;
	 ArrayList<String> arrsend  =  new ArrayList<String>();
	 arrsend.add("getinfo");
	 arrsend.add(id);
	
	try
	{
		this.con.getClient().handleMessageFromClientUI(arrsend);
	}
	catch(IOException e)
	{
		this.con.getLog().setText("Could not send message to server.  Terminating client.");
	}
	this.con.getLog().setText(con.getStringOut());
}

public void UpdateTeacing(String id,String unit)//update teaching unit for specpic teacher
{
	String anw = null;
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
		this.con.getLog().setText("Could not send message to server.  Terminating client.");
	}
	this.con.getLog().setText(con.getStringOut());
}

	   public static void main(String[] args) 
	   {
		launch(args);
	   }
	 }
