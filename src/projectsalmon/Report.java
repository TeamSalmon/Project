package projectsalmon;

public class Report {
	
	private String supplicant;
	private String type;
	private String description;
	
	
	
	
	public Report(String id, String type)
	{
		supplicant = id;
		this.type = type;
	}
	
	public String getSupplicant() {
		return supplicant;
	}
	public void setSupplicant(String supplicant) {
		this.supplicant = supplicant;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
