package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import ServerClient.ClientConsole;
import projectsalmon.*;
import projectsalmon.Class;

public abstract class SecretayController {

	static String searchStudentID;
	
//	
//	public void  getinfo(String id)//ask from server info about spespeic teacher
//	{
//		
//		 ArrayList<String> arrsend  =  new ArrayList<String>();
//		 arrsend.add("getinfo");
//		 arrsend.add(id);
//		
//		try
//		{
//			this.con.getClient().handleMessageFromClientUI(arrsend);
//		}
//		catch(IOException e)
//		{
//			ClientConsole.getLog().setText("Could not send message to server.  Terminating client.");
//		}
//		ClientConsole.getLog().setText(con.getStringOut());
//	}
	
	
	
	public static Student  searchStudentID(String studentID){
		
		//ask DB for student name (studentID)
		Student newStudent = new Student("123","Galit" ,"Alfarsi" ,"0000");
		
		if (newStudent == null)
			return null;
		return newStudent;
	}
	


public static ArrayList<String>  searchCourseNum(String CourseNum){
	ArrayList<String> classCourseArr = new ArrayList<String>();
	ArrayList<StudentsClassInCourse> StudentsClassInCourseArr= new ArrayList<StudentsClassInCourse>();
		//ask DB for ClassInCourseIDs (CourseNum)

	  Calendar startsAt = Calendar.getInstance();
	  Calendar endsAt = Calendar.getInstance();
	  startsAt.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
	  startsAt.set(Calendar.HOUR, 10);
	  startsAt.set(Calendar.MINUTE, 30);
	  endsAt.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
	  endsAt.set(Calendar.HOUR, 12);
	  endsAt.set(Calendar.MINUTE, 30);
	  Class newClass= new Class(startsAt,endsAt);
	  ArrayList<Class> classschedule = new ArrayList<>();
	  classschedule.add(newClass);
	  TeachingUnit newTeachingUnit = new TeachingUnit("22","Math");
	  Teacher newTeacher = new Teacher(20,"123456789","Elena", "Smith", "abcdefg");
	
	  Course newCourse = new Course("123", "Algebra1",newTeachingUnit, "In course Algebra 1 you will learn how to calculate matrices", (float)3.0);
	  
	  StudentsClass newStudentsClass = new StudentsClass("2646452","10A", "10");
	  
	  // public StudentsClassInCourse(String classCourseID,Course course,StudentsClass studentsClass,ArrayList<Class> classSchedule,Teacher teacher)
	StudentsClassInCourse newClassCourse1 = new StudentsClassInCourse("1000001",newCourse,newStudentsClass,classschedule,newTeacher);
	StudentsClassInCourse newClassCourse2 = new StudentsClassInCourse("1000002",newCourse,newStudentsClass,classschedule,newTeacher);
	
	StudentsClassInCourseArr.add(newClassCourse1);
	StudentsClassInCourseArr.add(newClassCourse2);
	
		if (StudentsClassInCourseArr == null)
			return null;
		
		for(StudentsClassInCourse classCourseID:StudentsClassInCourseArr)	
			classCourseArr.add(classCourseID.getclassCourseID());
		
		return classCourseArr;
	}
}


