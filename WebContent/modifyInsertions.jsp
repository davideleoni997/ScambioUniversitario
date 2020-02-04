<%@page import="controller.InsertionController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import = "bean.InsertionBean"
         import = "java.util.List"
         import = "bean.UserBean"%>
         
         <%InsertionController ic = InsertionController.getInstance();    
         UserBean ub = (UserBean) request.getSession().getAttribute("currentUser");
         List<InsertionBean> list = ic.myInsertions(ub.getId());%>
         
<!DOCTYPE html>
<html>
<head>
<title>Modify Insertions</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
   	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<link href="css/style.css" rel="stylesheet" type="text/css">
 
</head>
<body class="nostroSito">

<p>Your insertions :

<button type = Button onclick="history.back()">Back</button>

<%for(InsertionBean inser : list){ %>

<p>Title : <%= inser.getBasic().getTitle()%> Price : <%= inser.getBasic().getPrice() %> Seller : <%= inser.getSeller() %> Data : <%=inser.getBasic().getDate() %></p>
<form action="modifyInsertionDetail.jsp?Id=<%=inser.getId() %>" method = "POST" name ="myform">
<input type= hidden id = "err" name = "err" value =""> 
<Button>Modify</Button>
</form>
<%} %>
</body>
</html>