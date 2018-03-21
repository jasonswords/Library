package object;

public class Login {
	private int loginId;
	private String userName;
	private String password;
	private int privilege;
	private int userId;

	public Login() {
	}

	public Login(int loginId, String userName, String password, int privilege, int userId) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.privilege = privilege;
		this.userId = userId;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
