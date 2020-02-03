<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "controller.ReportController"%>
<!DOCTYPE html>
<html>
<head>
	<title>Report</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">

<p>ID : <%=request.getParameter("Id")%>


<form action="NewReportServlet" method ="POST" name ="myform">
<input type = "hidden" name ="insertion" value ="<%=request.getParameter("Id") %>">
<textarea rows="10" cols="40" name ="desc" id ="desc"></textarea>
<button>Submit</button>
</form>
<button type = button onclick="history.back()">Back</button>
</body>
</html>