package views;

import java.util.HashMap;
import java.util.Map;

public class Pages {
	public final static String HOME = "home.jsp";	
	public static final String LOGIN = "login.html";
	
	/**
	 * KEY = URL
	 * VALUE = LINK NAME
	 */
	public final static Map<String, String> HOME_LINKS_REGULAR = new HashMap<String, String>();
	public final static Map<String, String> HOME_LINKS_ADMIN = new HashMap<String, String>();
	public final static Map<String, String> HOME_LINKS_ACCOUNTANT = new HashMap<String, String>();
	public final static Map<String, String> HOME_LINKS_PROVIDER = new HashMap<String, String>();
	public final static Map<String, String> HOME_LINKS_MANAGER = new HashMap<String, String>();
	
	
	static
	{
		HOME_LINKS_REGULAR.put("LogoutController", "Log out");
		HOME_LINKS_REGULAR.put("BrowseItemsController", "Browse items");
		HOME_LINKS_REGULAR.put("ViewBasketController", "View basket");
		
		HOME_LINKS_ADMIN.put("LogoutController", "Log out");
		HOME_LINKS_ADMIN.put("EditUsersController", "Edit users");
		HOME_LINKS_ADMIN.put("EditWarehousesController", "Edit warehouses");
		
		
		
		HOME_LINKS_ACCOUNTANT.put("LogoutController", "Log out");
		HOME_LINKS_ACCOUNTANT.put("ViewBillsController", "Bills");
		
		
		HOME_LINKS_PROVIDER.put("LogoutController", "Log out");
		HOME_LINKS_PROVIDER.put("EditItemsController", "Edit available items"); 
		
		
		HOME_LINKS_MANAGER.put("LogoutController", "Log out");
		HOME_LINKS_MANAGER.put("OrderItemsController", "Order items");
		HOME_LINKS_MANAGER.put("EditItemsController", "Edit items price");
		
		
	}

}
