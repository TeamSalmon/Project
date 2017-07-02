package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController {
	EchoServer echoserver;
	public ServerController(){
		
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ipServerFT;

    @FXML
    private TextField portServerFT;

    @FXML
    private Button connectServerBT;

    @FXML
    private Button closeServerBT;

    @FXML
    private TextArea serverAnswer;

    @FXML
    private PasswordField serverPasswordFT;

    @FXML
    void close_Server(ActionEvent event)   {
    	
			try {
				if(echoserver.isListening())
				echoserver.close();
			} 
    	catch (RuntimeException e) {//in case ecoserver dont initialization	
				System.exit(1);  
			}
	 catch (IOException c) {
		System.exit(1);  
	}
       System.exit(1);   
       }

    @FXML
    void connect_Server(ActionEvent event) throws Exception {
      String PORT= portServerFT.getText();
      String PASSWORD= serverPasswordFT.getText();
      String ID= ipServerFT.getText();
      int port;
    		  if(PORT.isEmpty()==true||PASSWORD.isEmpty()==true||ID.isEmpty()==true){
    	         serverAnswer.setText("You must to fill all the labels!!!!!!");
    	         portServerFT.clear();
    	         serverPasswordFT.clear();
    	         ipServerFT.clear();
    		  }
    		  else
    		  {
    			  
    			  port = Integer.parseInt(PORT);
    			  echoserver = new EchoServer(port);
    			  if((echoserver.ConnectToDB("mat_db",ID,PASSWORD))==0){
    				  serverAnswer.setText("can't connect to DB");
    				  portServerFT.clear();
    	    	         serverPasswordFT.clear();
    	    	         ipServerFT.clear();
    				  echoserver.close();
    			  }
    			  else{
    				 try{
    					 echoserver.listen();
    					 serverAnswer.setText("Server is listening on port " + port);
    					 
          			    }
    				 catch(Exception ex)
    				 {
          				serverAnswer.setText(" doesn't listen for clients the port "+PORT+" in use");
          				portServerFT.clear();
           	         serverPasswordFT.clear();
           	         ipServerFT.clear();
          			 }
    			  }
    			}
    			
    		  }
}


