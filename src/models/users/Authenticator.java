package models.users;

import pojos.User;
import database.UserDAO;

public class Authenticator {
	private String username, password;

	public Authenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Authenticator(User u) {
		this(u.getName(), u.getPassword());
	}

	public User verify()
	{
		User u = UserDAO.select(username, password);
		return u;
	}

}
