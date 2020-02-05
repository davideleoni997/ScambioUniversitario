<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="controller.MessageController"
    import="logic.Message"
    import="bean.UserBean"
    import="java.util.List"%>
    <%MessageController mc = MessageController.getInstance();
    UserBean ub = (UserBean) request.getSession().getAttribute("currentUser");
    List<Message> messages = mc.getConversation(Integer.parseInt(request.getParameter("Id")),ub.getId());%>
<!DOCTYPE html>
<html>
<head>
 <!-- Browser title bar, favorites, name for search engines -->
    <title>Message Detail page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<%for(Message message : messages){
	if(message!=null){%>
<p>From : <%= message.getSenderName() %>  Date : <%= message.getDate().toString() %></p>
<p>Message : <%= message.getDesc() %> </p>
	
<%}} %>

<p>New message</p>
<form action="MessageSendServlet" name="myform" method="POST">
<p><input type = text id = "newmsg" name = "newmsg"></p>
<input type = hidden id ="to" name = "to" value =<%= request.getParameter("Id")%>>
<p><button>Submit</button></p>
</form>
<p><Button type = Button onclick="history.back()">Back</Button></p>

</body>
</html>