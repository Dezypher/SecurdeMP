package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import database.DBHelper;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
		
		String name = request.getParameter("pname");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String imagePath = request.getParameter("imagepath");
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	
		
		if(name.length() > 0) {
			int accountType = DBHelper.getAccountType(user);
			
			if(accountType == Account.TYPE_PRODUCTMANAGER ||
			   accountType == Account.TYPE_ADMINISTRATOR) {
				float fPrice = 0.0f;
				int iType = 0;
				
				try {
					fPrice = Float.parseFloat(price);
					iType = Integer.parseInt(type);
					
					DBHelper.createProduct(name, description, iType, fPrice, imagePath);
		            RequestDispatcher rs = request.getRequestDispatcher("viewproductadmin.jsp");
		            rs.forward(request, response);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					System.out.println("Invalid price input!");
		           	request.setAttribute("errorMessage", "Price has to be a number!");
		   	   		RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
		   	   		rs.forward(request, response);
				}
        	} else {
        		System.out.println("Account has no authorization to create a product!");
               	request.setAttribute("errorMessage", "You have no authorization to create a product!");
       	   		RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
       	   		rs.forward(request, response);
        	}
        } else {
        	System.out.println("No product name!");
           	request.setAttribute("errorMessage", "Product name cannot be blank!");
   	   		RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
   	   		rs.forward(request, response);
        }
	}

}
