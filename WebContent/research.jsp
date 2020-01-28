<%@page import="controller.InsertionController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import = "bean.InsertionBean"
         import = "java.util.List"%>
         
         <%InsertionController ic = new InsertionController(); 
         List<InsertionBean> list = ic.getResearchResults(request.getParameter("research"),null);%>
         
<!DOCTYPE html>
<html>
<head>
<title>Research</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<p>
<form action ="research.jsp" method = "POST" name ="myform">

Search : <input type= text id = "research" name = "research"> 
<input type = submit value = "Search">
<!-- Aggiungere filtri e ordinamento -->

</form>
<button type = Button onclick="history.back()">Back</button>

<%for(InsertionBean inser : list){ %>

<p>Title : <%= inser.getBasic().getTitle()%> Price : <%= inser.getBasic().getPrice() %> Seller : <%= inser.getSeller() %> Data : <%=inser.getBasic().getDate() %></p>
<form action="insertionDetail.jsp?Id=<%=inser.getId() %>" method = "POST" name ="myform">
<input type= hidden id = "research" name = "research" value ="<%= request.getParameter("research")%>"> 
<Button>Details</Button>
</form>
<%} %>
</body>
</html>