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
import models.Account;

/**
 * Servlet implementation class IncreaseSaleServlet
 */
@WebServlet("/Buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
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
		String ccnumber = request.getParameter("ccnumber");
		String cvc = request.getParameter("cvc");
		String expdate = request.getParameter("expdate");
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	

		if(ccnumber.length() == 16) {
			try {
				int prodID = Integer.parseInt(productID);
				
				DBHelper.increaseSales(prodID, 1);

                RequestDispatcher rs = request.getRequestDispatcher("buyproduct.jsp?productid=" + productID);
                request.setAttribute("errorMessage", "Transaction Successful!");
                rs.forward(request, response);
			} catch (NumberFormatException ex) {
	           	request.setAttribute("errorMessage", "Something went horribly wrong.");
			}
        } else {
        	System.out.println("CCNumber too Long!");
           	request.setAttribute("errorMessage", "Invalid Credit Card Number!");
   	   		RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
   	   		rs.forward(request, response);
        }
	}

}
