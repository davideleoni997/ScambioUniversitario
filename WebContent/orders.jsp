<%@page import="controller.OrderController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import ="bean.UserBean"
         import ="logic.Order"
         import ="java.util.List"%>
         <%UserBean ub = (UserBean) request.getSession().getAttribute("currentUser"); %>
         <%OrderController oc = new OrderController(); 
         List<Order> list = oc.getOrdersInfo(ub.getUsername());%>
<!DOCTYPE html>
<html>
<head>
	<title>Ordini</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<p align = center>Your orders : <Button type = "button" onclick="history.back()" >Back</Button></p>
<%for(Order order : list){
	if(order != null){%>
	<p align = center>Order Number: <%=order.getId() %></p>
	<form action="OrderDetailServlet?Id=<%=order.getId()%>" name="myform" method="POST">
	<Button>Details</Button>
	</form>
<%}}%>
</body>
</html>