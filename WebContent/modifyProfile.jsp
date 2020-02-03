<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import = "bean.UserBean"%>
	<%UserBean ub = (UserBean) request.getSession().getAttribute("currentUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Browser title bar, favorites, name for search engines -->
    <title>Modify Profile Page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<form action="ModifyProfileServlet" name="myform" method="POST">
<p>Name : <%=ub.getNome() %></p>
<p>Surname : <%=ub.getCognome() %></p>
<p>Username : <input type=text id =username name =username> </p>
<p>Insert Old Password : <input type=text id = oldpsw name = oldpsw></p>
<p>Insert New Password : <input type=text id = newpsw name = newpsw></p>
<Button>Submit</Button>
</form>
<Button type ="button" onclick ="history.back()">Back</Button>
<% if(request.getParameter("Error")!= null){ %>
<p>Error old/new password mismatch<p>
<%} %>
</body>
</html>