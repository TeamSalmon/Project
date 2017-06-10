package projectsalmon;

public class StudentInCourse
{
	private Student student;
	private Course course;
	
	public StudentInCourse(Student student, Course course)
	{
		this.student = student;
		this.course = course;
	}
	
	public Student getStudent(){return student;}
	public Course getCourse(){return course;}
}
