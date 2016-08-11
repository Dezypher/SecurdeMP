<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
	<body>
	<%
	   // New location to be redirected
	   	String site = new String("main.jsp");
		System.out.println("REDIRECT");
		
	   	response.setStatus(response.SC_MOVED_TEMPORARILY);
	   	response.setHeader("Location", site); 
	%>
		<p>Login Page</p>
		<div>${errorMessage}</div>
		<form method="post" action="Login">
			<div>
				<p>Username: </p>
				<input name="uname" value ="">
			</div>
	
			<div>
				<p>Password: </p>
				<input type="password" name="pass" value = "">
			</div>
	
			<input type="submit" value="Login" />
		</form>
		<form method="post" action="Register">
			<input type="hidden" name="cond" value = "regs" />
			<input type="submit" value="Register" />
		</form>
	</body>

</html>