<%@page import="controller.InsertionController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import = "bean.InsertionBean"
         import = "java.util.List"
         import = "bean.UserBean"
         import = "java.io.File"
         import = "java.awt.image.BufferedImage"
         import = "javafx.embed.swing.SwingFXUtils"
         import = "javax.imageio.ImageIO"
         import = "java.io.IOException"%>
         
         <%InsertionController ic =InsertionController.getInstance();  
         InsertionBean inser = ic.getDetail(Integer.parseInt(request.getParameter("Id")));
         if(request.getSession().getAttribute("currentUser") == null){
        	 %>
        	 <jsp:forward page="login.jsp"></jsp:forward>
        <%}
         else{
        	
         	UserBean ub = (UserBean) request.getSession().getAttribute("currentUser");
         	if(inser.getImages().size()!=0){
         	File outputFile = new File(application.getRealPath("/")+"/WEB-INF/main.png");
            BufferedImage bImage = SwingFXUtils.fromFXImage(inser.getImages().get(0), null);
            try {
              ImageIO.write(bImage, "png", outputFile);
            } catch (IOException e) {
              e.printStackTrace();
            }
         	}
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

<p>Title : <%= inser.getBasic().getTitle()%> Price : <%= inser.getBasic().getPrice() %> Seller : <%= inser.getSeller() %> Data : <%=inser.getBasic().getDate() %></p>
<p>Description : <%=inser.getBasic().getDesc() %> 
<p>Sold : <%= inser.getSold()%> Subject : <%= inser.getFilter().getSubject()%> City : <%=inser.getFilter().getCity() %>
<p>University : <%=inser.getFilter().getUniversity()%> Book : <%=inser.getFilter().getBook() %> Notes : <%=inser.getFilter().getNotes() %>
</p>
<%if(inser.getImages().size()!=0){ %>
<img src="<%=application.getRealPath("/") %>/WEB-INF/main.png" width = "200" height = "200">
<%} %>
<form action ="InsertionDetailServlet" method = "POST" name = "myform">
<input type = "hidden" name ="buyer" value ="<%= ub.getId()%>">
<input type = "hidden" name ="seller" value ="<%=inser.getSellerId() %>">
<input type = "hidden" name ="object" value ="<%=inser.getBasic().getTitle() %>">
<input type = "hidden" name ="insertion" value ="<%=inser.getId()%>">
<input type = "hidden" name ="price" value ="<%=inser.getBasic().getPrice() %>">

<button>Buy</button>
</form>
<form action="report.jsp?Id=<%=request.getParameter("Id")%>" method = "POST" name ="myform">
<button>Report</button>
</form>
<%} %>
<Button type = Button onclick="history.back()">Back</Button>
<%= request.getParameter("err") %>
</body>
</html>