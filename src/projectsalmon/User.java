
package projectsalmon;

public abstract class User {

	private String id;
	private byte privilige;
	private String first_name;
	private String last_name;
	private String password;
	private String email;
	private String phone;
	private String birthday;
	private boolean blockedFlag;
	private byte loginBlockCounter;
	
	public User(String id,String first, String last, String password)
	{
		this.id = id;
		this.first_name = first;
		this.last_name = last;
		this.password = password;
		blockedFlag = false;
		loginBlockCounter = 0;
	};
	public User(String id, byte privilige, String first, String last, String password, String email)
	{
		this.id = id;
		this.privilige = privilige;
		this.first_name = first;
		this.last_name = last;
		this.password = password;
		this.email = email;
		blockedFlag = false;
		loginBlockCounter = 0;		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte getPrivilige() {
		return privilige;
	}
	public void setPrivilige(byte privilige) {
		this.privilige = privilige;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public boolean isBlockedFlag() {
		return blockedFlag;
	}
	public void setBlockedFlag(boolean blockedFlag) {
		this.blockedFlag = blockedFlag;
	}
	public byte getloginBlockCounter() {
		return loginBlockCounter;
	}
	public void setLoginBlockCounter(byte loginBlockCounter) {
		this.loginBlockCounter = loginBlockCounter;
	}
	
	
	
}
