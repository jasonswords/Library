package object;

public class Login {
	private int loginId;
	private String userName;
	private String password;
	private int privilege;
	
	public Login(int loginId, String userName, String password, int privilege) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.privilege = privilege;
	}

	public int getLoginId() {
		return loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
}
