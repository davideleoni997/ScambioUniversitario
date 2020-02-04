package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.InsertionBean;

import controller.InsertionController;

import logic.BasicInformations;
import logic.Filters;

@WebServlet("/ModifyInsertionServlet")
public class ModifyInsertionServlet extends HttpServlet{
	private static final String PRICE = "price";
	private static final long serialVersionUID = 1L;

	
	public ModifyInsertionServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		InsertionController ic = InsertionController.getInstance();
		if(request.getParameter("op").equals("modify")) {
		InsertionBean ib = new InsertionBean();
		ib.setId(Integer.parseInt(request.getParameter("Id")));
		BasicInformations basic = new BasicInformations();
		Filters filter = new Filters();
		basic.setDesc(request.getParameter("desc"));
		basic.setTitle(request.getParameter("object"));
		if(request.getParameter(PRICE).matches("[0-9]+"))
			basic.setPrice(Integer.parseInt(request.getParameter(PRICE)));
		filter.setUniversity(request.getParameter("uni"));
		filter.setSubject(request.getParameter("subj"));
		filter.setCity(request.getParameter("city"));
		if(request.getParameter("book")!=null)
			filter.setBook(request.getParameter("book").equals("yes"));
		else
			filter.setBook(false);
		if(request.getParameter("notes")!=null)
			filter.setNotes(request.getParameter("notes").equals("yes"));
		else
			filter.setNotes(false);
		ib.setBasic(basic);
		ib.setFilter(filter);
		if(request.getParameter(PRICE).matches("[0-9]+")) {
			ic.modify(ib);
		}
		
		}
		else {
			//Delete
			ic.delete(Integer.parseInt(request.getParameter("Id")));
		}
		
		
		RequestDispatcher disp;
		
		disp = request.getRequestDispatcher("index.jsp");
			
		disp.forward(request, response);
	}
}
