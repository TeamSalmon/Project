package application;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import entities.*;



public abstract class DBController
{
	private static Connection conn = EchoServer.getConnection();
	private static Statement stmt;
	private static String x;
	
	public static void deleteAssignment(String assignmentId)
	{
		try {
			stmt = conn.createStatement();
			String sql="DELETE FROM mat_db.assignment WHERE assignmentId = " + assignmentId;
			}
		catch (SQLException e){e.printStackTrace();}
	}
	public static ArrayList<Object> getAllSemesters()
	{
        ArrayList<Object> answer= new ArrayList<Object>();
		
		try {
			stmt = conn.createStatement();
			String sql="SELECT distinct semester_year,semesterNumber FROM mat_db.report";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				 ArrayList<String> x= new ArrayList<String>();
				 x.add(rs.getString(1));
				 x.add(rs.getString(2));
				answer.add(x);
			}
		} catch (SQLException e){e.printStackTrace();}
		return answer;
	}
	/**
	 * Goes to the DB and create a list of all the class that exists in DB.
	 * @exception  SQLException when one of the connections falls.  
	 * @param no parameters.
	 * @return object arrayList with string ArrayLists inside with data of classes .
	 * @return [0]=studentsAmount,[1]=level,[2]=StudentsClassID,[3]=className.
	 * @author yevgeni_gitin. 
	  */
	public static ArrayList<Object> studentClassesByID(){
		ArrayList<Object> answer= new ArrayList<Object>();
		try {
			stmt = conn.createStatement();
			String sql="SELECT * FROM studentsclass";
			ResultSet rs = stmt.executeQuery(sql);
				
			while (rs.next()){
				ArrayList<String> x= new ArrayList<String>();
				x.add(Integer.toString(rs.getInt(2)));
				x.add(rs.getString(3));
				x.add(rs.getString(1));
				x.add(rs.getString(4));
				answer.add(x);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answer;
	}

	/**
	 * Goes to the DB and create a list of all the students that don't have class .
	 * @exception  SQLException when one of the connections falls.
	 * @param level of the student.
	 * @return object arrayList with string ArrayLists inside with data of students .
	 * @return [0]=studentId,[1]=first_name,[2]=last_name,[3]=Password.
	 *  @author yevgeni_gitin.
	 */
	public static ArrayList<Object> studentsWithoutClass(String level){
		ArrayList<Object> answer= new ArrayList<Object>();
		try {
			stmt = conn.createStatement();
			String sql="SELECT mat_db.user.id,mat_db.user.last_name,mat_db.user.first_name,mat_db.user.Password  FROM mat_db.user,mat_db.userextension WHERE mat_db.user.id=mat_db.userextension.user_id AND mat_db.userextension.sClass='NULL' AND mat_db.userextension.level='"+level+"' AND mat_db.userextension.permission=0";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x= new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(3));
				x.add(rs.getString(2));
				x.add(rs.getString(4));
				answer.add(x);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answer;
	}
	
	/**
	 *Goes to the DB and create a list of all the courses.
	 * @exception  SQLException when one of the connections falls.
	 * @param no parameters.
	 * @return array list of all the courses.
	 *  @return [0]=courseNumber,[1]=weeklyHours,[2]=name,[3]=semester_year,[4]=semesterNumber,[5]=correntSemester.
	 *  @author yevgeni_gitin. 
	 */
	public static ArrayList<Object> getAllCourses(){
		ArrayList<Object> answer= new ArrayList<Object>();
		try {
			stmt = conn.createStatement();
			String sql="SELECT *  FROM mat_db.course GROUP BY mat_db.course.courseNumber" ;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
			
			ArrayList<String> x= new ArrayList<String>();
			x.add(rs.getString(1));
			x.add(Float.toString(rs.getFloat(4)));
			x.add(rs.getString(2));
			x.add(rs.getString(6));
			x.add(rs.getString(7));
			x.add(rs.getString(8));
				answer.add(x);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * Goes to the DB and create a list of all the preconditions of the course that the function gets.
	 * @exception  SQLException when one of the connections falls.
	 * @param course number.
	 * @return arrayList<String> of all course's  preconditions.
	 * @return [0]=Pre-course
	 * @author yevgeni_gitin.
	 */
	public static ArrayList<String> coursePrecondition(String courseId){
		ArrayList<String> answer= new ArrayList<String>();
		
		try {
			stmt = conn.createStatement();
			String sql="SELECT *  FROM mat_db.course_has_precourse WHERE mat_db.course_has_precourse.course='"+courseId+"'" ;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * Goes to the DB and create a list of all the students that study in the class that the method gets.
	 * @exception  SQLException when one of the connections falls.
	 * @param classId
	 * @return array list of all students that learn in specific class.
	 * @return [0]=id,[1]=first_name,[2]=last_name,[3]=birthday.
	 * @author yevgeni_gitin.
	 */
	public static ArrayList<Object> studentsOfClass(String classId){
          ArrayList<Object> answer= new ArrayList<Object>();
		
		try {
			stmt = conn.createStatement();
			String sql="SELECT mat_db.user.*  FROM mat_db.user,mat_db.userextension WHERE mat_db.user.id=mat_db.userextension.user_id AND mat_db.userextension.permission=0 AND mat_db.userextension.sClass='"+classId+"'" ;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				 ArrayList<String> x= new ArrayList<String>();
				 x.add(rs.getString(1));
				 x.add(rs.getString(3));
				 x.add(rs.getString(2));
				 x.add(rs.getString(5));
				answer.add(x);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}

	/**
	 * The method changes user's status to 0(disconnected).
	 * @exception  SQLException when one of the connections falls.
	 * @param user id.
	 * @author yevgeni_gitin.
	 */
	public static void logOut(String userId){
		try {
			PreparedStatement logOut = conn.prepareStatement("UPDATE mat_db.user " + "SET mat_db.user.status = ? " + " WHERE mat_db.user.id = ? ");
			logOut.setInt(1, 0);
			logOut.setString(2, userId);
			logOut.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/**
 * 	Goes to the DB and create a list of all  student's courses that the student done.
 * @param student id.
 * @return list of courses.
 * @return [0]=CourseId,[1]=CourseName.
 * @author yevgeni_gitin.
 */
	public static ArrayList<Object>studentOldCourses(String studentId){
		 ArrayList<Object> answer= new ArrayList<Object>();
		 String sql="SELECT mat_db.course.*  FROM mat_db.report,mat_db.course WHERE mat_db.report.courseNumber=mat_db.course.courseNumber AND mat_db.course.correntSemester=0 AND mat_db.report.studentId='"+studentId+"' AND mat_db.report.studentGrsdeInCourse>-1" ;
		 answer=getCourseNumberName(sql);
			return answer;
		}	

	/**
	 * Goes to the DB and create a list of all  teacher's courses that he teaches in specific semester.
	 * @param techer id.
	 * @param techer year.
	 * @param semesterNumber.
	 * @return list of courses.
	 * @return [0]=CourseId,[1]=CourseName.
	 * @author yevgeni_gitin.
	 */
	public static ArrayList<Object>courseByTeacher(String teacherId,String year,String semesterNumber){
		 ArrayList<Object> answer= new ArrayList<Object>();
		 String sql="SELECT distinct mat_db.course.*  FROM mat_db.report,mat_db.course WHERE mat_db.report.courseNumber=mat_db.course.courseNumber AND mat_db.course.semester_year='"+year+"' AND mat_db.course.semesterNumber='"+semesterNumber+"' AND mat_db.report.teasherId='"+teacherId+"' " ;
		 answer=getCourseNumberName(sql);
			return answer;
		}	
	
	/**
	 * Goes to the DB and create a list of all  courses that the query needs.
	 * @exception  SQLException when one of the connections falls.
	 * @param sql query.
	 * @return array list of courses.
	 * @return [0]=CourseId,[1]=CourseName.
	 * @author yevgeni_gitin.
	 */
	public static ArrayList<Object> getCourseNumberName(String sql){
		ArrayList<Object> answer= new ArrayList<Object>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x= new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				x.add(rs.getString(3));
				x.add(Float.toString(rs.getFloat(4)));
				x.add(rs.getString(5));
				answer.add(x);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * Goes to the DB and return current semester.
	 *  @exception  SQLException when one of the connections falls.
	 *  @return current semester.
	 *  @return [0]=emester_year,[1]=semesterNumber.
	 *  @author yevgeni_gitin.
	 */
	public static ArrayList<String> CurrentSemester(){
		ArrayList<String> answer = new ArrayList<String>();
		String sql="SELECT Distinct mat_db.course.semester_year,mat_db.course.semesterNumber  FROM mat_db.course WHERE mat_db.course.correntSemester=1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(rs.getString(1));
				answer.add(rs.getString(2));
	     			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * Goes to the DB and and return list of all course's assignments.
	 *  @exception  SQLException when one of the connections falls.
	 *  @param course id.
	 *  @return list of assignments.
	 *  @return [0]=assignmentId,[1]=courseID,[2]=assignmentName,[3]=deadline,[4]=percentageOfFinalGrade,[5]=instructionForSubmission.
	 *  @author yevgeni_gitin.
	 */
	public static ArrayList<Object> getCourseAssignments(String courseId){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT *  FROM assignment WHERE mat_db.assignment.course='"+courseId+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x =new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				x.add(rs.getString(3));
				x.add(rs.getString(4));
				x.add(Integer.toString(rs.getInt(5)));
				x.add(rs.getString(6));
				answer.add(x);
	     			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method get user id then check if the user is student or teacher and create list of semesters that this user teach or study in.
	 *  @exception  SQLException when one of the connections falls.
	 *  @param user id.
	 *  @return list of Semesters or null if no such teacher or student.
	 *  @return [0]=semester_year,[1]=semesterNumber.
	 *   @author yevgeni_gitin.
	 */
	public static ArrayList<Object> getSemesters(String Id){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT *  FROM mat_db.userextension WHERE  mat_db.userextension.user_id='"+Id+"'";
		try {
			int p=-1;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				 p =rs.getInt(2);
	     			}
			if(p==0)
				sql="SELECT Distinct mat_db.report.semester_year,mat_db.report.semesterNumber FROM mat_db.report WHERE mat_db.report.studentId='"+Id+"'";
			if(p==2)
				sql="SELECT Distinct mat_db.report.semester_year,mat_db.report.semesterNumber FROM mat_db.report WHERE mat_db.report.teasherId='"+Id+"'";
			 rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x = new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				answer.add(x);
	     			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method gets user id and checks if the user exists in DB then checks if the user is already blocked if not the method blocks this user.   
	 * @exception  SQLException when one of the connections falls.
	 * @param user id.
	 * @return String(message).
	 * @author yevgeni_gitin.
	 */
	public static String block(String id){
		String answer="No such user";
		String sql="SELECT *  FROM mat_db.userextension WHERE mat_db.userextension.user_id='"+id+"'";
		try {
			int block=-1;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				block=rs.getInt(10);
	     			}
			if(block==1)
				answer="The user is already blocked";
			if(block==0){
				answer="The user was blocked successfully";
				PreparedStatement logOut = conn.prepareStatement("UPDATE mat_db.userextension " + "SET mat_db.userextension.blockedFlag = ? " + " WHERE mat_db.userextension.user_id = ? ");
				logOut.setInt(1, 1);
				logOut.setString(2, id);
				logOut.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method gets user id and checks if the user exists in DB then checks if the user is already unblocked if not the method unblocks this user.   
	 * @exception  SQLException when one of the connections falls.
	 * @param user id.
	 * @return String(message).
	 * @author yevgeni_gitin. 
	 */
	public static String unblock(String id){
		String answer="No such user";
		String sql="SELECT *  FROM mat_db.userextension WHERE mat_db.userextension.user_id='"+id+"'";
		try {
			int block=-1;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				block=rs.getInt(10);
	     			}
			if(block==0)
				answer="The user is already unblocked";
			if(block==1){
				answer="The user was unblocked successfully";
				PreparedStatement logOut = conn.prepareStatement("UPDATE mat_db.userextension " + "SET mat_db.userextension.blockedFlag = ? " + " WHERE mat_db.userextension.user_id = ? ");
				logOut.setInt(1, 0);
				logOut.setString(2, id);
				logOut.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method makes some updates for log in.
	 * @exception  SQLException when one of the connections falls.
	 * @param user id.
	 * @param user status.
	 * @param user counter of try.
	 * @param new block Flag.
	 * @author yevgeni_gitin. 
	 */
	public static void updateUser(String id,String status,String counter){
		PreparedStatement update;
		try {
			update = conn.prepareStatement("UPDATE mat_db.user " + "SET mat_db.user.status = ? ,mat_db.user.lockCounter = ? " + " WHERE mat_db.user.id = ? ");
			update.setInt(1, Integer.parseInt(status));
			update.setInt(2, Integer.parseInt(counter));
			update.setString(3, id);
			update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *The method search user in DB by user id and return him to the client.
	 * @exception  SQLException when one of the connections falls.
	 *  @param user id.
	 *  @return ArrayList<String>.
	 *  @return [0]=id,[1]=first_name,[2]=last_name,[3]=Password,[4]=lockCounter,[5]=Permission,[6]=status,[7]=blockedFlag.
	 *  @author yevgeni_gitin.
	*/
	public static ArrayList<String> searchUser(String id){
		ArrayList<String> answer = new ArrayList<String>();
		String sql="SELECT mat_db.user.* ,mat_db.userextension.blockedFlag  FROM mat_db.user,mat_db.userextension WHERE mat_db.user.id='"+id+"' AND mat_db.user.id=mat_db.userextension.user_id";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(rs.getString(1));
				answer.add(rs.getString(3));
				answer.add(rs.getString(2));
				answer.add(rs.getString(4));
				answer.add(Integer.toString(rs.getInt(8)));
				answer.add(Integer.toString(rs.getInt(6)));
				answer.add(Integer.toString(rs.getInt(7)));
				answer.add(Integer.toString(rs.getInt(9)));
	     			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method goes to the DB and checks if the user id is belong to student the method creates student and return him .
	 * @exception  SQLException when one of the connections falls.
	 *  @param user id.
	 *  @return ArrayList<String>.
	 *  @return [0]=id,[1]=first_name,[2]=last_name,[3]=Password;
	 *   @author yevgeni_gitin. 
	 */
	public static ArrayList<String> searchStudentID(String id){
		ArrayList<String> answer= new ArrayList<String>();
		String sql="SELECT mat_db.user.* FROM mat_db.user,mat_db.userextension WHERE mat_db.user.id=mat_db.userextension.user_id AND mat_db.userextension.permission=0 AND mat_db.user.id='"+id+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(rs.getString(1));
				answer.add(rs.getString(3));
				answer.add(rs.getString(2));
				answer.add(rs.getString(4));
	     			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 *The method goes to DB and return all the groups id in this Course.
	 *@exception  SQLException when one of the connections falls.
	 * @param course number.
	 * @return String or null.
	 * @author yevgeni_gitin. 
	 */
	public static ArrayList<String> searchCourseNum(String courseNum){
		ArrayList<String> answer= new ArrayList<String>();
		String sql="SELECT mat_db.classcourse.ClassCourseID FROM mat_db.classcourse WHERE mat_db.classcourse.courseNumber='"+courseNum+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(rs.getString(1));
	     			}
			if(answer.size()==0)
				answer=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method checks if the student exists in the course. 
	 * @exception  SQLException when one of the connections falls.
	 * @param course number.
	 * @param student id.
	 * @return true or false.
	 * @author yevgeni_gitin.
	 */
	public static boolean searchStudentInCourse(String courseId,String studentId){
		boolean answer= false;
		String sql="SELECT * FROM mat_db.report WHERE mat_db.report.studentId='"+studentId+"' AND mat_db.report.courseNumber='"+courseId+"' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer=true;
	     			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 *The method returns all the grades by group  for specific teacher,year,semester.
	 *@exception  SQLException when one of the connections falls.
	 *@param semester year.
	 *@param teacher id.
	 *@param semester. 
	 *@return (ArrayList<Object>) list of group and the grades for this group.
	 *@return [0]=classCourseID,[1.........n]and the grades for this group.
	 *@author yevgeni_gitin.	 
	 */
	public static ArrayList<Object> report1(String teacherId,String semesterYear,String semester ){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT Distinct mat_db.report.classCourseID FROM mat_db.report, mat_db.classcourse WHERE mat_db.report.teasherId='"+teacherId+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semester+"' AND  mat_db.report.studentGrsdeInCourse>-1";
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> grade = new ArrayList<String>();
		try {
		
		
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
	
				name.add(rs.getString(1));
				
	     			}
			int size= name.size();
			for(int i=0;i<size;i++){
			 sql="SELECT AVG(mat_db.report.studentGrsdeInCourse)FROM mat_db.report WHERE mat_db.report.teasherId='"+teacherId+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semester+"' AND mat_db.report.classCourseID='"+name.get(i)+"' AND  mat_db.report.studentGrsdeInCourse>-1";
			 rs = stmt.executeQuery(sql);
			 while (rs.next()){
					grade.add(Float.toString(rs.getFloat(1)));
		     			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		answer.add(name);
		answer.add(grade);
		return answer;
	}
	
	/**
	 *The method returns all the grades by teacher  for specific class,year,semester.
	 *@exception  SQLException when one of the connections falls.
	 *@param semester year.
	 *@param classr Id.
	 *@param semester. 
	 *@return list of teachers and grades.
	 *@return [0]=teacherId.[1]=firstName,[2]=lastName,[3........n]=grades.
	 *@author yevgeni_gitin.	 
	 */
	public static ArrayList<Object> report2(String classId,String semesterYear,String semester){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT Distinct mat_db.user.* FROM mat_db.report, mat_db.user WHERE mat_db.report.StudentsClassID='"+classId+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semester+"' AND  mat_db.user.id=mat_db.report.teasherId AND  mat_db.report.studentGrsdeInCourse>-1";
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> grade = new ArrayList<String>();
		ArrayList<String> need = new ArrayList<String>();
		String temp;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				temp=rs.getString(1);
				need.add(temp);
				name.add(temp+" "+rs.getString(3)+" "+rs.getString(2));
			
				
	     			}
			int size= name.size();
			
			for(int i=0;i<size;i++){
			 sql="SELECT AVG(mat_db.report.studentGrsdeInCourse)FROM mat_db.report WHERE mat_db.report.teasherId='"+need.get(i)+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semester+"' AND mat_db.report.StudentsClassID='"+classId+"' AND  mat_db.report.studentGrsdeInCourse>-1";
			 rs = stmt.executeQuery(sql);
			 while (rs.next()){
				 grade.add(Float.toString(rs.getFloat(1)));
		     			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		answer.add(name);
		answer.add(grade);
		return answer;
	}
	
	/**
	 *The method returns all the grades by course  for specific class,year,semester.
	 *@exception  SQLException when one of the connections falls.
	 *@param semester year.
	 *@param classr Id.
	 *@param semester. 
	 *@return list of courses and grades.
	 *@return [0]=courseNumber,[1]=name,[2........n]=grades.
	 *@author yevgeni_gitin.	 
	 */
	public static ArrayList<Object> report3(String classId,String semesterYear,String semester){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT Distinct mat_db.course.courseNumber,mat_db.course.name FROM mat_db.report, mat_db.course WHERE mat_db.report.StudentsClassID='"+classId+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semester+"' AND  mat_db.course.courseNumber=mat_db.report.courseNumber AND  mat_db.report.studentGrsdeInCourse>-1 ";
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> grade = new ArrayList<String>();
		ArrayList<String> need = new ArrayList<String>();
		String temp;
		try {
		
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				
				
				temp=rs.getString(1);
				need.add(temp);
				name.add(temp+" "+rs.getString(2));
		
	     			}
			int size= name.size();
			
			for(int i=0;i<size;i++){
				
			 sql="SELECT AVG(mat_db.report.studentGrsdeInCourse) FROM mat_db.report WHERE mat_db.report.courseNumber='"+need.get(i)+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semester+"' AND mat_db.report.StudentsClassID='"+classId+"' AND  mat_db.report.studentGrsdeInCourse>-1";
			 rs = stmt.executeQuery(sql);
			 while (rs.next()){
				 grade.add(Float.toString(rs.getFloat(1)));
		     			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		answer.add(name);
		answer.add(grade);
		return answer;

	}
	
	/**
	 *The method return all the Teaching Units that exists in DB.
	 *@exception  SQLException when one of the connections falls.
	 *@return (ArrayList<Object>) list of Teaching Units.
	 *@return [0]=number,[1]=name.
	 *@author yevgeni_gitin.  
	 */
	public static ArrayList<Object> allTeachingUnits(){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT * FROM mat_db.teachingunit";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x= new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				answer.add(x);
	     			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method return all the teachers that teach the specific course.
	 * @exception  SQLException when one of the connections falls.
	 * @param course Id.
	 * @return ArrayList of teachers.
	 * @return [0]=weeklyHours,[1]=id,[2]=first_name,[3]=last_name,[4]=Password.
	 * @author yevgeni_gitin.
	 */
	public static ArrayList<Object> optionalTeachersForCourse(String courseId){
		ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT  mat_db.userextension.weeklyHours,mat_db.user.* FROM mat_db.teacher_has_teachingunit, mat_db.course, mat_db.userextension,mat_db.user WHERE mat_db.course.courseNumber='"+courseId+"' AND mat_db.course.teachingUnit=mat_db.teacher_has_teachingunit.teachingunit_number  AND mat_db.teacher_has_teachingunit.user_id=mat_db.userextension.user_id AND mat_db.user.id=mat_db.userextension.user_id ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x= new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				x.add(rs.getString(4));
				x.add(rs.getString(3));
				x.add(rs.getString(5));
				answer.add(x);
	     			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	/**
	 * The method write a new class to DB and return true if succeed or false if not.
	 * @exception  SQLException when one of the connections falls.
	 * @param class Id.
	 * @param amount.
	 * @param level.
	 * @param class Name.
	 * @return true or false.
	 * @author yevgeni_gitin.
	 */
	public static boolean addNewClass(String classId,String amount,String level,String className){
		boolean answer=true;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO studentsclass"+" VALUES('"+classId+"',"+Integer.parseInt(amount)+",'"+level+"','"+className+"')");
			} catch (SQLException e) {
				answer=false;
				e.printStackTrace();
			}
			return answer;
	}
	
	/**
	 *The method write student to class.
	 *@exception  SQLException when one of the connections falls.
	 *@param id.
	 *@param classNumber.
	 *@return true or false.
	 *@author yevgeni_gitin. 
	 */
	public static boolean updateClassOfStudent(String id,String classNumber){
		PreparedStatement update;
		boolean answer=true;
		try {
			update = conn.prepareStatement("UPDATE mat_db.userextension " + "SET mat_db.userextension.sClass = ? " + " WHERE mat_db.userextension.user_id = ? ");
			update.setString(1,classNumber);
			update.setString(2,id);
			
			update.executeUpdate();
		} catch (SQLException e) {
			answer=false;
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method return if the specific student study the specific course in current semester.
	 * @exception  SQLException when one of the connections falls.
	 * @param course Id.
	 *@param student Id.
	 *@return true or false.
	 *@author yevgeni_gitin.
	 */
	public static boolean searchStudentInCourseCurrentSemester(String courseId ,String studentId){
		boolean answer=false;
		String sql="SELECT  mat_db.report.* FROM mat_db.report WHERE mat_db.report.courseNumber='"+courseId+"' AND mat_db.report.studentId='"+studentId+"' AND mat_db.report.studentGrsdeInCourse=-1";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer=true;
	     			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return answer;
	}
	
	/**
	 * The method delete a student from course and return true if succeed else false.
	 *  @exception  SQLException when one of the connections falls.
	 *  @param course Id.
	 *  @param student Id.
	 *  @return true or false.
	 *  @author yevgeni_gitin.
	 */
	public static boolean removeStudentFromCourse(String studentId,String courseNum){
		boolean answer=true;
		try {
			PreparedStatement delete =conn.prepareStatement("DELETE FROM mat_db.report WHERE mat_db.report.studentId='"+studentId+"' AND mat_db.report.courseNumber='"+courseNum+"'AND mat_db.report.studentGrsdeInCourse=-1 ");
			delete.executeUpdate();
			delete.close();
		} catch (SQLException e) {
			answer=false;
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method delete request for specific student in specific course and description if succeed return true else false.
	 * @exception  SQLException when one of the connections falls.
	 * @param course Id.
	 * @param student Id.
	 * @param description.
	 * @return true or false.
	 * @author yevgeni_gitin.
	 */
	public static boolean removeStudentFromCourseRequest(String studentId,String courseNum,String description){
		boolean answer=true;
		try {
			PreparedStatement delete =conn.prepareStatement("DELETE FROM mat_db.request WHERE mat_db.request.userID='"+studentId+"' AND mat_db.request.courseNumber='"+courseNum+"'AND mat_db.request.description='"+description+"'");
			delete.executeUpdate();
			delete.close();
		} catch (SQLException e) {
			answer=false;
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * The method returns all students ID and their Class IDs by classCourse ID
	 * @param classCourseID
	 * @return studentId and studentsClassID
	 * @author Mariya Portnoy
	 */
	public static ArrayList<Object> studentsOfCourseClass(String classCourseID){
	ArrayList<Object> answer= new ArrayList<Object>();
	String sql="SELECT *  FROM mat_db.report WHERE mat_db.report.classCourseID='"+classCourseID+"'";
	try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			ArrayList<String> x =new ArrayList<String>();
			x.add(rs.getString(2));
			x.add(rs.getString(3));
			answer.add(x);
			}
		if(answer.size()==0)
			answer=null;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return answer;
}
	
	/**
	 * The method updates students class name and level
	 * @param StudentsClassID
	 * @param level
	 * @param className
	 * @return true if there was some updates else false
	 *  @author Mariya Portnoy
	 */
	public static boolean updateSclassDetails(String StudentsClassID,String level, String className){

	String sql = "UPDATE mat_db.studentsclass SET level=?, className=? WHERE StudentsClassID=?";
	try {
		stmt = conn.createStatement();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, level);
		statement.setString(2, className);
		statement.setString(3, StudentsClassID);
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated <= 0) {
		    return false;
		}

	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
}
	
	/**
	 *The method update teachers Weekly Hours.
	 *@exception  SQLException when one of the connections falls.
	 *@param teacherId.
	 *@param newWeeklyHours.
	 *@return true or false.
	 *@author yevgeni_gitin.
	 */
	public static boolean updateTeacher(String teacherId,String newWeeklyHours){
		boolean answer=true;
		String sql = "UPDATE mat_db.userextension SET weeklyHours=? WHERE user_id=?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setFloat(1, Float.parseFloat(newWeeklyHours));
			statement.setString(2, teacherId); 
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			answer=false;
		}
		return answer;
	}

	/**
	 *The method return all course's groups id.
	 *@exception  SQLException when one of the connections falls.
	 *@param courseID.
	 *@return ArrayList<String> of all groups id.
	 *@author yevgeni_gitin.
	 */
	public static ArrayList<String> groupsOfCourse(String courseID){
		ArrayList<String> answer= new ArrayList<String>();
		String sql="SELECT  mat_db.classcourse.ClassCourseID FROM  mat_db.classcourse WHERE mat_db.classcourse.courseNumber='"+courseID+"'";
		try {
		
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(rs.getString(1));
	     			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	/**
	 * 
	 */
   public static boolean updateStudentCourse(String studentID,String CourseId){
	boolean answer=true;
	String sql = "UPDATE mat_db.report SET courseNumber=? WHERE studentId=?";
	try {
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, CourseId);
		statement.setString(2, studentID); 
		statement.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		answer=false;
	}
	return answer;
}

/**
 *The method return all the course number and name that the specific student learned in specific year and semester.
 *@param techer id.
 * @param techer year.
 * @param semesterNumber.
 * @return list of courses.
 * @return [0]=CourseId,[1]=CourseName.
 * @author yevgeni_gitin.  
 */
 public static ArrayList<Object> courseByStudent(String StudentId,String year,String semesterNumber){
	 ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT distinct mat_db.course.* FROM mat_db.report,mat_db.course WHERE mat_db.report.studentId='"+StudentId+"' AND mat_db.course.semester_year='"+year+"' AND  mat_db.course.semesterNumber='"+semesterNumber+"' AND mat_db.report.courseNumber=mat_db.course.courseNumber";
		answer=getCourseNumberName(sql);
		return answer; 
 }
public static ArrayList<Object>getStudentAssignment(String studentId, String assignmentId)
{
	 ArrayList<Object> answer= new ArrayList<Object>();
	 int id = Integer.parseInt(assignmentId);
	 String sql="SELECT * FROM mat_db.studentassignment WHERE assignment= " + id + " AND student = '" + studentId + "'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				answer.add(Integer.toString(rs.getInt(1)));
				answer.add(rs.getString(2));
				answer.add(rs.getString(3));
				answer.add(Integer.toString(rs.getInt(4)));
				answer.add(rs.getString(5));
				answer.add(rs.getString(6));
				answer.add(Integer.toString(rs.getInt(7)));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	 return answer;
}
 /**
  * The method return all parent's children.
  * @param parentId.
  * @exception  SQLException when one of the connections falls.
  * @return ArrayList<Object>.
  * @return [0]=id,[1]=first_name,[2]=last_name.
  * @author yevgeni_gitin.
  */
 public static ArrayList<Object> getChildren(String parentId){
	 ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT mat_db.user.id,mat_db.user.first_name,mat_db.user.last_name  FROM mat_db.studentparent,mat_db.user WHERE mat_db.studentparent.Parent='"+parentId+"' AND mat_db.studentparent.Student=mat_db.user.id";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x =new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				x.add(rs.getString(3));
				answer.add(x);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
 }
 
 /**
  * The method get teaching Unit Id and return all the courses that belongs to the teaching Unit Id.
  *@param teachingUnitId.
 * @return list of courses.
 * @return [0]=CourseId,[1]=CourseName.
 * @author yevgeni_gitin.  
  */
 public static ArrayList<Object> getTeachingUnitCourse(String teachingUnitId){
	 ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT Distinct mat_db.course.courseNumber,mat_db.course.name  FROM mat_db.course WHERE mat_db.course.teachingUnit='"+teachingUnitId+"'";
		answer=getCourseNumberName(sql);
		return answer; 
 }
 
/**
 *The method get class,year,semester and return all the courses that the class studies in specific year and semester.
 * @param classId.
 * @param SemesterYear.
 * @param semesterNumber.
 * @return list of courses.
 * @return [0]=CourseId,[1]=CourseName.
 * @author yevgeni_gitin.  
 */
 public static ArrayList<Object> getClassCourses(String classId,String SemesterYear,String semesterNumber){
	 ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT Distinct mat_db.course.courseNumber,mat_db.course.name  FROM mat_db.course,mat_db.classcourse WHERE mat_db.classcourse.StudentsClassID='"+classId+"' AND mat_db.classcourse.semester_year='"+SemesterYear+"' AND mat_db.classcourse.course_semesterNumber='"+semesterNumber+"' AND mat_db.classcourse.courseNumber=mat_db.course.courseNumber";
		answer=getCourseNumberName(sql);
		return answer;  
 }
 
/**
 *The method returns teacher that teaches  specific course,class,year,semester.
 *@exception  SQLException when one of the connections falls.
 *@param semester year.
 *@param CourseId.
 *@param class Id.
 *@param semester. 
 *@return ArrayList<String> teacher.
 *@return [0]=teacherId.[1]=firstName,[2]=lastName.
 *@author yevgeni_gitin.	
 */
 public static ArrayList<String> getClassTeacherInCourse(String classId,String CourseId,String semesterYear,String semesterNumber){
		ArrayList<String> answer= new ArrayList<String>();
		String sql="SELECT Distinct mat_db.user.* FROM mat_db.report, mat_db.user WHERE mat_db.report.StudentsClassID='"+classId+"' AND  mat_db.report.semester_year='"+semesterYear+"' AND  mat_db.report.semesterNumber='"+semesterNumber+"' AND  mat_db.user.id=mat_db.report.teasherId AND mat_db.report.courseNumber='"+CourseId+"'";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x= new ArrayList<String>();
				answer.add(rs.getString(1));
				answer.add(rs.getString(3));
				answer.add(rs.getString(2));
	     			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
 }
 
 /**
  *The method return all the courses that the specific teacher teaches on specific year and semester.
  *@exception  SQLException when one of the connections falls.
  *@param semester year.
  *@param teacherId.
  *@param semester. 
  *@return ArrayList<String> courses.
  *@return [0]=courseNumber.[1]=courseName.
  *@author yevgeni_gitin.	
  */
 public static ArrayList<Object> getTeacherCourses(String teacherId,String semesterYear,String semesterNumber){
	 ArrayList<Object> answer= new ArrayList<Object>();
		String sql="SELECT Distinct mat_db.course.courseNumber,mat_db.course.name  FROM mat_db.classcourse,mat_db.course WHERE mat_db.classcourse.Teacher='"+teacherId+"' AND mat_db.classcourse.semester_year='"+semesterYear+"' AND mat_db.classcourse.course_semesterNumber='"+semesterNumber+"' AND mat_db.classcourse.courseNumber=mat_db.course.courseNumber";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				ArrayList<String> x =new ArrayList<String>();
				x.add(rs.getString(1));
				x.add(rs.getString(2));
				answer.add(x);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer; 
 }
 
 /**
  *The method returns all the Submissions of a specific Assignment.
  *@exception  SQLException when one of the connections falls. 
  *@param AssignmentId.
  *@return ArrayList<String> Submissions.
  *@return [0]= assignmentId,[1]=studentId,[2]=grade,[3]=comments,[4]=evaluationFormPath,[5]=lateFlag;
  *@author inbar.
  */
 public static ArrayList<Object> getSubmissions(String AssignmentId)
 {
	 ArrayList<Object> answer= new ArrayList<Object>();
	 int assignmentId = Integer.parseInt(AssignmentId);
		String sql="SELECT * FROM mat_db.studentassignment WHERE assignment = " + assignmentId;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				ArrayList<String> x =new ArrayList<String>();
				answer.add(Integer.toString(rs.getInt(1)));
				x.add(rs.getString(2));
				x.add(Integer.toString(rs.getInt(3)));
				x.add(rs.getString(4));
				x.add(rs.getString(5));
				x.add(Integer.toString(rs.getInt(6)));
				answer.add(x);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
 }
 
 
 /**
  * The method get new Assignment and enters the Assignment to the DB  and return his index.
  *@exception  SQLException when one of the connections falls. 
  *@param courseId.
  *@param assignmentName.
  *@param deadline.
  *@param instructions.
  *@param filepath.
  *@return index.
  *@author yevgeni_gitin.
  */
 public static ArrayList<String> createAssignment(String courseId,String assignmentName,String deadline,String instructions,String filepath){
	 ArrayList<String> answer=new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO assignment(course,assignmentName,deadline,percentageOfFinalGrade,instructionForSubmission,assignmentFile)"+" VALUES('"+courseId+"','"+assignmentName+"','"+deadline+"',-1,'"+instructions+"','"+filepath+"')");
			String sql="SELECT mat_db.assignment.assignmentId  FROM mat_db.assignment WHERE mat_db.assignment.course='"+courseId+"' AND mat_db.assignment.assignmentName='"+assignmentName+"' AND mat_db.assignment.deadline='"+deadline+"' AND mat_db.assignment.instructionForSubmission='"+instructions+"' AND mat_db.assignment.assignmentFile='"+filepath+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				answer.add(Integer.toString(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
 }
 
 public static void createSubmission(String studentId, String assignmentId, String filePath)
 {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO studentassignment(assignment,student,submission)"+" VALUES('"+studentId+"','"+assignmentId+"','"+filePath+"')");
			}catch (SQLException e){e.printStackTrace();
		}
 }
 
/**
 *  The method set data in specific assignment if succeed return true else false .
  *@exception  SQLException when one of the connections falls. 
  *@param courseId.
  *@param assignmentId
  *@param assignmentName.
  *@param deadline.
  *@param instructions.
  *@param filepath.
  *@return true or false.
  *@author yevgeni_gitin.
 */
 public static boolean editAssignment(String assignmentId,String courseId,String assignmentName,String deadline,String instructions,String filepath){
	 PreparedStatement update;
	 boolean answer=true;
		try {
			update = conn.prepareStatement("UPDATE mat_db.assignment " + "SET mat_db.assignment.course = ? ,mat_db.assignment.assignmentName = ? ,mat_db.assignment.deadline = ? ,mat_db.assignment.instructionForSubmission = ?,mat_db.assignment.assignmentFile = ?" + " WHERE mat_db.assignment.assignmentId = ? ");
			update.setString(1, courseId);
			update.setString(2, assignmentName);
			update.setString(3, deadline);
			update.setString(4, instructions);
			update.setString(5, filepath);
			update.setInt(6, Integer.parseInt(assignmentId));
			update.executeUpdate();
		} catch (SQLException e) {
			answer=false;
			e.printStackTrace();
		} 
 return answer;
 }
 
/**
 *The method updates some student's assignment.
 *@exception  SQLException when one of the connections falls. 
  *@param StudentId.
  *@param assignmentId
  *@param assignmentName.
  *@param deadline.
  *@param submissionFilePath.
  *@param filepath.
  *@return true or false.
  *@author yevgeni_gitin.  
 */
 public static boolean editStudentAssignment(String StudentId,String assignmentId,String submissionFilePath,String grade,String comments,String evaluationFormPath,String lateFlag){
	 PreparedStatement update;
	 boolean answer=true;
		try {
			update = conn.prepareStatement("UPDATE mat_db.studentassignment " + "SET mat_db.studentassignment.student = ? ,mat_db.studentassignment.submission = ? ,mat_db.studentassignment.grade = ? ,mat_db.studentassignment.comments = ?,mat_db.studentassignment.evaluationForm = ? ,mat_db.studentassignment.lateFlag = ?" + " WHERE mat_db.studentassignment.assignment = ? ");
			update.setString(1, StudentId);
			update.setString(2, submissionFilePath);
			update.setInt(3, Integer.parseInt(grade));
			update.setString(4, comments);
			update.setString(5,evaluationFormPath);
			update.setInt(6, Integer.parseInt(lateFlag));
			update.setInt(7, Integer.parseInt(assignmentId));
			update.executeUpdate();
		} catch (SQLException e) {
			answer=false;
			e.printStackTrace();
		} 
 return answer;
 }
 
 /**
  *The method set a new teacher to the groupe.
  *@exception  SQLException when one of the connections falls.
  *@param getclassCourseID.
  *@param getTeacherID.
  *@return true or false.
  *@author yevgeni_gitin.  
 */
public static ArrayList<String> updateTinReport(String getclassCourseID,String getTeacherID){
	 PreparedStatement update;
	 ArrayList<String> answer = new ArrayList<String>();
		try {
			update = conn.prepareStatement("UPDATE mat_db.classcourse SET mat_db.classcourse.Teacher= ? WHERE mat_db.classcourse.ClassCourseID= ?");
			update.setString(1,getTeacherID);
			update.setString(2, getclassCourseID);
			update.executeUpdate();
			answer.add("true");
		} catch (SQLException e) {
			answer.add("false");
			e.printStackTrace();
		} 
return answer;
}


/**
 *The method search teacherID and the amount of student in DB by classCourseID  and return them to the client.
 * @exception  SQLException when one of the connections falls.
 *  @param classCourseID
 *  @return ArrayList<String>.
 *  @return [0]=Amount,[1]=teacherID
 *  @author Galit
*/
public static ArrayList<Object> getAmountAndTinClassCourse(String classCourseID){
	ArrayList<Object> answer = new ArrayList<Object>();
	String sql="SELECT  mat_db.classCourse.studentsAmount ,mat_db.classCourse.Teacher  FROM mat_db.classCourse WHERE mat_db.classCourse.ClassCourseID='"+classCourseID+"'";
	try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){				
			answer.add(Integer.toString(rs.getInt(4)));
			answer.add(rs.getString(2));
     			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return answer;
}


/**
 *The method search StudentClass in DB by stidentID and return it to the client.
 * @exception  SQLException when one of the connections falls.
 *  @param stidentID
 *  @return ArrayList<String>.
 *  @return [0]=studentClass
 *  @author Galit
*/
public static ArrayList<Object> getStudentClass(String studentID){
	ArrayList<Object> answer = new ArrayList<Object>();
	String sql="SELECT  mat_db.userextension.sClass  FROM mat_db.userextension WHERE mat_db.userextension.user_id='"+studentID+"' AND mat_db.userextension.permission='0'";
	try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){				
			answer.add(rs.getString(5));
     			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return answer;
}


/**
 *The method search CourseNumber of student in DB by classCourseID  and return them to the client.
 * @exception  SQLException when one of the connections falls.
 *  @param classCourseID
 *  @return ArrayList<String>.
 *  @return [0]=CourseNumber
 *  @author Galit
*/
public static ArrayList<Object> getCourseNumber(String classCourseID){
	ArrayList<Object> answer = new ArrayList<Object>();
	String sql="SELECT  distinct mat_db.classCourse.courseNumber  FROM mat_db.classCourse WHERE mat_db.classCourse.ClassCourseID='"+classCourseID+"'";
	try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){				
			answer.add(rs.getString(3));
     			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return answer;
}



/**
 *The method search CourseName of student in DB by courseNumber  and return them to the client.
 * @exception  SQLException when one of the connections falls.
 *  @param CourseName
 *  @return ArrayList<String>.
 *  @return [0]=CourseNumber
 *  @author Galit
*/
public static ArrayList<Object> getCourseName(String courseNumber){
	ArrayList<Object> answer = new ArrayList<Object>();
	String sql="SELECT  distinct mat_db.course.name  FROM  mat_db.course WHERE  mat_db.course.courseNumber='"+courseNumber+"'";
	try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){				
			answer.add(rs.getString(2));
     			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return answer;
}



	/**
 *The method search correntSemester of student in DB and return it to the client.
 * @exception  SQLException when one of the connections falls.
 *  @return ArrayList<String>.
 *  @return [0]=correntSemesterNumber,[1]=correntSemesterYear
 *  @author Galit
*/
public static ArrayList<Object> getCurrentSemesterDB(){
	ArrayList<Object> answer = new ArrayList<Object>();
	String sql="SELECT  distinct mat_db.course.semesterNumber ,mat_db.course.semester_year FROM  mat_db.course WHERE  mat_db.course.correntSemester='1'";
	try {
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){				
			answer.add(rs.getString(7));
			answer.add(rs.getString(6));
     			}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return answer;
}

	/**
 *The method update Amount of students in ClassCourse in DB.
 * @exception  SQLException when one of the connections falls.
 * @param classCourseID
 * @param NewAmount
 * @return [0]="true" OR "false"
 *  @author Galit
*/
public static ArrayList<Object> updateAmountClassCourse(String classCourseID,String NewAmount){
	ArrayList<Object> answer = new ArrayList<Object>();
	String sql="UPDATE mat_db.classCourse SET mat_db.classCourse.studentsAmount=? WHERE mat_db.classCourse.ClassCourseID='?'";
	try {
		stmt = conn.createStatement();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, NewAmount);
		statement.setString(2, classCourseID);
		 
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated <= 0) {
			answer.add("false") ;
		}

	} catch (SQLException e) {
		e.printStackTrace();
		answer.add("false") ;
	}
	answer.add("true") ;
	return answer;
}

/**
 * insert tuple in Report table
 * @param teasherId
 * @param studentId
 * @param ClassOfStudentID
 * @param studentGrsdeInCourse
 * @param courseNumber
 * @param semester_year
 * @param semesterNumber
 * @param classCourseID
 * @return [0]="true" OR "false"
 * @author Galit
 */
public static ArrayList<Object> addStoReport(String teasherId,String studentId,String StudentsClassID,String studentGrsdeInCourse,
		String courseNumber,String semester_year,String SemesterNumber,String classCourseID){
ArrayList<Object> answer = new ArrayList<Object>();
String sql="INSERT INTO mat_db.report (mat_db.report.teasherId,mat_db.report.studentId,mat_db.report.StudentsClassID,mat_db.report.studentGrsdeInCourse,mat_db.report.courseNumber,"
		+ "mat_db.report.semester_year,mat_db.report.semesterNumber,classCourseID)VALUES ( ?,?,?,-1,?,?,?,? )";
		try {
			stmt = conn.createStatement();				
			// create the mysql insert preparedstatement
		      PreparedStatement rs = conn.prepareStatement(sql);
		      rs.setString (1, teasherId);      
		      rs.setString(2, studentId);   
		      rs.setString(3, StudentsClassID);
		    //  rs.setInt(Integer.parseInt(studentGrsdeInCourse));
		      rs.setString(5, courseNumber);      
		      rs.setString(6, semester_year);   
		      rs.setString(7, SemesterNumber);
		      rs.setString(8, classCourseID);
		      // execute the preparedstatement
		      rs.execute();
		      
		      conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			answer.add("false");
			return answer;
		}
		answer.add("true");
		return answer;
	}
 
 
 

}
