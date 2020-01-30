package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/CheckCurrServlet")
public class CheckCurrServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public CheckCurrServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher disp;
		if(request.getSession().getAttribute("currentUser") != null) {
			if(request.getParameter("page").equals("Orders"))
				disp = request.getRequestDispatcher("orders.jsp");
			else
				if(request.getParameter("page").equals("newIns"))
					disp = request.getRequestDispatcher("newInsertion.jsp");
				else
					disp = request.getRequestDispatcher("messages.jsp");
		}
		else {
			disp = request.getRequestDispatcher("login.jsp");
			
		}
			disp.forward(request, response);
	}
}
