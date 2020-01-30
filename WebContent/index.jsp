<%@page import="controller.OrderController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	
	<title>Scambio</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
    
</head>
<body>

<form action="AdminLoginServlet" name="myform" method="POST">
<button>Admin Login</button>
</form>

<form action="register.jsp" name="myform" method="POST">
<button>Register</button>
</form>

<form action="LoginServlet" name="myform" method="POST">
<button>Log In</button>
</form>

<h1 align ="center">SCAMBIO</h1>

<p align = center>Start by searching for a book!</p>

<form action ="research.jsp" name = "myForm" method ="POST">
<input type = "text" id = "research" name = "research">
<input type = "submit" value ="Search">
</form>

<p align = center>Or create your own insertion!</p>

<form action ="CheckUserServlet?page=newIns" name = "myForm" method = "POST">
<button>New Insertion</button>
</form>

</body>
</html>