package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBHelper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("uname");
        String pass = request.getParameter("pass");
        
        Cookie ck = new Cookie("user", user);
        ck.setMaxAge(100000);
        
        if(DBHelper.checkUser(user, pass))
        {
            response.addCookie(ck);
            RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
            rs.forward(request, response);
        }
        else
        {
        	out.println("Username or Password incorrect");
           	request.setAttribute("errorMessage", "Incorrect Username or Password.");
   	   		response.setHeader("Location", "pages/login.jsp"); 
   	   		RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
   	   		rs.forward(request, response);
        }
    }  

}
