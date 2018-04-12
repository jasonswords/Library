package object;

public class User {

	private int userId;
	private String firstName;
	private String surName;
	private String address;
	private String phone;
	private String username;
	private String password;
	private int privilege;

	public User() {
	}

	public User(String firstName, String surName, String address, String phone, String username, String password,
			int privilege) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.privilege = privilege;
	}

	public User(int userId, String firstName, String surName, String address, String phone, String username,
			String password, int privilege) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.privilege = privilege;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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