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
import models.LogGenerator;
import security.InputChecker;

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
        
        if(InputChecker.checkInput(user) && InputChecker.checkInput(pass) && DBHelper.checkUser(user, pass))
        {	
            
            Cookie ck = new Cookie("user", user);
            ck.setMaxAge(100000);
            
            String userType = "" + DBHelper.getAccountType(user);
            
            Cookie ckT = new Cookie("usertype", userType);
            ck.setMaxAge(100000);
            
            LogGenerator.generateLoginLog(user, 0);
        	
            response.addCookie(ck);
            response.addCookie(ckT);
            //RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
            //rs.forward(request, response);
            response.sendRedirect("main.jsp");
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
