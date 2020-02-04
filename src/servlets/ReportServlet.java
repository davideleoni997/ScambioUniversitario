package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ReportController;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public ReportServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReportController rc = ReportController.getInstance();
		String id = request.getParameter("Id");
		if(request.getParameter("op").equals("ban")) {
			rc.ban(Integer.parseInt(request.getParameter("InsId")), Integer.parseInt(id));
		}
		else {
			rc.removeReport(Integer.parseInt(id));
		}
		
		RequestDispatcher disp;
		
		disp = request.getRequestDispatcher("reports.jsp");
		disp.forward(request, response);
	}
}
