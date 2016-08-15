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
					String userType = "0";
					
					if(ck != null) {
						for(int i = 0; i < ck.length; i++) {
							if(ck[i].getName().equals("user")){
								user = ck[i].getValue();
							}
							
							if(ck[i].getName().equals("usertype")){
								userType = ck[i].getValue();
							}
						}			
					}
					
					System.out.println("userType: " + userType);
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
      <a class="pull-left" href="main.jsp"><img src="logo.png"></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="products.jsp?producttype=1">Catalog</a></li>
        <li><a href="products.jsp?producttype=2">Add</a></li>
        <li><a href="products.jsp?producttype=3">Edit</a></li>
        <li><a href="products.jsp?producttype=4">Delete</a></li>
        
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
          	<%if(userType.equals("2")) {%>
        <li><a href="#">Manage Products</a></li>
          	<%} else if(userType.equals("3")) {%>
        <li><a href="#">Manage Accounting</a></li>
          	<%} %>
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
   <div class="row">
      <div class="col-xs-3">
         <div class="panel panel-default">
            <img src="slipp.png" onclick="" />
            <h5>Turtle shell slippers</h5>
            <p class="text-muted">Php 999.00</p>
            <p class="text-muted"><small>Super hard slippers with materials from endangered turtle species. Made in China.</small></p>
         </div>
      </div>
      <!--Duplicate this part -->
      <div class="col-xs-3">
         <div class="panel panel-default">
            <img src="boots.png" onclick="" />
            <h5>Burnt Boots</h5>
            <p class="text-muted">Php 999.00</p>
            <p class="text-muted"><small>Boots from burned street dogs. Made in China.</small></p>
         </div>
      </div>
      <!--Duplicate until here -->
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
