package pojos;

import java.sql.Timestamp;

public class User {

	public static enum UserType {
		REGULAR(5), ADMIN(1), ACCOUNTANT(3), PROVIDER(4), MANAGER(2);

		private final int type;

		public int getTypeId() {
			return type;
		}

		UserType(int i) {
			type = i;
		}
	}

	private int id;
	private String name;
	private String password;
	private Timestamp registerDate;
	private int creatorId;
	private UserType userType;

	public User() {
		this.userType = UserType.REGULAR;
	}

	public User(int userTypeId) {
		boolean good = false;
		for (UserType t : UserType.values()) {			
			if (t.getTypeId() == userTypeId) {
				userType = t;
				good = true;				
				break;
			}
		}
		if (!good)
			throw new RuntimeException("Invalid typeID -: " + userTypeId);
	}

	public User(UserType userType) {
		this.userType = userType;
	}

	public UserType getUserType() {
		return userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

}
