package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import controller.LoginController;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
		private static final String ADMINLOGIN_JSP = "adminlogin.jsp";
		private static final String CURRENT_USER = "currentUser";
		private static final long serialVersionUID = 1L;
		private static final String INDEX_JSP = "index.jsp";

		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public AdminLoginServlet() {
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
						if(request.getHeader("referer").contains("AdminLoginServlet") || request.getHeader("referer").contains(ADMINLOGIN_JSP))
							{
							// get data for the logger
							String username = request.getParameter("username");
							String password = request.getParameter("password");
				
				
							// Populate the UserBean
				
							user.setUsername(username);
							user.setPassword(password);
						
					
							// prova a fare il login
							try {
								if (lc.validate("admin", user)) {



									// set user come attributo di sessione
									request.getSession().setAttribute(CURRENT_USER, user);

									disp = request.getRequestDispatcher("reports.jsp");

								} else {

									request.setAttribute("currentMessage", "Wrong username or password, please retry.");

									disp = request.getRequestDispatcher(ADMINLOGIN_JSP);
								}
							} catch (ClassNotFoundException e) {
								Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
								disp = request.getRequestDispatcher(INDEX_JSP);
							} catch (SQLException e) {
								Logger.getGlobal().log(Level.WARNING, "SQLException", e);
								disp = request.getRequestDispatcher(INDEX_JSP);
							}
				
							}
						else {
							disp = request.getRequestDispatcher(ADMINLOGIN_JSP);
						}
						
				}
				
				
				else {
					try {
						if(lc.validate("admin", (UserBean) request.getSession().getAttribute(CURRENT_USER)))
							// forward to the correct page
								disp = request.getRequestDispatcher("reports.jsp");
							
						else
							
								disp = request.getRequestDispatcher(ADMINLOGIN_JSP);
					} catch (ClassNotFoundException e) {
						Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
						disp = request.getRequestDispatcher(INDEX_JSP);
					} catch (SQLException e) {
						Logger.getGlobal().log(Level.WARNING, "SQLException", e);
						disp = request.getRequestDispatcher(INDEX_JSP);
					}
				}
			
				disp.forward(request, response);
			}
		
}
