package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojos.Item;

public class ItemsDAO {

	public static boolean addItem(Item i)
	{
		return addItem(i.getName(), i.getVolume(), i.getPrice());
	}
	
	public static boolean addItem(String itemName, float itemVolume, float itemPrice)
	{
		if(itemVolume < 0.0f || itemPrice < 0.0f) return false;
		
		Connection con = DataBase.getConnection();
		
		if(con == null)
			throw new RuntimeException("Connection is null");
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO items(item_name, item_volume, item_price) VALUES(?,?,?)");
			ps.setString(1, itemName);
			ps.setFloat(2, itemVolume);
			ps.setFloat(3, itemPrice);
			if(ps.executeUpdate() <=0) return false;
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static boolean removeItem(Item i)
	{
		if(i.getId() == -1)
			return removeItem(i.getName(), i.getVolume(), i.getPrice());
		else
			return removeItem(i.getId());
	}
	
	public static boolean removeItem(String itemName, float itemVolume, float itemPrice)
	{
		
		if(itemVolume < 0.0f || itemPrice < 0.0f) return false;
		
		Connection con = DataBase.getConnection();
		
		if(con == null)
			throw new RuntimeException("Connection is null");
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM items WHERE item_name=?" +
										" AND CAST(item_volume as DECIMAL(10,5))=? " +
										"AND CAST(item_price as  DECIMAL(10,5))=?");
			ps.setString(1, itemName);
			ps.setFloat(2, itemVolume);
			ps.setFloat(3, itemPrice);
			if(ps.executeUpdate() <=0) return false;
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean removeItem(int itemId)
	{
		
		Connection con = DataBase.getConnection();
		
		if(con == null)
			throw new RuntimeException("Connection is null");
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM items WHERE item_id=?");
			ps.setInt(1, itemId);
			if(ps.executeUpdate() <=0) return false;
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;			
		}
	}
	
	public static Item getItem(int id)
	{

		Connection con = DataBase.getConnection();
		
		if(con == null)
			throw new RuntimeException("Connection is null");
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("SELECT * FROM items WHERE item_id=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Item i = new Item();
				i.setId(id);
				i.setName(rs.getString("item_name"));
				i.setPrice(rs.getFloat("item_price"));
				i.setVolume(rs.getFloat("item_volume"));
				return i;
			}
			else return null;
		} catch (SQLException e) {
			System.out.println(e);
			return null;		
		}
		
	}		
}
