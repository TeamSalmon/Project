package application;
import java.util.ArrayList;
import entities.*;
public class CheckOption {
	public static Object CheakOp(ArrayList<String> msg){
		switch (msg.get(0)){
		case "studentClassesByID":
			return (Object)DBController.studentClassesByID();
		case "getStudentAssignment":
		return (Object)DBController.getStudentAssignment(msg.get(1),msg.get(2));
		case "createSubmission":
			DBController.createSubmission(msg.get(1),msg.get(2),msg.get(3));
		case "deleteAssignment":
			DBController.deleteAssignment(msg.get(1));
		case "getAllSemesters":
			return (Object)DBController.getAllSemesters();
		case "studentsWithoutClass":
			return (Object)DBController.studentsWithoutClass(msg.get(1));
		case "getAllCourses":
			return (Object)DBController.getAllCourses();
		case "coursePrecondition":
			return (Object)DBController.coursePrecondition(msg.get(1));
		case "studentsOfClass":
			return (Object)DBController.studentsOfClass(msg.get(1));
		case "logOut":
			DBController.logOut(msg.get(1));
		case "studentOldCourses":
			return (Object)DBController.studentOldCourses(msg.get(1));
		case "courseByTeacher":
			return (Object)DBController.courseByTeacher(msg.get(1),msg.get(2),msg.get(3));
		case "CurrentSemester":
			return (Object)DBController.CurrentSemester();
		case "getCourseAssignments":
			return (Object)DBController.getCourseAssignments(msg.get(1));
		case "getSemesters":
			return (Object)DBController.getSemesters(msg.get(1));
		case "block":
			return (Object)DBController.block(msg.get(1));
		case "unblock":
			return (Object)DBController.unblock(msg.get(1));
		case "updateUser":
			 DBController.updateUser(msg.get(1),msg.get(2),msg.get(3));
		case "searchStudentID":
			return (Object)DBController.searchStudentID(msg.get(1));
		case "searchCourseNum":
			return (Object)DBController.searchCourseNum(msg.get(1));
		case "searchStudentInCourse":
			return (Object)DBController.searchStudentInCourse(msg.get(1),msg.get(2));
		case "report1":
			return (Object)DBController.report1(msg.get(1),msg.get(2),msg.get(3));
		case "report2":
			return (Object)DBController.report2(msg.get(1),msg.get(2),msg.get(3));
		case "report3":
			return (Object)DBController.report3(msg.get(1),msg.get(2),msg.get(3));
		case "searchUser":
			return (Object)DBController.searchUser(msg.get(1));
		case "allTeachingUnits":
			return (Object)DBController.allTeachingUnits();
		case "optionalTeachersForCourse":
			return (Object)DBController.optionalTeachersForCourse(msg.get(1));
		case "addNewClass":
			return (Object)DBController.addNewClass(msg.get(1),msg.get(2),msg.get(3),msg.get(4));
		case "updateClassOfStudent":
			return (Object)DBController.updateClassOfStudent(msg.get(1),msg.get(2));
		case "searchStudentInCourseCurrentSemester":
			return (Object)DBController.searchStudentInCourseCurrentSemester(msg.get(1),msg.get(2));
		case "removeStudentFromCourse":
			return (Object)DBController.removeStudentFromCourse(msg.get(1),msg.get(2));
		case "removeStudentFromCourseRequest":
			return (Object)DBController.removeStudentFromCourseRequest(msg.get(1),msg.get(2),msg.get(3));
		case "studentsOfCourseClass":
			return (Object)DBController.studentsOfCourseClass(msg.get(1));
		case "updateSclassDetails":
			return (Object)DBController.updateSclassDetails(msg.get(1),msg.get(2),msg.get(3));
		case "updateTeacher":
			return (Object)DBController.updateTeacher(msg.get(1),msg.get(2));
		case "groupsOfCourse":
			return (Object)DBController.groupsOfCourse(msg.get(1));
		case "courseByStudent":
			return (Object)DBController.courseByStudent(msg.get(1),msg.get(2),msg.get(3));
		case "getChildren":
			return (Object)DBController.getChildren(msg.get(1));
		case "getTeachingUnitCourse":
			return (Object)DBController.getTeachingUnitCourse(msg.get(1));
		case "getClassCourses":
			return (Object)DBController.getClassCourses(msg.get(1),msg.get(2),msg.get(3));
		case "getClassTeacherInCourse":
			return (Object)DBController.getClassTeacherInCourse(msg.get(1),msg.get(2),msg.get(3),msg.get(4));
		case "getTeacherCourses":
			return (Object)DBController.getTeacherCourses(msg.get(1),msg.get(2),msg.get(3));
		case "getSubmissions":
			return (Object)DBController.getSubmissions(msg.get(1));
		case "createAssignment":
			return (Object)DBController.createAssignment(msg.get(1),msg.get(2),msg.get(3),msg.get(4),msg.get(5));
		case "editAssignment":
			return (Object)DBController.editAssignment(msg.get(1),msg.get(2),msg.get(3),msg.get(4),msg.get(5),msg.get(6));
		case "editStudentAssignment":
			return (Object)DBController.editStudentAssignment(msg.get(1),msg.get(2),msg.get(3),msg.get(4),msg.get(5),msg.get(6),msg.get(7));
		case "updateTinReport":
			return (Object)DBController.updateTinReport(msg.get(1),msg.get(2));
		case "getAmountAndTinClassCourse":
			return (Object)DBController.getAmountAndTinClassCourse(msg.get(1));
		case "getStudentClass":
			return (Object)DBController.getStudentClass(msg.get(1));
		case "getCourseNumber":
			return (Object)DBController.getCourseNumber(msg.get(1));
		case "getCourseName":
			return (Object)DBController.getCourseName(msg.get(1));	
		case "getCurrentSemesterDB":
			return (Object)DBController.getCurrentSemesterDB();		
		case "updateAmountClassCourse":
			return (Object)DBController.updateAmountClassCourse(msg.get(1),msg.get(2));
		case "addStoReport":
			return (Object)DBController.addStoReport(msg.get(1),msg.get(2), msg.get(3), msg.get(4), msg.get(5), msg.get(6), msg.get(7), msg.get(8));	
			
		
		default:
			return "null";
		}
	}
}
