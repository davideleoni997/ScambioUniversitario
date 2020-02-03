<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.MessageController"
    import="logic.Message"
    import="bean.UserBean"
    import="java.util.List"%>
    <%MessageController mc = new MessageController(); 
    UserBean ub = (UserBean) request.getSession().getAttribute("currentUser");
    List<Message> messages = mc.getMessageList(ub);%>
<!DOCTYPE html>
<html>
<head>
    <!-- Browser title bar, favorites, name for search engines -->
    <title>Messages page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<p align = center>Messages : <Button type = button onclick="history.back()">Back</Button></p>

<%for(Message message : messages){
if(message!=null){%>

<p>From : <%= message.getFrom()%></p>
<form action="MessageDetailServlet?Id=<%= message.getFrom() %>" name="myform" method="POST">
<button>Details</button>
</form>
<%}} %>
</body>
</html>