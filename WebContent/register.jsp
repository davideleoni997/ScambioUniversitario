<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Browser title bar, favorites, name for search engines -->
    <title>Registration page</title>
    <!-- info about content, e.g.: content type, keywords, charset or description -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<p align = center>Are you a student or a society?</p>
<form action ="RegisterServlet" name = "myForm" method ="POST">
<p align = center><input type ="radio" name = "type" id = "type" value="student">Student <input type = "radio" name = "type" id = "type" value ="company">Company</p>
<p>Enrollment Number : <input type = text name = "enroll" id = "enroll"></p>
<p>Name :<input type = text name = "name" id = "name"></p>
<p>Surname :<input type = text name = "surname" id = "surname"></p>
<p>Username :<input type = text name = "username" id = "username"></p>
<p>New Password :<input type = text name = "newpsw" id = "newpsw"></p>
<p>Confirm password :<input type = text name = "checkpsw" id = "checkpsw"></p>

<p>Company Logo :<input type = file name = "logo" id = "logo"></p>

<p><input type = submit name = "submit" value = "submit">
</form>
<button type = button onclick="history.back()">Back</button>
</body>
</html>