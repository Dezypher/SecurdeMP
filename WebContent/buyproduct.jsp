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
        <li><a href="products.jsp?producttype=1">Boots</a></li>
        <li><a href="products.jsp?producttype=2">Shoes</a></li>
        <li><a href="products.jsp?producttype=3">Sandals</a></li>
        <li><a href="products.jsp?producttype=4">Slippers</a></li>
        
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
            <img src="slipp.png" />
            
         </div>
      </div>
      <div class="col-xs-3">
            <h3>Turtle shell slippers</h3>
            <p class="text-muted">Php 999.00</p>
           
            <br/><br/>
            <p class="text-muted"><small>Super hard slippers with materials from endangered turtle species. Made in China.</small></p>

            <br/><br/>
            <h4>Shipping Address</h4>
            <p>B7 L3 Villa Castillo San Pedro, Laguna</p>
            <br/>
            <h4>Billing Addess</h4>
            <p>B7 L3 Villa Castillo San Pedro, Laguna</p>
            <br/>
            <h4>Credit Card Credentials</h4>
               <input type="text" class="form-control" placeholder="Credit Card Number" aria-describedby="basic-addon1">
               <br/>
               <input type="text" class="form-control" placeholder="CVC" aria-describedby="basic-addon1">
               <br/>
               <input type="text" class="form-control" placeholder="Expiration Date" aria-describedby="basic-addon1">
            <br/>
            <button onclick="Submit()" class="btn btn-primary" type="button">Buy</button>
      </div>

   </div>
   
</div>
  
  
    <script>
    	function Submit(){
    		alert("buy");
    	}
    </script>

<footer class="footer">
<div class="container">
<p class="muted-credit">Talaria Footwear Company (c) 2016</p>
</div>
</footer>
</body>
</html>
