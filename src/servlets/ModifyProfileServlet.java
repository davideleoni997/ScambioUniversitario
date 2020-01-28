package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import controller.LoginController;

@WebServlet("/ModifyProfileServlet")
public class ModifyProfileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public ModifyProfileServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		LoginController lc = LoginController.getInstance();
		UserBean ub = (UserBean)request.getSession().getAttribute("currentUser");
		ub.setUsername(request.getParameter("username"));
		ub.setPassword(request.getParameter("newpsw"));
		RequestDispatcher disp;
		if(request.getParameter("oldpsw").equals(request.getParameter("newpsw"))) {
			lc.updateInfo(ub);
		
		
		
		
		request.getSession().invalidate();
		
		disp = request.getRequestDispatcher("index.jsp");
			
		
		
		}
		else
			disp = request.getRequestDispatcher("modifyProfile.jsp?Error=Yes");
			
		disp.forward(request, response);
	}
}
