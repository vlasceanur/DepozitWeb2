package models.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojos.Item;
import pojos.Warehouse;
import database.WarehouseDAO;

public class ItemBrowser {
	public static List<Item> getItems(Warehouse wh)
	{					
		if(wh == null) return null;
		return WarehouseDAO.getItems(wh.getId());
	}
	
	public static Map<Warehouse, List<Item>> getAllItemsFromWarehouses()
	{
		Map<Warehouse, List<Item>> ret = new HashMap<Warehouse, List<Item>>();
		for(Warehouse wh: WarehouseDAO.getAllWarehouses())
		{
			List<Item> l = WarehouseDAO.getItems(wh);
			if(l.isEmpty())
				continue;
			ret.put(wh, WarehouseDAO.getItems(wh));
		}		
		return ret;
	}
}
