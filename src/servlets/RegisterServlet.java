package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import database.DBHelper;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
        //String cond = request.getParameter("cond");
        
        /*
        if(cond.equals("regs")){
            request.setAttribute("user", "");
            request.setAttribute("fname", "");
            request.setAttribute("lname", "");
            request.setAttribute("email", "");
            RequestDispatcher rs = request.getRequestDispatcher("reg.jsp");
            rs.forward(request, response);
        } else */
        //{
        	//ACCOUNT DETAILS
            String user = request.getParameter("uname");
            String pass = request.getParameter("pass");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
        	
            //BILLING ADDRESS
            String bHouseNo = request.getParameter("bhouseno");
            String bStreet = request.getParameter("bstreet");
            String bSubd = request.getParameter("bsubd");
            String bCity = request.getParameter("bcity");
            String bPCode = request.getParameter("bpcode");
            String bCountry = request.getParameter("bcountry");
        
            //BILLING ADDRESS
            String sHouseNo = request.getParameter("shouseno");
            String sStreet = request.getParameter("sstreet");
            String sSubd = request.getParameter("ssubd");
            String sCity = request.getParameter("scity");
            String sPCode = request.getParameter("spcode");
            String sCountry = request.getParameter("scountry");
            
	        String errorMsg = "";
	        boolean accepted = true;
	        
	        if(user.length() == 0 	|| pass.length() == 0 	|| 
	           fname.length() == 0 	|| lname.length() == 0 	|| 
	           mname.length() == 0 	|| email.length() == 0){
	        	errorMsg += "A field is empty!";
	        	accepted = false;
	        }
	        
	        if(user.contains(" ") || pass.contains(" ") || email.contains(" ")){
	            errorMsg += " Username, Password and E-Mail cannot contain spaces.";
	        	
	        	accepted = false;
	        }
	        
	        if(accepted){
	        	int result = DBHelper.createUser(user, pass, Account.TYPE_CUSTOMER, email, fname, mname, lname);
	        	
	        	if(result == 0) {
	        		int accountID = DBHelper.getAccountID(user);
	        		
	        		DBHelper.addBillingAddress(accountID, bHouseNo, bStreet, bSubd, bCity, bPCode, bCountry);
	        		DBHelper.addShippingAddress(accountID, sHouseNo, sStreet, sSubd, sCity, sPCode, sCountry);
	        		
	                RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
	                request.setAttribute("errorMessage", "Registration Successful!");
	                rs.forward(request, response);
	        	} else {
	                RequestDispatcher rs = request.getRequestDispatcher("signup.jsp");
	                
	        		if(result == 1)
	                    request.setAttribute("errorMessage", "Username taken!");
	        		if(result == 2)
	                    request.setAttribute("errorMessage", "E-Mail taken!");
	        		if(result == 3)
	                    request.setAttribute("errorMessage", "Account taken!");
	        		
	                request.setAttribute("user", user);
	                request.setAttribute("fname", fname);
	                request.setAttribute("lname", lname);
	                request.setAttribute("email", email);
	        		
	                rs.forward(request, response);
	        	}
	        	
	        } else {
	            request.setAttribute("errorMessage", errorMsg);
	            request.setAttribute("user", user);
	            request.setAttribute("fname", fname);
	            request.setAttribute("lname", lname);
	            request.setAttribute("email", email);
	            RequestDispatcher rs = request.getRequestDispatcher("signup.jsp");
	            rs.forward(request, response);
	        }
        //}
	}
}
