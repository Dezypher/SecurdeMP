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
 * Servlet implementation class BuyProduct
 */
@WebServlet("/BuyProduct")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
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
				
		String productID = request.getParameter("productid");
		String amount = request.getParameter("amount");
				
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	

		int userID = DBHelper.getAccountID(user);
		
		try {
			boolean result = DBHelper.addToCart(userID, Integer.parseInt(productID), Integer.parseInt(amount));
			
			if(result) {
				//Return to main products page
			} else {
				//send error
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			// Send error
        	System.out.println("Invalid amount!");
           	request.setAttribute("errorMessage", "Amount has to be a positive integer.");
   	   		RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
   	   		rs.forward(request, response);
		}
	}

}
