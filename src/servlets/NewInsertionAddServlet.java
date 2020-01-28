package servlets;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import controller.InsertionController;

@WebServlet("/NewInsertionAddServlet")
public class NewInsertionAddServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public NewInsertionAddServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		InsertionController ic = new InsertionController();
		UserBean ub = (UserBean)request.getSession().getAttribute("currentUser");
		List<File> images = new LinkedList<>();
		if(!request.getParameter("img1").isEmpty())
			images.add(new File(request.getParameter("img1")));
		
		if(!request.getParameter("img2").isEmpty())
			images.add(new File(request.getParameter("img2")));
		
		if(!request.getParameter("img3").isEmpty())
			images.add(new File(request.getParameter("img3")));
		
		ic.newInsertion(request.getParameter("title"), request.getParameter("desc"), request.getParameter("price"), images, ub.getId(), request.getParameter("uni"), request.getParameter("city"), request.getParameter("subj"), request.getParameter("book").equalsIgnoreCase("yes"), request.getParameter("notes").equalsIgnoreCase("yes"));
		
		RequestDispatcher disp;
		
		disp = request.getRequestDispatcher("index.jsp");
			
		
		disp.forward(request, response);
	}
}
