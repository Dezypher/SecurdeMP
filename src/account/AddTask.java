package account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddTask() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String title = request.getParameter("title");
		String details = request.getParameter("details");
		
		Cookie ck[] = request.getCookies();

		String user = "";

		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				user = ck[i].getValue();
			}
		}	
		
		if(title.length() > 0)
        {
			DBHelper.createTask(title, details, user);
            RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
            rs.forward(request, response);
        }
        else
        {
        	System.out.println("Username or Password incorrect");
           	request.setAttribute("errorMessage", "Title cannot be blank!");
   	   		RequestDispatcher rs = request.getRequestDispatcher("add.jsp");
   	   		rs.forward(request, response);
        }
	}

}
