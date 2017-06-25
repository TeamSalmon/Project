package controllers;

import java.io.IOException;
import java.util.ArrayList;

import ServerClient.ClientConsole;
import projectsalmon.*;

public abstract class UserController
{
/*	public void searchUser(String id)//ask from server info about spespeic teacher
	{
		
		 ArrayList<String> arrsend  =  new ArrayList<String>();
		 arrsend.add("searchUser");
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
		 

	}*/
	
	public void logout(User user)
	{
		//change status to logged out;
	}
}
