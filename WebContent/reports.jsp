<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "controller.ReportController"
    import = "logic.Report"
    import = "java.util.List"%>
    <%ReportController rc = ReportController.getInstance();
    List<Report> reports = rc.getReport();%>
<!DOCTYPE html>
<html>
<head>
	<title>Reports</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<p align = center>Available reports <button type = button onclick="history.back()">Back</button>
<form action="http://localhost:8080/Dynamic_Scambio/LogOutServlet" name="myform" method="POST">
<button>Log Out</button>
</form>

<%for(Report report : reports){ %>
<p>Id Number :<%= report.getId() %> From : <%= report.getReporter() %> Reported : <%= report.getInsId() %></p>
<form action="reportDetail.jsp?Id=<%= report.getId()%>" method = "POST" name = "myform">
<p><Button>Details</Button></p>
</form>

<%}%>
</body>
</html>