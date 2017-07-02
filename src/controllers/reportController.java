package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ClientGui.Main;

public  class reportController 
{
	private  String sYear;
	private  String sNum;
	 public  ArrayList<String> name = new ArrayList<String>();
	 public  ArrayList<Object> grade = new ArrayList<Object>();
	 
		/**
		 * Clear the fields and update current semster.
 		 * @param no parameters.
		 * @return no parameters
		 * @author tamir. 
		  */	 
public reportController ()	
{
	name.clear();
	grade.clear();
	getCurrentSemester();
	
}
	 
	 
	 
/**
 * GO DB and update the corrent semster.
	 * @param no parameters.
 * @return no parameters
 * @author tamir. 
  */	 
	public  void getCurrentSemester()
	{
		ArrayList<String> askInfo = new ArrayList<String>();
		askInfo.add("CurrentSemester");
		
		try {
			Main.con.sendToServer(askInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con) {
    		
    		try {
				Main.con.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	ArrayList<String> answer=(ArrayList<String>) Main.con.getMessage();
		
		
    	setsYear(answer.get(0));
		setsNum(answer.get(1));

	}
	
	  /**
	   * Asking all the data for report
	   * @param howMuchBack.-to know period for the report
	   * @param reportnum.-which report to return
	   * @param key.-the priamry key for the report
	   * @return 1 None
	   * @author tamir. 
	   */
	public  void makeReport(int howMuchBack,String reportnum,String key)
	{
		
		int year=Integer.parseInt(sYear);
		String num=sNum;
		ArrayList<ArrayList<String>> collection=new ArrayList<ArrayList<String>>();
		ArrayList<String> corrent = new ArrayList<String>();
		corrent.add(reportnum);
		corrent.add(key);
		corrent.add(sYear);
		corrent.add(sNum);
		collection.add(corrent);
		for(int i=1;i<=howMuchBack;i++)
		{
			
			if(sNum.equals("A"))
			{
			year=year-i;
			num="B";
			}
			else 
				num="B";
			ArrayList<String> req = new ArrayList<String>();
			req.add(reportnum);
			req.add(key);
			req.add(String.valueOf(year));
			req.add(num);
			collection.add(req);
			getdata(req);
		}
		
		
	}
	  /**
	   * Getting all the data for report
	   * @param askInfo.- info for server to know whice data to return
	   * @author tamir. 
	   */

	public void getdata(ArrayList<String> askInfo)
	{
		
		try {
			Main.con.sendToServer(askInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized (Main.con) {
    		
    		try {
				Main.con.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	@SuppressWarnings("unchecked")
		ArrayList<Object> answer=(ArrayList<Object>) Main.con.getMessage();
    	
    	
   	   ArrayList<String> nametemp = new ArrayList<String>();
   	   ArrayList<Object> gradetemp = new ArrayList<Object>();
    	
    	
    	nametemp=(ArrayList<String>) answer.get(0);
    	gradetemp=(ArrayList<Object>) answer.get(1);
    	for(int i=0;i<nametemp.size();i++)
    	{
    		this.name.add(nametemp.get(i));
    		this.grade.add(gradetemp.get(i));
    	}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getsYear() {
		return sYear;
	}


	public  void setsYear(String sYear) {
		this.sYear = sYear;
	}
	
	

	public String getsNum() {
		return sNum;
	}


	public  void setsNum(String sNum) {
		this.sNum =sNum;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
