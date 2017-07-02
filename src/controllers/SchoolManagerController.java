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
	static ArrayList<Object> list_of_objects = new ArrayList<Object>();
	
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
			
		try {
			myMain.getConnection().sendToServer((Object) query_searchUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		synchronized (myMain.getConnection()) {
			try {
				myMain.getConnection().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		received_object = myMain.con.getMessage();
//		list_of_objects = (ArrayList<Object>) received_object;

		
		received_object = myMain.getConnection().getMessage();
		if (((ArrayList<String>)received_object).size() == 0)
		{
			return false;
		}
		
		Integer permission = Integer.valueOf( (((ArrayList<String>)received_object).get(5)) );
		return ((permission & LoginUser.ParentPER)!=0);
		
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
		
		try{
			myMain.getConnection().sendToServer((Object)query_block);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		received_object = myMain.con.getMessage();
				
		String answer = ((ArrayList<String>)received_object).get(0);
			
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
		
		try{
			myMain.getConnection().sendToServer((Object)query_unblock);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			synchronized(myMain.getConnection()){
				try {
					myMain.getConnection().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		received_object = myMain.con.getMessage();
				
		String answer = ((ArrayList<String>)received_object).get(0);
			
		return answer;
	}
	
}
