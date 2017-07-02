package application;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ocsf.server.*;
import java.util.ArrayList;

public class EchoServer extends AbstractServer  {
	public static Connection connection;
	  
	  /**
	   * Constructs an instance of the echo server.
	   * @param port The port number to connect on.
	   */
	  public EchoServer(int port) 
	  {
	    super(port);
	  }

	  /**
	   * The connect method connect to the DB and give the global and private connection to the DB
         only this class can use or change the DB.
	   * @param DBName.
	   * @param UserID.
	   * @param Password.
	   * @return 1 if connection was succeeded or 0 if failed.
	   * @throws SQLException if connection failed.
	   * @author yevgeni_gitin. 
	   */
	  public int ConnectToDB(String DBName,String UserID , String Password) throws Exception 
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DBName,UserID,Password);
				///DBController.studentClassesByID();
				/*ArrayList<Object> x=DBController.getAllCourses();
				for(int i=0;i<x.size();i++){
					ArrayList<String> y=DBController.coursePrecondition(((ArrayList<String>)x.get(i)).get(0));
					}*/
				///DBController.coursesByID();
			    ///DBController.studentsOfClass("54361");
				///DBController.studentOldCourses("123456782");
				///DBController.courseByTeacher("123567935","2016","A");
				///DBController.CurrentSemester();
				///DBController.getCourseAssignments("56789");
				///DBController.getSemesters("123456782");
				///DBController.block("123456781");
				///DBController.unblock("123456781");
				///DBController.updateUser("123456781","0","1");
				///DBController.searchUser("123456781");
				///DBController.searchStudentID("123456781");
				///DBController.searchCourseNum("23456");
				///DBController.searchStudentID("123456781");
				///DBController.report1("123567947","2016","B");
				///DBController.report2("100","2016","B");
				///DBController.report3("100","2016","B");
				///DBController.allTeachingUnits();
				///DBController.optionalTeachersForCourse("33789");
				///DBController.addNewClass("24236","8","h","h3");
				///DBController.updateClassOfStudent("123456782","101");
				///DBController.searchStudentInCourseCurrentSemester("11345","123456782");
				///DBController.removeStudentFromCourse("123456782","11345");
				///DBController.removeStudentFromCourseRequest("123456783","22001","hhhhhhh");
				///DBController.updateTeacher("123567892","8");
				///DBController.groupsOfCourse("11102");
				//DBController.updateStudentCourse("123456782","33789");
				///DBController.courseByStudent("123456782","2016","B");
				///DBController.getChildren("123567891");
				///DBController.getTeachingUnitCourse("66");
				///DBController.getClassCourses("102","2017","A");
				///DBController.getClassTeacherInCourse("100","22001","2016","A");
				///DBController.getTeacherCourses("123567933","2017","A");
				///DBController.getSubmissions(1);
				///DBController.createAssignment("33789","dfghjk","22/10/1995","ggadkfsgvjzuzbbcgdnc","fjyksvoshcMnncx");
				///DBController.editAssignment("2","33789","dgghjhiji","54/56/7879","hkgksdvlshdlvhsl","ffkgfjflkgk");
				///DBController.editStudentAssignment("123456782","1","hihihihihi","0","hihihihihihi","hihihihihihi","1");
				return 1;
			}catch (SQLException ex)
			{
				return 0;
			}
		}
	  /**
	   * This method handles any messages received from the client.
	   *
	   * @param msg The message received from the client.
	   * @param client The connection from which the message originated.
	   */
	  /*The handleMessageFromClient method get array list with action to do and the data 
	   * for this action for the prototype the method check if the action was update or
	   * view. The method is start the method that does the action and send to the client the result 
	   * for this action. */
	  public void handleMessageFromClient (Object msg, ConnectionToClient client)  {
		  ArrayList<String> action =  new ArrayList<String>();
		  Object answer=null;
		  action=(ArrayList<String>)msg;
		  answer=CheckOption.CheakOp(action);
			try {
				client.sendToClient(answer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	public static Connection getConnection() {
		return connection;
	} 
	
	 }
	    
	