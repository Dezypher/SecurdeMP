<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="models.Product"%>
<%@ page import="models.Address"%>
<%@ page import="database.DBHelper"%>

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
					
					int productID = 0;
					
					try {
						productID = Integer.parseInt(request.getParameter("productid"));
					} catch (NumberFormatException ex) {
					}
					
					Product product = DBHelper.getProduct(productID);
					
					int accountID = DBHelper.getAccountID(user);
					
					Address shippingAddress = DBHelper.getShippingAddress(accountID);
					Address billingAddress = DBHelper.getBillingAddress(accountID);
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
        <li><a href="viewproductadmin.jsp">Manage Products</a></li>
          	<%} else if(userType.equals("3")) {%>
        <li><a href="viewproductadmin.jsp">Manage Accounting</a></li>
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
            <img class="thumb1" src="<%=product.getImagePath()%>" />
            
         </div>
      </div>
      <div class="col-xs-3">
            <h3><%=product.getName()%></h3>
            <p class="text-muted">Php <%=product.getPrice()%></p>
           
            <br/><br/>
            <p class="text-muted"><small><%=product.getDescription()%></small></p>

            <br/><br/>
            <h4>Shipping Address</h4>
            <p><%=shippingAddress.getAddress()%></p>
            <br/>
            <h4>Billing Addess</h4>
            <p><%=billingAddress.getAddress()%></p>
            <br/>
            
            <form action="Buy" method="post" id="buyProduct">
	            <h4>Credit Card Credentials</h4>
	  				<div>${errorMessage}</div>
	               <input type="text" name="ccnumber" class="form-control" placeholder="Credit Card Number" aria-describedby="basic-addon1">
	               <br/>
	               <input type="text" name="cvc" class="form-control" placeholder="CVC" aria-describedby="basic-addon1">
	               <br/>
	               <input type="text" name="expdate" class="form-control" placeholder="Expiration Date" aria-describedby="basic-addon1">
	            <br/>
	            <input type="hidden" name="productid" value="<%=product.getProductID()%>">
	            
	            <button onclick="Submit()" class="btn btn-primary" type="button">Buy</button>
            </form>
      </div>

   </div>
   
</div>
  
  
    <script>
    	function Submit(){
    		document.forms["buyProduct"].submit();
    	}
    </script>


<style>
	.thumb1 { 
	background: url(blah.jpg) 50% 50% no-repeat; /* 50% 50% centers image in div */
		width: 250px;
		height: 250px;
	}
</style>

<footer class="footer">
<div class="container">
<p class="muted-credit">Talaria Footwear Company (c) 2016</p>
</div>
</footer>
</body>
</html>
