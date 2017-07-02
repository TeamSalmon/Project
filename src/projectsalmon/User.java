package projectsalmon;

public abstract class User {
	
	private String id;
	private Integer permission;
	private String first_name;
	private String last_name;
	private String password;
	private String email;
	private String phone;
	private String birthday;
	private boolean lockedFlag;//Galit don't use
	private Integer loginLockCounter;
//	lockedFlag = false;

	public User(String id,String first, String last, String password,Integer loginLockCounter,Integer permission)
	{/**
		 * @author Galit
		 * @param id
		 * @param first
		 * @param last
		 * @param password
		 * @param loginLockCounter
		 * Constructor for LoginUser
		 */
		this.id = id;
		this.first_name = first;
		this.last_name = last;
		this.password = password;
		this.loginLockCounter = loginLockCounter;
		this.permission=permission;
	/*	this.setId(id);
		this.setFirst_name(first);
		this.setLast_name(last);
		this.setPassword(password);
		this.setloginLockCounter(Integer.getInteger(loginLockCounter));
		this.setPrivilige(Integer.getInteger(permission));*/
	}

/*	public User(String id,String first, String last, String password)
	{
		*
		 * @author Galit
		 * @param id
		 * @param first
		 * @param last
		 * @param password
		 * @param lockedFlag
		 * @param loginLockCounter =0
		 * Constructor for LoginUser
		 *
		this.setId(id);
		this.setFirst_name(first);
		this.setLast_name(last);
		this.setPassword(password);
		this.setloginLockCounter(0);
	}
*/	

	public User(String id,String first, String last, String password)
	{//Galit don't use
		this.id = id;
		this.first_name = first;
		this.last_name = last;
		this.password = password;
		lockedFlag = false;
		loginLockCounter = 0;
	};
	
	public User(String id, int permission, String first, String last, String password, String email)
	{
		this.id = id;
		this.permission = permission;
		this.first_name = first;
		this.last_name = last;
		this.password = password;
		this.email = email;
		lockedFlag = false;
		loginLockCounter = 0;		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPermission() {
		return permission;
	}
	public void setPrivilige(int privilige) {
		this.permission = privilige;
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
	public boolean islockedFlag() {//Galit don't use
		return lockedFlag;
	}
	public void setlockedFlag(boolean lockedFlag) {//Galit don't use
		this.lockedFlag = lockedFlag;
	}
	public Integer getloginLockCounter() {
		return loginLockCounter;
	}
	public void setloginLockCounter(Integer loginLockCounter) {
		this.loginLockCounter = new Integer(loginLockCounter);
	}
	
	
	
}
