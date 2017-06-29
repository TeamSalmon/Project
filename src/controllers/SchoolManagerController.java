package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ClientGui.Main;
import projectsalmon.*;

/**
 * This controller is responsible for methods and scenes that are used 
 * as part of the schools manager's functionalities.
 * 
 * @see BlockOrUnblockParentController

 * @author Elia
 */
public class SchoolManagerController {

	static Main myMain = Main.getInstance();
	private static Object received_object;

	
	/* Block or Unblock Parent */
						
	/**
	 * Checks if the given user has a parent permission.
	 * @param id
	 * @return boolean answer
	 * @throws IOException
	 */
	public static boolean checkIfParent (String id) throws IOException {
		
		ArrayList<String> query_searchUser = new ArrayList<String>();
		query_searchUser.add("searchUser");
		query_searchUser.add(id);
		
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_searchUser);
		received_object = myMain.getConnection().getMessage();
		LoginUser user = (LoginUser)received_object;
			
		return ((user.getPermission() & LoginUser.ParentPER)!=0);
	
	}
	
	
	/**
	 * Attempting to block the specified user
	 * @param id
	 * @return String response from DB
	 * @throws IOException
	 */
	public static String blockUser (String id) throws IOException {
				
		ArrayList<String> query_block = new ArrayList<String>();
		query_block.add("block");
		query_block.add(id);
		
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_block);
		received_object = myMain.getConnection().getMessage();
		String answer = (String)received_object;
			
		return answer;
	}
	
	
	/**
	 * Attempting to unblock the specified user
	 * @param id
	 * @return String response from DB
	 * @throws IOException
	 */
	public static String unblockUser (String id) throws IOException {
		
		ArrayList<String> query_unblock = new ArrayList<String>();
		query_unblock.add("unblock");
		query_unblock.add(id);
		
		myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_unblock);
		received_object = myMain.getConnection().getMessage();
		String answer = (String)received_object;
			
		return answer;
	}
	
}
