package ServerClient;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.ArrayList;

import ClientGui.Main;
import javafx.scene.control.TextField;
import ocsf.client.ObservableClient;
import projectsalmon.TeachingUnit;


/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConsole extends ObservableClient 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  
  private String stringOut;
  private static TextField log;
  private Object message;
  private int flag=0;
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public ClientConsole(String host, int port) throws IOException
  {
	  super(host, port); //Call the superclass constructor
		openConnection();


  }

  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */


  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */



public static TextField getLog() {
	return log;
}


public static void setLog(TextField log) {
	ClientConsole.log = log;
}






public Object getMessage() {
	

	return message;
}


public void setMessage(Object message) 
{

	  this.message=message;    
}


//End of ConsoleChat class
synchronized public void handleMessageFromServer(Object msg) 
{
	  		String action ;
           this.setMessage(msg);
	    	notify();
	}   

}