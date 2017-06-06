package projectsalmon;

public class Teacher {

	private String degree; 
	private float weekly_hours; 
	private float max_hours_per_week;
	
	public Teacher(int max_hours)
	{
		max_hours_per_week = max_hours;
		weekly_hours = 0;
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
	public float getMax_hours_per_week() {
		return max_hours_per_week;
	}
	public void setMax_hours_per_week(float max_hours_per_week) {
		this.max_hours_per_week = max_hours_per_week;
	} 
	
	
}
