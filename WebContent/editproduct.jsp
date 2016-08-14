<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="models.Product"%>
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
   <div class="panel panel-default">
      <div class="page-header">
    <h3>Edit Product</h3>
  </div>
  <form action="EditProduct" method="post" id="editproduct">
  		<div>${errorMessage}</div>
  <div class="row">
     <div class="col-xs-3">
        <input type="text" name="pname" class="form-control" placeholder="Name" aria-describedby="basic-addon1"
        	value="<%=product.getName()%>">
    </div>
    <div class="col-xs-3">
        <input type="number" name="price" class="form-control" placeholder="Price" aria-describedby="basic-addon1"
        	value="<%=product.getPrice()%>">
    </div>
  </div><br/>
  <div class="row">
     <div class="col-xs-5">
        <input type="text" name="description" class="form-control" placeholder="Description" aria-describedby="basic-addon1"
        	value="<%=product.getDescription()%>">
    </div>
    <div class="col-xs-1">
        <input type="text" name="stock" class="form-control" placeholder="Stock" aria-describedby="basic-addon1">
    </div>
  </div><br/>
  <div class="row"></div>
    <div class="col-xs-5">
        <input type="text" name="imagepath" class="form-control" placeholder="Image Link" aria-describedby="basic-addon1"
        	value="<%=product.getImagePath()%>">
    </div>
    <div class="col-xs-1">
        <input type="text" name="type" class="form-control" placeholder="Type" aria-describedby="basic-addon1"
        	value="<%=product.getType()%>">
    </div>
  </div></br>
  
  <button onclick="Submit()" class="btn btn-primary" type="button">Submit</button>
		<input type="hidden" name="productid" value="<%=productID%>">
	</form>
   </div>
</div>
  
  <script>
  	function Submit(){
  		document.forms["editproduct"].submit();
  	}
  </script>

<footer class="footer">
<div class="container">
<p class="muted-credit">Talaria Footwear Company (c) 2016</p>
</div>
</footer>
</body>
</html>
