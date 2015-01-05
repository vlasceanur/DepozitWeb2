package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.User;
import views.Pages;
import models.users.Authenticator;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("usr");
		String password = request.getParameter("pwd");

		//test only
		user =  request.getParameter("usr");
		password = request.getParameter("pwd");
		
		Authenticator auth = new Authenticator(user, password);
		User u = auth.verify();
		
		if(u==null)
			System.out.println("Login failed");
		
		if (u != null) {
			request.getSession().setAttribute("user", u);
			
			switch(u.getUserType())
			{
			case ADMIN:	
				request.getSession().setAttribute("links", Pages.HOME_LINKS_ADMIN);
				System.out.println("Admin login");
				request.getRequestDispatcher(Pages.HOME).forward(request, response);
				break;
			case ACCOUNTANT:
				request.getSession().setAttribute("links", Pages.HOME_LINKS_ACCOUNTANT);
				System.out.println("accountant login");
				request.getRequestDispatcher(Pages.HOME).forward(request, response);
				break;
			case MANAGER:
				request.getSession().setAttribute("links", Pages.HOME_LINKS_MANAGER);
				System.out.println("manager login");
				request.getRequestDispatcher(Pages.HOME).forward(request, response);
				break;
			case PROVIDER:
				request.getSession().setAttribute("links", Pages.HOME_LINKS_PROVIDER);
				System.out.println("provider login");
				request.getRequestDispatcher(Pages.HOME).forward(request, response);
				break;
			default:
				request.getSession().setAttribute("links", Pages.HOME_LINKS_REGULAR);
				System.out.println("regular login");
				request.getRequestDispatcher(Pages.HOME).forward(request, response);
				break;			
			}			
			return;
		}
		response.sendRedirect(views.Pages.LOGIN);
		return;

	}

}
