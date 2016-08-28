<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="java.util.ArrayList"%>
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
					
					int sortType = 0;
					
					try {
						sortType = Integer.parseInt(request.getParameter("sort"));
					} catch (Exception ex) {
						
					}
					
					System.out.println("userType: " + userType);
					
					ArrayList<Product> products = DBHelper.getProductsSorted(sortType);
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
      <form class="navbar-form navbar-left" role="search" action="Search" method="post">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" name="search">
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
<div class="container">
<h3>Sales: January 1, 2016 - January 2, 2016</h3>
   <div class="row">
   <a href="">Sales Ascending</a> | 
   <a href="">Sales Descending</a>   
   </div>

   <div class="row">
   <a href="">Newest</a> |
   <a href="">Oldest</a> |
   <a href="">Name</a> 
   
   </div>
  <div class="row">
   <div class="col-xs-3">
        <input type="text" class="form-control" placeholder="Date Start" aria-describedby="basic-addon1">
    </div>
    <div class="col-xs-3">
        <input type="text" class="form-control" placeholder="Date End" aria-describedby="basic-addon1">
    </div>
   </div><br/>
   <div class="row">
      <div class="col-xs-5">
            <label class="radio-inline"><input type="radio" name="type" onclick = "" value="1" checked="checked">All</label>
            <label class="radio-inline"><input type="radio" name="type" onclick = "" value="1" checked="checked">Boots</label>
            <label class="radio-inline"><input type="radio" name="type" onclick = "" value="1" checked="checked">Shoes</label>
            <label class="radio-inline"><input type="radio" name="type" onclick = "" value="1" checked="checked">Sandals</label>
            <label class="radio-inline"><input type="radio" name="type" onclick = "" value="1" checked="checked">Slippers</label>
       </div>
        
   </div>
   <div class="row">
    <div class="col-xs-3">
        <button class="btn btn-primary" type="button">Go</button>
    </div>
  </div><br/>      
  <table class="table">
    <thead>
      <tr>
        <th>Product Name</th>
        <th>Total Sales</th>
      </tr>
    </thead>
    <tbody>
    <!-- duplicate this part -->
      <tr>
        <td>Killer Crocs</td>
        <td>Php 3000.00</td>
      </tr>
      <!-- until here -->
    </tbody>
  </table>
   <h3>Grand Total: Php 9000.00</h3>
</div>

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
