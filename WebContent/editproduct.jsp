<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
   <title>Talaria Co.</title>
   <link href="css/bootstrap.min.css" rel="stylesheet">
   <script src="js/bootstrap.min.js"></script>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
      <div class="nav navbar-nav navbar-right">
         <ul class="nav navbar-nav">
        <li><a href="#">Log In</a></li>
        <li><a href="#">Sign Up</a></li>        
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
  <div class="row">
     <div class="col-xs-3">
        <input type="text" class="form-control" placeholder="Name" aria-describedby="basic-addon1">
    </div>
    <div class="col-xs-3">
        <input type="text" class="form-control" placeholder="Price" aria-describedby="basic-addon1">
    </div>
  </div><br/>
  <div class="row">
     <div class="col-xs-5">
        <input type="text" class="form-control" placeholder="Description" aria-describedby="basic-addon1">
    </div>
    <div class="col-xs-1">
        <input type="text" class="form-control" placeholder="Stock" aria-describedby="basic-addon1">
    </div>
  </div><br/>

  <button class="btn btn-primary" type="button">Submit</button>

   </div>
</div>
  

<footer class="footer">
<div class="container">
<p class="muted-credit">Talaria Footwear Company (c) 2016</p>
</div>
</footer>
</body>
</html>
