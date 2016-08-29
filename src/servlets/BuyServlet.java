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
import models.LogGenerator;
import models.PurchaseLog;
import models.SaleLog;
import security.CreditCard;

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
		String price = request.getParameter("price");
		
		float fPrice = 0;
		
		try {
			fPrice = Float.parseFloat(price);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	

		if(ccnumber.length() == 16 && CreditCard.CheckCreditCardValidity(ccnumber)) {
			if(CreditCard.CheckCreditCardLimit(ccnumber, fPrice)) {
				try {
					int prodID = Integer.parseInt(productID);
					
					SaleLog log = LogGenerator.generateSaleLog("", prodID, DBHelper.getAccountID(user), fPrice);
					PurchaseLog pLog = LogGenerator.generatePurchaseLog(user, prodID, DBHelper.getAccountID(user), fPrice);
					
					DBHelper.increaseSales(prodID, 1);
					DBHelper.createSaleLog(log);
					DBHelper.createPurchaseLog(pLog);
			        
	                RequestDispatcher rs = request.getRequestDispatcher("buyproduct.jsp?productid=" + productID);
	                request.setAttribute("errorMessage", "Transaction Successful!");
	                rs.forward(request, response);
				} catch (NumberFormatException ex) {
		           	request.setAttribute("errorMessage", "Something went horribly wrong.");
				}
			} else {
	        	System.out.println("Product price is more than your credit card limit!");
	           	request.setAttribute("errorMessage", "Product price is more than your credit card limit!");
                RequestDispatcher rs = request.getRequestDispatcher("buyproduct.jsp?productid=" + productID);
	   	   		rs.forward(request, response);
			}
        } else {
        	System.out.println("CCNumber invalid!");
           	request.setAttribute("errorMessage", "Invalid Credit Card Number!");
            RequestDispatcher rs = request.getRequestDispatcher("buyproduct.jsp?productid=" + productID);
   	   		rs.forward(request, response);
        }
	}

}
