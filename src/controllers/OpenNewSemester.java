package controllers;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientGui.Main;
import ServerClient.ClientConsole;
import controllers.SecretaryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;

import projectsalmon.*;

public abstract class OpenNewSemester 
{
	
	static Main myMain = Main.getInstance();
	private static Object received_object;
	

	static String former_year = null;
	static String former_semester = null;
	static String new_semester = null;
	static String new_year = null;
		
	
	static ArrayList<String> request = new  ArrayList<String>();
	
	static ArrayList<Course> courses = new  ArrayList<Course>();
	static ArrayList<StudentsClass> classes = new  ArrayList<StudentsClass>();
	static ArrayList<Student> students = new  ArrayList<Student>();
	static ArrayList<String> lineInReport = new  ArrayList<String>();
	static ArrayList<StudentsClassInCourse> coursesGroups = new  ArrayList<StudentsClassInCourse>();

	private static ArrayList<Object> list_of_objects = new ArrayList<Object> ();

	
	public static void updateSemester()
	{
		
		//get current semester from DB
	
		ArrayList<String> query_CurrentSemester = new ArrayList<String>();
		query_CurrentSemester.add("CurrentSemester");
	
		try{
			myMain.getConnection().sendToServer((Object)query_CurrentSemester);
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
			
		received_object = myMain.con.getMessage();
		list_of_objects = (ArrayList<Object>)received_object;
				
		for(Object obj : list_of_objects)
		{
			String year = null;
			String semesternumber=null;
			
			for(int i = 0 ; i < ((ArrayList<String>)obj).size() ; i++ )
			{
				switch (i)
				{
					case 0:
						former_year = ((ArrayList<String>)obj).get(i);
						break;
					case 1:
						former_semester = ((ArrayList<String>)obj).get(i);
						break;
		
				}	
			}
		}
		
		if (former_semester.equals("A")) 
		{
			new_semester = "B";
			new_year = former_year;
		} 
		else 
		{
			new_semester = "A";
			new_year = ((Integer) (Integer.valueOf(former_year) + 1)).toString();
		}
			

		// ask DB: Get list of all the classes in the DB - SORTED BY 'studentsClassId'
		// ArrayList<StudentsClass> list_of_classes = DB.classesByID();
		
		
		classes = SecretaryController.getOptionalClasses();
		
		 //change the levels of the classes if a new year starts
		if(new_semester.equals("1"))
		{
			for (StudentsClass sclass :classes)
			{
				String level = sclass.getlevel(); 
				String name = sclass.getClassName(); 

				switch (level)
				{
					case "10":
						sclass.setlevel("11");
						sclass.setClassName( name.charAt(0) + "1" + name.charAt(2));
						break;
					case "11":
						sclass.setlevel("12"); 
						sclass.setClassName( name.charAt(0) + "2" + name.charAt(2));
						break;
					case "12":
						sclass.setlevel("X");
						sclass.setClassName("X");
						break;
				}
		
				// update the class in the DB
				
				public static ArrayList<String> updateSclassDetails(String StudentsClassID,String level, String className){

				ArrayList<String> query_updateSclassDetails = new ArrayList<String>();
				query_updateSclassDetails.add("updateSclassDetails");
				query_updateSclassDetails.add(sclass.getClassId());
				query_updateSclassDetails.add(sclass.getlevel());
				query_updateSclassDetails.add(sclass.getClassName());

				
				try{
					myMain.getConnection().sendToServer((Object)query_updateClassOfStudent);
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
				String result = ((ArrayList<String>)received_object).get(0);
						
			}
			}
			}
			
			// ask from the DB: actual courses (not types of courses) that are studied 
			// during the current semester.  
	
			//take only the courses that their semester is null			

			myMain.getConnection().getClient().handleMessageFromClientUI((Object)query_coursesByID);
			received_object = myMain.getConnection().getMessage();	

			
			for (Course course : courses)
			{
				
				// ask DB: list of groups in this class
				for (StudentsClassInCourse cic : coursesGroups)
				{
				
					//ask from DB: get list of the students in this course-class	
					for (Student student : students)
					{
						
						// ask DB for average grade in the course for this student
						String gradeInCourse = "0";
						//lineInReport.add();
						lineInReport.add(course.getTeacher());
						lineInReport.add(student.getId());
						lineInReport.add(student.getStudentClass().getClassId());
						lineInReport.add(gradeInCourse);
						lineInReport.add(course.getCourseNumber());
						lineInReport.add(course.getYear());
						lineInReport.add(course.getSemester());
					}
				}
			}
			
		}
	}
		
	
}
}