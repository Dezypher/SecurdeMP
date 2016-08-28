package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import security.HTMLTagChecker;
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
            String user = HTMLTagChecker.cleanStringTags(request.getParameter("uname"));
            String pass = request.getParameter("pass");
            String fname = HTMLTagChecker.cleanStringTags(request.getParameter("fname"));
            String mname = HTMLTagChecker.cleanStringTags(request.getParameter("mname"));
            String lname = HTMLTagChecker.cleanStringTags(request.getParameter("lname"));
            String email = HTMLTagChecker.cleanStringTags(request.getParameter("email"));
        	
            //BILLING ADDRESS
            String bHouseNo = HTMLTagChecker.cleanStringTags(request.getParameter("bhouseno"));
            String bStreet = HTMLTagChecker.cleanStringTags(request.getParameter("bstreet"));
            String bSubd = HTMLTagChecker.cleanStringTags(request.getParameter("bsubd"));
            String bCity = HTMLTagChecker.cleanStringTags(request.getParameter("bcity"));
            String bPCode = HTMLTagChecker.cleanStringTags(request.getParameter("bpcode"));
            String bCountry = HTMLTagChecker.cleanStringTags(request.getParameter("bcountry"));
        
            //BILLING ADDRESS
            String sHouseNo = HTMLTagChecker.cleanStringTags(request.getParameter("shouseno"));
            String sStreet = HTMLTagChecker.cleanStringTags(request.getParameter("sstreet"));
            String sSubd = HTMLTagChecker.cleanStringTags(request.getParameter("ssubd"));
            String sCity = HTMLTagChecker.cleanStringTags(request.getParameter("scity"));
            String sPCode = HTMLTagChecker.cleanStringTags(request.getParameter("spcode"));
            String sCountry = HTMLTagChecker.cleanStringTags(request.getParameter("scountry"));
            
	        String errorMsg = "";
	        boolean accepted = true;
	        
	        if(user.length() == 0 	|| pass.length() == 0	|| 
	           fname.length() == 0 	|| lname.length() == 0 	|| 
	           mname.length() == 0 	|| email.length() == 0){
	        	errorMsg += "A field is empty!";
	        	accepted = false;
	        }
	        
	        if(pass.length() < 6) {
	        	errorMsg += "Password is too short! Has to be 6 to 64 characters long!";
	        	accepted = false;
	        }
	        
	        if(pass.length() > 64) {
	        	errorMsg += "Password is too short! Has to be 6 to 64 characters long!";
	        	accepted = false;
	        }
	        
	        if(user.length() < 6) {
	        	errorMsg += "Username is too short! Has to be 6 to 64 characters long!";
	        	accepted = false;
	        }
	        
	        if(user.length() > 64) {
	        	errorMsg += "Username is too short! Has to be 6 to 64 characters long!";
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
