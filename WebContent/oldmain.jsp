<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>To-Do List</title>
</head>
<body>
	<%
		Cookie ck[] = request.getCookies();
		
		boolean loggedIn = false;
		String user = "";
	
		for(int i = 0; i < ck.length; i++) {
			if(ck[i].getName().equals("user")){
				loggedIn = true;
				user = ck[i].getValue();
			}
		}
		
		System.out.println("loggedIn: " + loggedIn + " user: " + user);
	%>
	<div>
		<form  method="post" action="Logout">
			<input type="submit" name="logout" value="Logout" />
		</form>
	</div>
	<div>
		<p>
			User: ${user}
		</p>
	
		<p>
			To-Do List
		</p>
	</div>
</body>
</html>