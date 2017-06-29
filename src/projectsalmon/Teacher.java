
package projectsalmon;

import java.util.ArrayList;

public class Teacher extends User{

	private String degree;
	private float weeklyHours;
	private float maximalWeeklyHours;
	private ArrayList<Class> schedule;
	private ArrayList<Course> teachable;
	private ArrayList<Integer> grades;

	public Teacher(int max_hours,String id,String first, String last, String password)
	{
		super(id,first,last,password);
		maximalWeeklyHours = max_hours;
		weeklyHours = 0;
		schedule = new ArrayList<Class>();
		teachable = new ArrayList<Course>();
	}
	
	public Teacher(float hours, String id, String first, String last, String password)
	{
		super(id,first,last,password);
		weeklyHours = 0;
	}
	
	
	public void addTeachable(Course course)
	{
		teachable.add(course);
	}
	public boolean removeTeachable(Course course)
	{
		int i = teachable.indexOf(course);
		
		if(i == -1)
			return false;
		
		teachable.remove(i);
		return true;
	}
	public boolean addClass(Class Aclass)
	{
		schedule.add(Aclass);
		return true;
	}
	public String getDegree()
	{
		return degree;
	}
	public void setDegree(String degree)
	{
		this.degree = degree;
	}
	public float getWeekly_hours()
	{
		return weeklyHours;
	}
	public void setWeekly_hours(float weekly_hours)
	{
		this.weeklyHours = weekly_hours;
	}
	public float getMax_maximal_weekly_hours()
	{
		return maximalWeeklyHours;
	}
	public void setMax_maximal_weekly_hours(float max_hours_per_week)
	{
		this.maximalWeeklyHours = max_hours_per_week;
	}

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	} 
	
	
}