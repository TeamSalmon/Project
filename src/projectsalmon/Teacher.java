package projectsalmon;

import java.util.Calendar;

public class Teacher extends User{

	private String degree;
	private float weekly_hours;
	private float maximal_weekly_hours;
	private Calendar schedule;
	
	public Teacher(int max_hours,String id,String first, String last, String password)
	{
		super(id,first,last,password);
		maximal_weekly_hours = max_hours;
		weekly_hours = 0;
		schedule = Calendar.getInstance();
	}
	
	
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public float getWeekly_hours() {
		return weekly_hours;
	}
	public void setWeekly_hours(float weekly_hours) {
		this.weekly_hours = weekly_hours;
	}
	public float getMax_maximal_weekly_hours() {
		return maximal_weekly_hours;
	}
	public void setMax_maximal_weekly_hours(float max_hours_per_week) {
		this.maximal_weekly_hours = max_hours_per_week;
	} 
	
	
}
