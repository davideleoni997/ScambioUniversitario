<%@page import="controller.InsertionController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import = "bean.InsertionBean"
         import = "java.util.List"
         import = "bean.UserBean"%>
         
         <%InsertionController ic = new InsertionController(); 
         List<InsertionBean> list = ic.getResearchResults(request.getParameter("research"),null);
         if(request.getSession().getAttribute("currentUser") == null){
        	 %>
        	 <jsp:forward page="login.jsp"></jsp:forward>
        <%}
         else{
         	UserBean ub = (UserBean) request.getSession().getAttribute("currentUser");
         %>
<!DOCTYPE html>
<html>
<head>
	<title>Insertion</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">

<%for(InsertionBean inser : list){ 
	if(inser.getId() == Integer.parseInt(request.getParameter("Id"))){%>

<p>Title : <%= inser.getBasic().getTitle()%> Price : <%= inser.getBasic().getPrice() %> Seller : <%= inser.getSeller() %> Data : <%=inser.getBasic().getDate() %></p>
<p>Description : <%=inser.getBasic().getDesc() %> Sold : <%= inser.getSold() %>

<!-- Trovare modo di convertire imgs -->
<form action ="InsertionDetailServlet" method = "POST" name = "myform">
<input type = "hidden" name ="buyer" value ="<%= ub.getId()%>">
<input type = "hidden" name ="seller" value ="<%=inser.getSellerId() %>">
<input type = "hidden" name ="object" value ="<%=inser.getBasic().getTitle() %>">
<input type = "hidden" name ="insertion" value ="<%=inser.getId()%>">
<input type = "hidden" name ="price" value ="<%=inser.getBasic().getPrice() %>">
<input type = "hidden" name ="research" value ="<%=request.getParameter("research") %>">
<button>Buy</button>
</form>
<form action="report.jsp?Id=<%=request.getParameter("Id")%>" method = "POST" name ="myform">
<button>Report</button>
</form>
<%} }}%>
<Button type = Button onclick="history.back()">Back</Button>
<%= request.getParameter("err") %>
</body>
</html>