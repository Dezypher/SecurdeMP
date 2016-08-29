package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.LogGenerator;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String user = "";

		Cookie ck[] = request.getCookies();
		if(ck != null) {
			for(int i = 0; i < ck.length; i++) {
				if(ck[i].getName().equals("user")){
					user = ck[i].getValue();
				}
			}			
		}
		
        LogGenerator.generateLoginLog(user, 0);
        
		Cookie ckU = new Cookie("user", "");
		ckU.setMaxAge(0);
		Cookie ckT = new Cookie("usertype", "");
		ckT.setMaxAge(0);
		
		response.addCookie(ckU);
		response.addCookie(ckT);

        LogGenerator.generateLogoutLog(user, 0);
        
		System.out.println("LOGOUT");
		
		//RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
		//rs.forward(request, response);
		response.sendRedirect("main.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Cookie ck = new Cookie("user", "");
		ck.setMaxAge(0);
		
		response.addCookie(ck);
		
		System.out.println("LOGOUT");
		
		RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
		rs.forward(request, response);
	}

}
