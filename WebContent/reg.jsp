<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
    	<div>${errorMessage}</div>
        <form method="post" action="Register">
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>First Name</td>
                        <% String fname = request.getParameter("fname"); %>
          				<% if(fname == null) { %>
                        	<td><input type="text" name="fname" value="" /></td>
          				<% } else { %>
                        	<td><input type="text" name="fname" value=${fname} /></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <% String lname = request.getParameter("lname"); %>
          				<% if(lname == null) { %>
                        	<td><input type="text" name="lname" value="" /></td>
          				<% } else { %>
                        	<td><input type="text" name="lname" value=${lname} /></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <% String email = request.getParameter("email"); %>
          				<% if(email == null) { %>
                        	<td><input type="text" name="email" value="" /></td>
          				<% } else { %>
                        	<td><input type="text" name="email" value=${email} /></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>User Name</td>
                        <% String uname = request.getParameter("uname"); %>
          				<% if(uname == null) { %>
                        	<td><input type="text" name="uname" value="" /></td>
          				<% } else { %>
                        	<td><input type="text" name="uname" value=${uname} /></td>
                        <% } %>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <% String pass = request.getParameter("pass"); %>
          				<% if(pass == null) { %>
                        	<td><input type="password" name="pass" value="" /></td>
          				<% } else { %>
                        	<td><input type="password" name="pass" value=${pass} /></td>
                        <% } %>
                    </tr>
                    <tr>
                    	<input type="hidden" name="cond" value="try"/>
                        <td><input type="submit" value="Submit" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">Already registered!! <a href="index.jsp">Login Here</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>