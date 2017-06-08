package projectsalmon;

public class Teacher {

	private String degree;
	private float weekly_hours;
	private float maximal_weekly_hours;
	
	public Teacher(int max_hours)
	{
		maximal_weekly_hours = max_hours;
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
	public float getMax_maximal_weekly_hours() {
		return maximal_weekly_hours;
	}
	public void setMax_maximal_weekly_hours(float max_hours_per_week) {
		this.maximal_weekly_hours = max_hours_per_week;
	} 
	
	
}
