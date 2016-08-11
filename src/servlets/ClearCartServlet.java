package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBHelper;

/**
 * Servlet implementation class ClearCartServlet
 */
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	

		int userID = DBHelper.getAccountID(user);
		
		boolean result = DBHelper.clearCart(userID);
			
		if(result) {
			//Return to main products page
   	   		RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
   	   		rs.forward(request, response);
		} else {
			//send error
        	System.out.println("Something wrong!");
           	request.setAttribute("errorMessage", "What.");
   	   		RequestDispatcher rs = request.getRequestDispatcher("cart.jsp");
   	   		rs.forward(request, response);
		}
	}

}
