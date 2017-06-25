/**
 * Sample Skeleton for 'ClientXml.fxml' Controller Class
 */

package ClientGui;

import java.io.IOException;
import ServerClient.ClientConsole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
	
	Main	myMain=	Main.getInstance();
	boolean flag=true;
	String serverip,serverport;
	 @FXML // fx:id="connectPT"
    private Button connectPT; // Value injected by FXMLLoader

    @FXML // fx:id="exitPT"
    private Button exitPT; // Value injected by FXMLLoader

    @FXML // fx:id="ipPT"
    private TextField ipPT; // Value injected by FXMLLoader

    @FXML // fx:id="portPT"
    private TextField portPT; // Value injected by FXMLLoader

    @FXML // fx:id="logPT"
    private TextField logPT; // Value injected by FXMLLoader

    @FXML
    void connect(ActionEvent event) throws IOException   
    {
    	
    	serverport=portPT.getText();
    	serverip=ipPT.getText();
    	
    	// if(event.getSource()==connectPT && serverport.length()>0 && serverip.length()>0 )
        {//start if

            try
            {
            ClientConsole con =new ClientConsole(serverip, Integer.parseInt(serverport));	
            myMain.setConnection(con);
            myMain.getConnection();
			ClientConsole.setLog(logPT);	
            }
            
            
            /*catch(RuntimeException e)
            {
         	   logPT.setText ("Error: Can't setup connection!" + " Terminating client.");
         	  flag=false;
            }
             */
            catch(IOException e)
            {
         	   logPT.setText ("Error: Can't setup connection!" + " Terminating client.");
         	   flag=false;
            }
            if(flag)
            {




            	try{
            		
            		((Node)event.getSource()).getScene().getWindow().hide();
            		myMain.getMange().changeScene(myMain.getMange().initializationScreens(4));
            	}
            	
            	catch(IOException e)
            	{
            		logPT.setText ("cant load stage2");
            	}

            }
           
        }//end if

    }

    @FXML
    void exit(ActionEvent event) 
    {
    	System.exit(1);
    }

}
