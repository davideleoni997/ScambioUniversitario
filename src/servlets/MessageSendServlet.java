package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import controller.MessageController;

@WebServlet("/MessageSendServlet")
public class MessageSendServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public MessageSendServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String newmsg = request.getParameter("newmsg");
		Integer to = Integer.parseInt(request.getParameter("to"));
		MessageController mc = MessageController.getInstance();
		UserBean ub = (UserBean) request.getSession().getAttribute("currentUser");
		
		RequestDispatcher disp;
		if(request.getSession().getAttribute("currentUser") != null) {
			mc.newMessage(ub.getId(), to, newmsg);
			disp = request.getRequestDispatcher("messageDetail.jsp?Id="+to);
		}
		else {
			disp = request.getRequestDispatcher("login.jsp");
			
		}
			disp.forward(request, response);
	}
}