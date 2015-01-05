package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Item;
import pojos.Warehouse;

public class WarehouseDAO {

	public static boolean create(Warehouse wh) {
		return create(wh.getName(), wh.getLocation(), wh.getCash(),
				wh.getVolume(), wh.getManager_id());
	}

	public static boolean create(String name, String location, double cash,
			float volume, int manager_id) {
		Connection con = DataBase.getConnection();

		if (con == null)
			throw new RuntimeException("Connection is null");

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO warehouses(warehouse_name, warehouse_location, warehouse_cash,"
					+ " warehouse_volume, warehouse_manager_id)"
					+ " VALUES(?, ?, ?, ?, ?);");
			ps.setString(1, name);
			ps.setString(2, location);
			ps.setDouble(3, cash);
			ps.setFloat(4, volume);
			ps.setInt(5, manager_id);

			if (ps.executeUpdate() == 1)
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean delete(Warehouse wh) {
		return delete(wh.getId());
	}

	public static boolean delete(int id) {

		Connection con = DataBase.getConnection();

		if (con == null)
			throw new RuntimeException("Connection is null");

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM warehouses WHERE =?");
			ps.setInt(1, id);
			if (ps.executeUpdate() <= 0)
				return false;
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean addItem(Warehouse wh, Item i, int quantity) {
		return addItem(wh.getId(), i.getId(), quantity);
	}

	public static boolean addItem(int warehouse_id, int item_id, int quantity) {
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");
		int oldQuantity = hasItem(warehouse_id, item_id);
		if (oldQuantity >= 0) {
			return updateStock(warehouse_id, item_id, oldQuantity + quantity);
		} else {
			return addToStock(warehouse_id, item_id, quantity);
		}
	}

	private static boolean addToStock(int warehouse_id, int item_id,
			int quantity) {
		if (quantity < 0)
			return false;

		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO stock_items(warehouse_id, item_id, item_quantity) VALUES(?, ?, ?)");
			ps.setInt(1, warehouse_id);
			ps.setInt(2, item_id);
			ps.setInt(3, quantity);

			if (ps.executeUpdate() == 1)
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean updateStock(int warehouse_id, int item_id,
			int new_quantity) {
		if (new_quantity < 0)
			return false;
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE stock_items SET item_quantity = ? WHERE warehouse_id=? AND item_id = ?");
			ps.setInt(1, new_quantity);
			ps.setInt(2, warehouse_id);
			ps.setInt(3, item_id);

			if (ps.executeUpdate() == 1)
				return true;
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static int hasItem(Warehouse wh, Item i)
	{
		return hasItem(wh.getId(), i.getId());
	}
	public static int hasItem(int warehouse_id, int item_id) {
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT item_quantity FROM stock_items WHERE warehouse_id = ? AND item_id=?");
			ps.setInt(1, warehouse_id);
			ps.setInt(2, item_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("item_quantity");
			}
			return -1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Item> getItems(Warehouse wh)
	{		
		return getItems(wh.getId());
	}
	public static List<Item> getItems(int warehouse_id)
	{
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			PreparedStatement ps = conn
					.prepareStatement("select i.item_id, i.item_name, i.item_price, i.item_volume "+
										"FROM stock_items s, items i " +
										"WHERE s.item_id = i.item_id AND s.item_quantity > 0 AND warehouse_id = ?");
			ps.setInt(1, warehouse_id);			
			ResultSet rs = ps.executeQuery();
			List<Item> ls = new ArrayList<Item>();
			
			while (rs.next()) {
				Item i = new Item(rs.getInt("item_id"), 
								rs.getString("item_name"), 
								rs.getFloat("item_volume"),
								rs.getFloat("item_price"));
				ls.add(i);
			}
			return ls;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Warehouse> getAllWarehouses()
	{
		Connection conn = DataBase.getConnection();
		if (conn == null)
			throw new RuntimeException("Connection is null");

		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM warehouses");
			List<Warehouse> ls = new ArrayList<Warehouse>();
			while (rs.next()) {
				Warehouse wh = new Warehouse(rs.getInt(1), 
											rs.getString(2), 
											rs.getString(3),
											rs.getFloat(4), 
											rs.getFloat(5), 
											rs.getInt(6));
				ls.add(wh);
			}
			return ls;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
