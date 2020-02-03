<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "controller.ReportController"
    import = "logic.Report"
    import = "java.util.List"%>
    <%ReportController rc = new ReportController();
    List<Report> reports = rc.getReport();
    %>
<!DOCTYPE html>
<html>
<head>
<title>Details</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="nostroSito">
<p align = right><button type = button onclick="history.back()">Back</button></p>
<%for(Report report : reports){
	if(report.getId() == Integer.parseInt(request.getParameter("Id"))){%>

<p>From : <%= report.getReporter() %></p>
<p>Description : <%= report.getDesc() %></p>

<form action ="ReportServlet?Id=<%= report.getId() %>&insId=<%= report.getInsId() %>" name = "myForm" method ="POST">
<input type = hidden id = "op" name = "op" value = "ban">
<Button>Delete insertion</Button>
</form>
<form action ="ReportServlet?Id=<%= report.getId() %>" name = "myForm" method ="POST">
<input type = hidden id = "op" name = "op" value = "ignore">
<button >No Action</button>
<%}} %>
</form>
</body>
</html>