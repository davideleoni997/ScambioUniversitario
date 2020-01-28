package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MessageDetailServlet")
public class MessageDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public MessageDetailServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("Id");
		RequestDispatcher disp;
		if(request.getSession().getAttribute("currentUser") != null) {
			disp = request.getRequestDispatcher("messageDetail.jsp?Id="+id);
		}
		else {
			disp = request.getRequestDispatcher("login.jsp");
			
		}
			disp.forward(request, response);
	}
}
