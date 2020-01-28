<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>new Insertion</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- linked CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<p align = center>New insertion</p>
<form action="NewInsertionAddServlet" name = "myform" method = "POST">

<p>Title : <input type = text name ="title" id = "title"> University :<input type = text name ="uni" id = "uni"></p>
<p>Price :<input type = text name ="price" id = "price"> City : <input type = text name ="city" id = "city"> Subject : <input type = text name ="subj" id = "subj"></p>
Description <textarea name ="desc" id = "desc" rows ="5" cols ="30">Description here</textarea>
<p align = center><input type ="radio" name = "book" id = "book" value="yes">Book <input type = "radio" name = "notes" id = "notes" value ="yes">Notes</p>
<p>Images : <input type = file name ="img1" id ="img1"><input type = file name ="img2" id ="img2"><input type = file name ="img3" id ="img3"></p>
<p><input type = submit value ="Insert"></p>
</form>
<button type = button onclick = "history.back()">Back</button>
</body>
</html>