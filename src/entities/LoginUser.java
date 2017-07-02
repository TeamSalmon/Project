package entities;

import java.io.Serializable;

public class LoginUser extends User implements Serializable{
	private Integer loggedStatus ;
	private Integer isBlock;
	
	
	public static final Integer StudentPER=1;
	public static final Integer ParentPER=2;
	public static final Integer TeacherPER=4;
	public static final Integer SchoolManagerPER=8;
	public static final Integer SystemAdminPER=16;
	public static final Integer SecretaryPER=32;
	public static final Integer notLoged=0;
	public static final Integer Loged=1;
	public static final Integer Locked=2;
	
	

	
	public LoginUser(String id,String first, String last, String password,Integer loginLockCounter,Integer permission,Integer loggedStatus,Integer isBlock){
		super(id,first,last,password,loginLockCounter,permission);
		this.setLoggedStatus(loggedStatus);
		this.setIsBlock(isBlock);
	}

	public void copy(LoginUser newUser){
		/**
		 * @author Galit
		 * @param id
		 * @param first
		 * @param last
		 * @param password
		 * @param lockedFlag
		 * @param loginLockCounter
		 * copy for LoginUser
		 */
		this.setId(newUser.getId());
		this.setFirst_name(newUser.getFirst_name());
		this.setLast_name(newUser.getLast_name());
		this.setPassword(newUser.getPassword());
		this.setLoggedStatus(newUser.getLoggedStatus());
		this.setloginLockCounter(newUser.getloginLockCounter());
		this.setPrivilige(newUser.getPermission());
	}

	public Integer getLoggedStatus() {
		return this.loggedStatus;
	}
	public void setLoggedStatus(Integer newloggedStatus) {
		this.loggedStatus =new Integer(newloggedStatus);
	}

	public Integer getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(Integer isBlock) {
		this.isBlock = new Integer(isBlock);
	}
	

}/**end of LoginUser*/
