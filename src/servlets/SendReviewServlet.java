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
import security.HTMLTagChecker;

/**
 * Servlet implementation class SendReview
 */
@WebServlet("/SendReview")
public class SendReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendReviewServlet() {
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
		String review = request.getParameter("review");
		String rating = request.getParameter("rating");
		
		String cleanedReview = HTMLTagChecker.cleanStringTagsWithWhitelist(review);
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}
	
			try {
				 float fRating = Float.parseFloat(rating);
				 int iProductID = Integer.parseInt(productID);
				 
				 DBHelper.addReview(user, iProductID, cleanedReview, fRating);
				 
		   	   	RequestDispatcher rs = request.getRequestDispatcher("viewproduct.jsp?productid=" + productID);
		   	   	rs.forward(request, response);
				 
			} catch (Exception ex) {
				ex.printStackTrace();
		   	   	RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
		   	   	rs.forward(request, response);
			}
	}
}