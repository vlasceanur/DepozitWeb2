package tests;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.items.ItemBrowser;

import org.junit.Test;

import pojos.Item;
import pojos.Warehouse;

public class WarehouseDAOTest {
	
	@Test
	public void listAllWarehousesAndItems()
	{
		Map<Warehouse, List<Item>> tmp = ItemBrowser.getAllItemsFromWarehouses();
		for(Entry<Warehouse, List<Item>> e : tmp.entrySet())
		{
			System.out.println(e.getKey());
			System.out.println("---->");
			for(Item i : e.getValue())
				System.out.println(i);
			System.out.println("########");
		}
	}

}
