<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
   <title>Talaria Co.</title>
   <link href="css/bootstrap.min.css" rel="stylesheet">
   <script src="js/bootstrap.min.js"></script>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   
   <%
						Cookie ck[] = request.getCookies();
					String user = "";
	
					for(int i = 0; i < ck.length; i++) {
						if(ck[i].getName().equals("user")){
							user = ck[i].getValue();
						}
					}			
					
	%>
	
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="pull-left" href="#"><img src="logo.png"></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="#">Boots</a></li>
        <li><a href="#">Shoes</a></li>
        <li><a href="#">Sandals</a></li>
        <li><a href="#">Slippers</a></li>
        
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default"><span class = "glyphicon glyphicon-search"></span></button>
      </form>
      
      <form method="post" action="Logout" id="logout">
      </form>
      <div class="nav navbar-nav navbar-right">
         <ul class="nav navbar-nav">
         <% if(user.length() == 0) { %>
        <li><a href="login.jsp">Log In</a></li>
        <li><a href="signup.jsp">Sign Up</a></li>   
          <%} else { %>
        <li><a href="#"><%=user%></a></li>
        <li><a href="Logout">Logout</a></li>   
        <%} %>
      </ul>
      </div>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>
<br/>
<!-- put contents here -->

<div class="container">
   <div class="panel panel-default">
      
   </div>
</div>
  
    <script>
    	function signup(){
    		document.forms["logout"].submit();
    	}
    </script>

<footer class="footer">
<div class="container">
<p class="muted-credit">Talaria Footwear Company (c) 2016</p>
</div>
</footer>
</body>
</html>
