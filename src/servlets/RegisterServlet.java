package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.Account;
import account.DBHelper;

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
		
        String cond = request.getParameter("cond");
        
        if(cond.equals("regs")){
            request.setAttribute("user", "");
            request.setAttribute("fname", "");
            request.setAttribute("lname", "");
            request.setAttribute("email", "");
            RequestDispatcher rs = request.getRequestDispatcher("reg.jsp");
            rs.forward(request, response);
        } else {
    		
            String user = request.getParameter("uname");
            String pass = request.getParameter("pass");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
        	
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
	                RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
	                request.setAttribute("errorMessage", "Registration Successful!");
	                rs.forward(request, response);
	        	} else {
	                RequestDispatcher rs = request.getRequestDispatcher("reg.jsp");
	                
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
	            RequestDispatcher rs = request.getRequestDispatcher("reg.jsp");
	            rs.forward(request, response);
	        }
        }
	}

}
