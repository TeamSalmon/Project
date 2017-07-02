package projectsalmon;

import java.util.ArrayList;

public class LoginUser extends User{
	public static LoginUser loginUser=new LoginUser("null","null","null", "null",0,-1,-1,0);
	private Integer loggedStatus ;
	private Integer isBlock;
	
	public static final Integer StudentPER=1;
	public static final Integer ParentPER=2;
	public static final Integer TeacherPER=4;
	public static final Integer SchoolManagerPER=8;
	public static final Integer SystemAdminPER=16;
	public static final Integer SecretaryPER=32;
	public static final Integer logOut=0;
	public static final Integer Loged=1;
	public static final Integer Locked=2;
	

	
	public LoginUser(String id,String first, String last, String password,Integer loginLockCounter,Integer permission,Integer loggedStatus,Integer isBlock){
		super(id,first,last,password,loginLockCounter,permission);
		this.setLoggedStatus(loggedStatus);
		this.setIsBlock(isBlock);
	}

	public LoginUser(ArrayList<String> newLoginUser){
		super(newLoginUser.get(0),newLoginUser.get(1),newLoginUser.get(2)
				,newLoginUser.get(3),Integer.getInteger(newLoginUser.get(4)),Integer.getInteger(newLoginUser.get(5)));
		
	}
	public LoginUser(String id,String first, String last, String password,String loginLockCounter,String permission,String loggedStatus){
		super(id,first,last,password,Integer.getInteger(loginLockCounter),Integer.getInteger(permission));
		
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
		this.setLoggedStatus(newUser.getLoggedStatus());
		this.setIsBlock(newUser.getIsBlock());
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
		this.isBlock = isBlock;
	}
	

}/**end of LoginUser*/
