package projectsalmon;

public class Report
{
	private String reportId;
	private String type;
	private String description;
	
	public Report(String id, String type)
	{
		reportId = id;
		this.type = type;
	}
	
	public String getreportId(){return reportId;}
	public void setreportId(String reportId){this.reportId = reportId;}
	public String getType(){return type;}
	public void setType(String type){this.type = type;}
	public String getDescription(){return description;}
	public void setDescription(String description){this.description = description;}
}
