package servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RegistrationController;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public RegisterServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RegistrationController rc = RegistrationController.getInstance();
		
		if(request.getParameter("type").equals("student"))
			rc.registraUtente(request.getParameter("name"), request.getParameter("surname"), request.getParameter("username"), request.getParameter("newpsw"), false, request.getParameter("enroll"));
		else {
			File f;
			
			if(!request.getParameter("logo").isEmpty())
				f = new File(request.getParameter("logo"));
			else
				f = null;
			
			rc.registraSocieta(request.getParameter("name"), request.getParameter("username"), request.getParameter("newpsw"), true, f);
		}
		RequestDispatcher disp;
		
		disp = request.getRequestDispatcher("index.jsp");
		
		disp.forward(request, response);
	}
}