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


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Please generate a POST request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		RequestDispatcher disp;
		
		LoginController lc = LoginController.getInstance();
		
			
			
			
			if(request.getSession().getAttribute("currentUser") == null) {
					UserBean user = new UserBean();
					if(request.getHeader("referer").contains("LoginServlet") || request.getHeader("referer").contains("login.jsp"))
						{
						// get data for the logger
						String username = request.getParameter("username");
						String password = request.getParameter("password");
			
			
						// Populate the UserBean
			
						user.setUsername(username);
						user.setPassword(password);
					
				
						// prova a fare il login
						if (lc.validate("user", user)) {
	
				
	
							// set user come attributo di sessione
							request.getSession().setAttribute("currentUser", user);
	
							disp = request.getRequestDispatcher("ProfileServlet");
	
						} else {
	
							request.setAttribute("currentMessage", "Wrong username or password, please retry.");
				
							disp = request.getRequestDispatcher("login.jsp");
						}
			
						}
					else {
						disp = request.getRequestDispatcher("login.jsp");
					}
					
			}
			
			
			else {
				if(lc.validate("user", (UserBean) request.getSession().getAttribute("currentUser")))
					// forward to the correct page
						disp = request.getRequestDispatcher("ProfileServlet");
					
				else
					
						disp = request.getRequestDispatcher("login.jsp");
			}
		
			disp.forward(request, response);
		}
	

}
