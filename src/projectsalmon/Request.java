package projectsalmon;

public class Request {
	private int requestID;
	private Student student;
	private StudentsClassInCourse classCourse;
	private String description;
	
	public Request(int requestID, Student student, StudentsClassInCourse classCourse, String description) {
		this.requestID = requestID;
		this.student = student;
		this.classCourse = classCourse;
		this.description = description;
	}
	
	public void setrequestID(int requestID){this.requestID=requestID;}
	public void setstudent(Student student){this.student=student;}
	public void setclassCourse(StudentsClassInCourse classCourse){this.classCourse=classCourse;}
	public void setdescription(String description){this.description=description;}
	
	public int getrequestID(){return requestID;}
	public Student getstudent(){return student;}
	public StudentsClassInCourse getclassCourse(){return classCourse;}
	public String getdescription(){return description;}
	

}
