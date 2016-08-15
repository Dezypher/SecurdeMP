<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="models.Product"%>
<%@ page import="models.Review"%>
<%@ page import="java.util.ArrayList"%>
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
						ex.printStackTrace();
					}
					
					Product product = DBHelper.getProduct(productID);
					ArrayList<Review> reviews = DBHelper.getReviews(productID);
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
		            <img src="<%=product.getImagePath()%>" onclick="" />
		            <h5><%=product.getName()%></h5>
		            <p class="text-muted">Php <%=product.getPrice()%></p>
		            <p class="text-muted"><small><%=product.getDescription()%></small></p>
		         </div>
		      </div>
   </div>
   
   <% if(userType.equals("1")){%>
	   <div><a href=<%="buyproduct.jsp?productid=" + product.getProductID()%>>
	  	<button class="btn btn-primary" type="button">Buy</button>
	  </a></div>
	<% } else { %>
		<div>Log in to buy this product!</div>
	<% } %>
   
	<div><h2>Reviews</h2></div>
  	<% for (int i = 0; i < reviews.size(); i++) { %>
	   <div>
	   		<div><%=reviews.get(i).getAuthor()%>Rating: <%=reviews.get(i).getRating()%>/5.0</div><br/>
	   		
	   		<div><%=reviews.get(i).getReview()%></div>
	   </div>
	   </br>
   <% } %>
   
   <% if(userType.equals("1")) { %>
   <div>
   		<div>Write your review: </div><br/>
   		<form action="SendReview" method="post" id="sendReview">
        	<input type="text" name="review" class="form-control" placeholder="Review..." aria-describedby="basic-addon1">
        	<br/>
        		<div class="row">
			      <div class="col-xs-5">
			      <h5>Rating</h5>
			      <label class="radio-inline"><input type="radio" name="rating" onclick = \"getAnswer('1') value="1">1</label>
			      <label class="radio-inline"><input type="radio" name="rating" onclick = \"getAnswer('2') value="2">2</label>
			      <label class="radio-inline"><input type="radio" name="rating" onclick = \"getAnswer('3') value="3">3</label>
			      <label class="radio-inline"><input type="radio" name="rating" onclick = \"getAnswer('4') value="4">4</label>
			      <label class="radio-inline"><input type="radio" name="rating" onclick = \"getAnswer('5') value="5" checked="checked">5</label>
			      </div>
			   </div>
        	<input type="hidden" name="author" value="<%=user%>">
        	<input type="hidden" name="productid" value="<%=productID%>">
        </br>
  		<button onclick="sendReview()" class="btn btn-primary" type="button">Submit</button>
  		</form>
  		<br/>
   </div>
   <% } %>
</div>
  
    <script>
    	function signup(){
    		document.forms["logout"].submit();
    	}

    	function sendReview(){
    		document.forms["sendReview"].submit();
    	}
    </script>

<footer class="footer">
<div class="container">
<p class="muted-credit">Talaria Footwear Company (c) 2016</p>
</div>
</footer>
</body>
</html>
