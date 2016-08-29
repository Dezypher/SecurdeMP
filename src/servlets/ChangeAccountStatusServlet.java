package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBHelper;

/**
 * Servlet implementation class ChangeAccountStatusServlet
 */
@WebServlet("/ChangeAccountStatus")
public class ChangeAccountStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAccountStatusServlet() {
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
		
		String statusSet = request.getParameter("status");
		String accountID = request.getParameter("accountid");
		
		int accID = -1;
		
		try {
			accID = Integer.parseInt(accountID);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		
		if(accID != -1) {
			if(statusSet.equals("0")) {
				DBHelper.lockAccount(accID);
	           	request.setAttribute("errorMessage", "Account locked!");
			} else {
				DBHelper.unlockAccount(accID);
	           	request.setAttribute("errorMessage", "Account unlocked!");
			}
			
   	   		RequestDispatcher rs = request.getRequestDispatcher("viewaccounts.jsp");
   	   		rs.forward(request, response);
		} else {
			
		}
	}

}
