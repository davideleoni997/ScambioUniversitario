<%@page import="controller.OrderController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import ="bean.UserBean"
         import ="logic.Order"%>
         <%UserBean ub = (UserBean) request.getSession().getAttribute("currentUser"); %>
         <%
         Order order = OrderController.getOrderDetail(Integer.parseInt(request.getParameter("Id")));				%>
<!DOCTYPE html>
<html>
<head>
	<title>Ordine</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<p>Buyer : <%=order.getBuyer() %></p>
<p>Seller : <%=order.getSeller() %></p>
<p>Price : <%=order.getItem().getPrezzo() %></p>
<p>Item : <%=order.getItem().getNome() %></p>
<p>Data : <%=order.getData() %></p>
<p>Paid : <%=order.getPaid() %></p>
<%if(!order.getPaid()){ %>
	<form action="PaymentServlet?Id=<%= order.getId()%>" name="myform" method="POST">
	<Button>Pay</Button>
	</form>
<%} %>
<Button type = button onclick="history.back()">Back</Button>
</body>
</html>