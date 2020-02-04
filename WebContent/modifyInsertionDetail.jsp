<%@page import="controller.InsertionController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import = "bean.InsertionBean"
         import = "java.util.List"
         import = "bean.UserBean"%>
         
         <%InsertionController ic =InsertionController.getInstance();  
         InsertionBean inser = ic.getDetail(Integer.parseInt(request.getParameter("Id")));
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
	<title>Modify Insertion</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">

<p>Title : <%= inser.getBasic().getTitle()%> Price : <%= inser.getBasic().getPrice() %> Seller : <%= inser.getSeller() %> Data : <%=inser.getBasic().getDate() %></p>
<p>Description : <%=inser.getBasic().getDesc() %> 
<p>Sold : <%= inser.getSold()%> Subject : <%= inser.getFilter().getSubject()%> City : <%=inser.getFilter().getCity() %>
<p>University : <%=inser.getFilter().getUniversity()%> Book : <%=inser.getFilter().getBook() %> Notes : <%=inser.getFilter().getNotes() %>
<!-- Trovare modo di convertire imgs -->
<form action ="ModifyInsertionServlet?Id=<%= inser.getId() %>" method = "POST" name = "myform">
<p>Title :<input type = text name ="object">
<p>Price :<input type = text name ="price">
<p>Description :<textarea  id = desc name ="desc" rows = 4 cols = 40><%=inser.getBasic().getDesc() %></textarea>
<p>Subject :<input type = text name ="subj">
<p>City :<input type = text name ="city">
<p>University :<input type = text name ="uni">
<p><input type ="radio" name = "book" id = "book" value="yes" <%if(inser.getFilter().getBook()){%>checked <%}%>>Book <input type = "radio" name = "notes" id = "notes" value ="yes"<%if(inser.getFilter().getNotes()){%>checked <%}%>>Notes
<input type = "hidden" name ="op" value ="modify">
</p>
<button>Modify</button>
</form>
<form action="ModifyInsertionServlet?Id=<%= inser.getId() %>" method = "POST" name ="myform">
<input type = "hidden" name ="op" value ="delete">
<button>Delete</button>
</form>
<%} %>
<Button type = Button onclick="history.back()">Back</Button>
<%= request.getParameter("err") %>
</body>
</html>