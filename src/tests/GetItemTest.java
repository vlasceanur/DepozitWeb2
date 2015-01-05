package tests;

import org.junit.Test;

import pojos.Item;
import database.ItemsDAO;
import database.WarehouseDAO;

public class GetItemTest {
	
	@Test
	public void getItem()
	{
		for(Item i: WarehouseDAO.getItems(55))
			System.out.println(i.toString() + "\n\n\n");
	}

}
