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
import logic.BasicInformations;
import logic.Filters;

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
		
		BasicInformations basic = new BasicInformations();
		basic.setTitle(request.getParameter("title"));
		basic.setDesc(request.getParameter("desc"));
		basic.setPrice(Integer.parseInt(request.getParameter("price")));
		Filters filter = new Filters();
		filter.setUniversity(request.getParameter("uni"));
		filter.setCity(request.getParameter("city"));
		filter.setSubject(request.getParameter("subj"));
		filter.setBook(request.getParameter("book").equalsIgnoreCase("yes"));
		filter.setNotes(request.getParameter("notes").equalsIgnoreCase("yes"));
		
		
		ic.newInsertion(basic, images, ub.getId(), filter);
		
		RequestDispatcher disp;
		
		disp = request.getRequestDispatcher("index.jsp");
			
		
		disp.forward(request, response);
	}
}
