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
	
	private static final String LOGIN_JSP = "login.jsp";
	private static final String CURRENT_USER = "currentUser";
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
			
			if(request.getSession().getAttribute(CURRENT_USER) == null) {
				UserBean user = new UserBean();
				if(request.getHeader("referer").contains("LoginServlet") || request.getHeader("referer").contains(LOGIN_JSP)){
					// get data for the logger
					String username = request.getParameter("username");
					String password = request.getParameter("password");			
	
					// Populate the UserBean			
					user.setUsername(username);
					user.setPassword(password);				
				
					// prova a fare il login
				
					if (lc.validate("user", user)) {
						// set user come attributo di sessione
						request.getSession().setAttribute(CURRENT_USER, user);

						disp = request.getRequestDispatcher("ProfileServlet");
					} else {
						request.setAttribute("currentMessage", "Wrong username or password, please retry.");

						disp = request.getRequestDispatcher(LOGIN_JSP);
						}
				}
				else {
					disp = request.getRequestDispatcher(LOGIN_JSP);
				}					
				}			
			
			else {
				
					if(lc.validate("user", (UserBean) request.getSession().getAttribute(CURRENT_USER)))
						// forward to the correct page
							disp = request.getRequestDispatcher("ProfileServlet");						
					else						
							disp = request.getRequestDispatcher(LOGIN_JSP);
			}		
			disp.forward(request, response);
		}
	

}
