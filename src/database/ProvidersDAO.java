package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pojos.Item;
import pojos.User;
import pojos.User.UserType;

public class ProvidersDAO {

	private static void validateProvider(User p)
	{
		if(p==null || p.getUserType()!= UserType.PROVIDER)
			throw new RuntimeException("Specified user is not a provider");
	}
	
	public static boolean addItem(User p, Item i)
	{
		validateProvider(p);
		return addItem(p.getId(), i.getId());
	}
	
	public static boolean addItem(int provider_id, int item_id)
	{
		if(checkItem(provider_id, item_id))
			return false;
		
		Connection conn = DataBase.getConnection();
		if(conn == null)
			throw new RuntimeException("Connection is null");
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO providers_stock VALUES(?, ?)");
			ps.setInt(1, provider_id);
			ps.setInt(2, item_id);
			
			if(ps.executeUpdate()==1)
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}			
	}
	
	private static boolean checkItem(int provider_id, int item_id)
	{
		Connection conn = DataBase.getConnection();
		if(conn == null)
			throw new RuntimeException("Connection is null");
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM providers_stock WHERE provider_id=? AND item_id=?");
			ps.setInt(1, provider_id);
			ps.setInt(2, item_id);
			
			if(ps.executeQuery().next())
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	public static boolean removeItem(User p, Item i)
	{
		validateProvider(p);
		return removeItem(p.getId(), i.getId());
	}
	public static boolean removeItem(int provider_id, int item_id)
	{
		Connection conn = DataBase.getConnection();
		if(conn == null)
			throw new RuntimeException("Connection is null");
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM providers_stock WHERE provider_id=? AND item_id=?");
			ps.setInt(1, provider_id);
			ps.setInt(2, item_id);
			
			if(ps.executeUpdate()==1)
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	

}
