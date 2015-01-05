package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import pojos.User;

public class UserDAO {
	private static final int USER_TYPES_REGULAR_USER = 11;

	public static boolean typeExists(int type) {
		Connection con = DataBase.getConnection();

		if (con == null)
			throw new RuntimeException("Connection is null");

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT * FROM user_types WHERE user_type=?");
			ps.setInt(1, type);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean userExists(String userName) {
		Connection con = DataBase.getConnection();

		if (con == null)
			throw new RuntimeException("Connection is null");

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT user_id FROM users WHERE user_name=?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean createUser(String userName, String password) {
		return createUser(userName, password, null, USER_TYPES_REGULAR_USER);
	}

	public static boolean createUser(String userName, String password,
			Integer creator_id, int type) {
		if (userExists(userName))
			return false;
		if (!typeExists(type))
			return false;

		Connection con = DataBase.getConnection();

		if (con == null)
			throw new RuntimeException("Connection is null");

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO users(user_name, user_password, user_creator_id, user_type) "
					+ "VALUES(?,?,?,?)");
			ps.setString(1, userName);
			ps.setString(2, password);
			if (creator_id == null)
				ps.setNull(3, java.sql.Types.NULL);
			else
				ps.setInt(3, creator_id);
			ps.setInt(4, type);
			if (ps.executeUpdate() <= 0)
				return false;
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean removeUser(String userName) {
		if (!userExists(userName))
			return false;

		Connection con = DataBase.getConnection();

		if (con == null)
			throw new RuntimeException("Connection is null");

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM users WHERE user_name=?");
			ps.setString(1, userName);
			if (ps.executeUpdate() <= 0)
				return false;
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet getUser(int id) {
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM users WHERE user_id = ?");
			ps.setInt(1, id);

			return ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Map<Integer, String> getUsers() {
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");
		try

		{
			PreparedStatement ps = conn
					.prepareStatement("SELECT user_id, user_name FROM users");
			ResultSet rs = ps.executeQuery();

			Map<Integer, String> map = new TreeMap<Integer, String>();

			while (rs.next()) {
				map.put(rs.getInt(1), rs.getString(2));
			}
			return map;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Map<Integer, String> getTypes() {
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT user_type, user_type_name FROM user_types");
			ResultSet rs = ps.executeQuery();

			Map<Integer, String> map = new TreeMap<Integer, String>();

			while (rs.next()) {
				map.put(rs.getInt(1), rs.getString(2));
			}
			return map;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static User select(String username, String password) {
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM users WHERE user_name=? AND user_password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.first() == false)
				return null;

			User u = new User(rs.getInt("user_type"));
			u.setId(rs.getInt(1));
			u.setName(username);
			u.setPassword(password);
			u.setRegisterDate(rs.getTimestamp("user_register_date"));
			u.setCreatorId(rs.getInt("user_creator_id"));
			return u;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
