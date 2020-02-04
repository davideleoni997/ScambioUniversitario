package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.OrderController;
import external.MockupPayment;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public PaymentServlet() {
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
			if(MockupPayment.payment()) {
				
				OrderController oc = new OrderController();
				oc.payOrder(Integer.parseInt(request.getParameter("Id")));
				
				}
			disp = request.getRequestDispatcher("orderDetail.jsp?Id="+ request.getParameter("Id"));
		}
		else {
			disp = request.getRequestDispatcher("login.jsp");
			
		}
			disp.forward(request, response);
	}
}