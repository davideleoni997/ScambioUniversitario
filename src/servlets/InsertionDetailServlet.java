package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.OrderController;

@WebServlet("/InsertionDetailServlet")
public class InsertionDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public InsertionDetailServlet() {
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
			OrderController.newOrder(Integer.parseInt(request.getParameter("buyer")), Integer.parseInt(request.getParameter("seller")), request.getParameter("object"), Integer.parseInt(request.getParameter("insertion")), Integer.parseInt(request.getParameter("price")));
			disp = request.getRequestDispatcher("insertionDetail.jsp?Id="+ request.getParameter("buyer"));
		}
		else {
			disp = request.getRequestDispatcher("login.jsp");
			
		}
			disp.forward(request, response);
	}
}