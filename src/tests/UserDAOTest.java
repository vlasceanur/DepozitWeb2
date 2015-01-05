package tests;

import org.junit.Assert;
import org.junit.Test;

import pojos.User;
import database.UserDAO;

public class UserDAOTest {
	
	@Test
	public void selectTest()
	{
		User u = UserDAO.select("a", "a");		
		Assert.assertNotNull(u);
	}

}
