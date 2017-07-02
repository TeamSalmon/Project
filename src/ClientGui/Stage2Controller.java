/**
 * Sample Skeleton for 'secondStage.fxml' Controller Class
 */

package ClientGui;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import ServerClient.ClientConsole;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import projectsalmon.Course;
//import projectsalmon.FileEvent;
import projectsalmon.TeachingUnit;
//import projectsalmon.filesMange;

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
    	
    	
    	Socket clientSocket;
    	
    	 Socket socket = null;
    	 ObjectOutputStream outputStream = null;
    	 boolean isConnected = false;
    	 String sourceFilePath = "C:/temp/test.txt";
    	// FileEvent fileEvent = null;
    	 String destinationPath = "C:/tmp/downloads/";
    	
    	
		//fileEvent = new FileEvent();
		String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
		String path = sourceFilePath.substring(0, sourceFilePath.lastIndexOf("/") + 1);
	//	fileEvent.setDestinationDirectory(destinationPath);
	//	fileEvent.setFilename(fileName);
		//fileEvent.setSourceDirectory(sourceFilePath);
		File file = new File(sourceFilePath);
		System.out.println("enter func");
		if (file.isFile()) {
		try {
		DataInputStream diStream = new DataInputStream(new FileInputStream(file));
		long len = (int) file.length();
		byte[] fileBytes = new byte[(int) len];
		int read = 0;
		int numRead = 0;
		while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
		read = read + numRead;
		}
		//fileEvent.setFileSize(len);
	//	fileEvent.setFileData(fileBytes);
	//	fileEvent.setStatus("Success");
		} catch (Exception e) {
		e.printStackTrace();
	//	fileEvent.setStatus("Error");
		}
		} else {
		System.out.println("path specified is not pointing to a file");
	//	fileEvent.setStatus("Error");
		}
		//Now writing the FileEvent object to socket
		//Main.con.sendToServer(fileEvent.getFileData());
		
		System.out.println("Done...Going to exit");
		
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//new filesMange();
  		
	
	    	
		//TeachingUnit bla=new TeachingUnit();
		//bla=(TeachingUnit)Main.con.getMessage();
		
	
		//arrget=(ArrayList<TeachingUnit>) Main.con.getMessage();
		
		//InfoLog.setText(arrget.get(0).getName());
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		//InfoLog.setText(arrget.get(9).getName());
		/*
		 * 
		 * 
		 * 
		 * 
		 * 	ArrayList<TeachingUnit> arrget  =  new ArrayList<TeachingUnit>();
		 ArrayList<String> arrsend  =  new ArrayList<String>();
		 arrsend.add("coursesByID");
		
		
		
			Main.con.sendToServer(arrsend);xSENDING FILE TO SERVER
	
		
	    	synchronized (Main.con) {
	    		
	    		Main.con.wait();
			}
			
			
			
			//ster=(String)Main.con.getMessage(); //geting string
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 		// file to byte[]  (file-->byte[]-->server)
		File file = new File("c:/newfile.txt");
		Path p =Paths.get(file.getAbsolutePath());
		byte[] data = new byte[(int) file.length()];
		data = Files.readAllBytes(p);
		//now sending with sendtoserver byte[] data
		
		//when get byte[]  (byte[]-->client-->file)
		//getting from server byte[] and make it file
		Files.write(new File("c:/newfile.txt").toPath(), data);
		
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
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
    		
    		myMain.UpdateTeacing(teachId, unitUpdateval);
    	}
    	
    }

}
