package object;

public class User {

	private int userId;
	private String firstName;
	private String surName;
	private String address1;
	private String address2;
	private String address3;
	private String phone;
	private Login login;

	public User() {
		super();
		login = new Login();
	}

	public User(int userId, String firstName, String surName, String address1, String address2, String address3,
			String phone) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surName = surName;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.phone = phone;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	///////////////////////
	// WRAPPER METHODS
	///////////////////////
	public int getLoginId() {
		return login.getLoginId();
	}

	public String getUserName() {
		return login.getUserName();
	}

	public String getPassword() {
		return login.getPassword();
	}

	public int getPrivilege() {
		return login.getPrivilege();
	}

}
