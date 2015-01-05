package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.items.ItemBrowser;

/**
 * Servlet implementation class BrowseItemsControler
 */
@WebServlet("/BrowseItemsController")
public class BrowseItemsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1039755122263670635L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		req.setAttribute("items", ItemBrowser.getAllItemsFromWarehouses());
		req.getRequestDispatcher("BrowseItemsView.jsp").forward(req, resp);
	}

	
}
