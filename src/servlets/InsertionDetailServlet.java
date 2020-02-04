package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MessageController;
import controller.OrderController;

@WebServlet("/InsertionDetailServlet")
public class InsertionDetailServlet extends HttpServlet{
	private static final String INSERTION = "insertion";
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
		
			
			if(OrderController.newOrder(Integer.parseInt(request.getParameter("buyer")), Integer.parseInt(request.getParameter("seller")), request.getParameter("object"), Integer.parseInt(request.getParameter(INSERTION)), Integer.parseInt(request.getParameter("price")))) {
				MessageController mc = new MessageController();
				mc.newMessage(Integer.parseInt(request.getParameter("buyer")), Integer.parseInt(request.getParameter("seller")), "I have bought your item :"+ request.getParameter("object"));
				
				disp = request.getRequestDispatcher("insertionDetail.jsp?Id="+ request.getParameter(INSERTION) +"&err=");
			}
			else
		
				disp = request.getRequestDispatcher("insertionDetail.jsp?Id="+ request.getParameter(INSERTION) +"&err=L'oggetto e' gia' stato comprato");
				
			disp.forward(request, response);
	}
}