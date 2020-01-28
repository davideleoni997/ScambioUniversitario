package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import controller.ReportController;



@WebServlet("/NewReportServlet")
public class NewReportServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public NewReportServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReportController rc = new ReportController();
		RequestDispatcher disp;
		UserBean ub = (UserBean)request.getSession().getAttribute("currentUser");
		if(request.getSession().getAttribute("currentUser") != null) {
			rc.newReport(Integer.parseInt(request.getParameter("insertion")), request.getParameter("desc"), ub.getId());
			disp = request.getRequestDispatcher("index.jsp");
		}
		else {
			disp = request.getRequestDispatcher("login.jsp");
			
		}
			disp.forward(request, response);
	}
}
