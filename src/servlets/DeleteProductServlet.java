package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.Account;
import database.DBHelper;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
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

		String id = request.getParameter("productid");
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	
		
		
		int accountType = DBHelper.getAccountType(user);
			
		if(accountType == Account.TYPE_PRODUCTMANAGER ||
		   accountType == Account.TYPE_ADMINISTRATOR) {
			DBHelper.deleteProduct(Integer.parseInt(id));
		    RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
		    rs.forward(request, response);
        } else {
        	System.out.println("Account has no authorization to create a product!");
            request.setAttribute("errorMessage", "You have no authorization to create a product!");
       	   	RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
       	   	rs.forward(request, response);
        }
	}

}
