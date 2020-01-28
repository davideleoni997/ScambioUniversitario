<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" 
         import="controller.OrderController"
         %>
         
<jsp:useBean id="currentUser" class="bean.UserBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <!-- Browser title bar, favorites, name for search engines -->
    <title>Profile page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<p> Name : <jsp:getProperty property="nome" name="currentUser"/> </p>
<p> Surname : <jsp:getProperty property="cognome" name="currentUser"/></p>
<a href="http://localhost:8080/Dynamic_Scambio/OrdersServlet">View Orders</a>
<p></p>
<a href="http://localhost:8080/Dynamic_Scambio/modifyProfile.jsp">Modify profile</a>
<p></p>
<a href="http://localhost:8080/Dynamic_Scambio/MessagesServlet">Messages</a>
<p></p>
<a href="http://localhost:8080/Dynamic_Scambio/LogOutServlet">Log Out</a>
<p></p>
<a href="index.jsp">Back</a>
</body>
</html>