<%@page import="controller.InsertionController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import = "bean.InsertionBean"
         import = "java.util.List"
         import = "logic.Filters"
         import = "logic.Filters.Date"%>
         
         <%InsertionController ic = InsertionController.getInstance();    
         Filters filter = new Filters();
         if(!request.getParameter("uni").isEmpty())
        	 filter.setUniversity(request.getParameter("uni"));
         if(!request.getParameter("city").isEmpty())
        	 filter.setCity(request.getParameter("city"));
         if(!request.getParameter("subj").isEmpty())
        	 filter.setSubject(request.getParameter("subj"));
         if(request.getParameter("book").equals("yes"))
        	 filter.setBook(true);
         else
        	 filter.setBook(false);
         if(request.getParameter("notes").equals("yes"))
        	 filter.setNotes(true);
         else
        	 filter.setNotes(false);
         
         if(request.getParameter("order").equals("new"))
        	 filter.setDate(Date.NEW);
         else
        	 filter.setDate(Date.OLD);
         
         List<InsertionBean> list = ic.getResearchResults(request.getParameter("research"),filter);%>
         
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
<body class="nostroSito">

<p>
<form action ="research.jsp" method = "POST" name ="myform">

Search : <input type= text id = "research" name = "research"> 
	<input type = hidden id ="uni" name = "uni" value ="">
    <input type = hidden id ="city" name = "city" value ="">
    <input type = hidden id ="subj" name = "subj" value ="">
    <input type = hidden name = "book" id = "book" value="yes">
    <input type = hidden name = "notes" id ="notes" value="yes">
    <input type = hidden name = "order" id = "order" value="new">
<input type = submit value = "New Research">
</form>

<form action ="research.jsp" method = "POST" name ="myform">
<div class="dropdown">
  <button class="dropbtn">Filters</button>
  <div class="dropdown-content">
    <a>University</a>
    <input type = text id ="uni" name = "uni">
    <a>City</a>
    <input type = text id ="city" name = "city">
    <a>Subject</a>
    <input type = text id ="subj" name = "subj">
    
    <input type ="radio" name = "book" id = "book" value="yes" <%  if(request.getParameter("book").equals("yes")){ %>
    checked
    <% }%>
    >Yes Book
    <input type ="radio" name = "book" id = "book" value="no"<% if(request.getParameter("book").equals("no")){
    %> checked  <%} %>>No
   
    <a></a>

    <input type ="radio" name = "notes" id = "notes" value="yes" <%  if(request.getParameter("notes").equals("yes")){ %>
    checked
    <% }%>>Yes Notes
     <input type ="radio" name = "notes" id = "notes" value="no" <% if(request.getParameter("notes").equals("no")){
    %> checked  <%} %>>No
     <a></a>
     <button>Apply</button>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">Order</button>
  <div class="dropdown-content">

    <input type ="radio" name = "order" id = "order" value="new" <% if(request.getParameter("order").equals("new")){
    %> checked  <%} %>>Latest Date
     <input type ="radio" name = "order" id = "order" value="old" <% if(request.getParameter("order").equals("old")){
    %> checked  <%} %>>Oldest
     <a></a>
     <button>Apply</button>
  </div>
</div>

</form>
<button type = Button onclick="history.back()">Back</button>

<%for(InsertionBean inser : list){ %>

<p>Title : <%= inser.getBasic().getTitle()%> Price : <%= inser.getBasic().getPrice() %> Seller : <%= inser.getSeller() %> Data : <%=inser.getBasic().getDate() %></p>
<form action="insertionDetail.jsp?Id=<%=inser.getId() %>" method = "POST" name ="myform">
<input type= hidden id = "err" name = "err" value =""> 
<Button>Details</Button>
</form>
<%} %>
</body>
</html>