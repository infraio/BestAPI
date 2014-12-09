<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"
	 import="java.util.ArrayList" 
    import="java.util.Iterator"
    import="com.buaa.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/table.css">
<link type="text/css" rel="stylesheet" href="css/candy.css">
<title>User Profile</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="add-button" style="width:300px; height:150px; margin-left: auto;margin-right: auto;">
		<div style="text-align:center;height:70px;padding-top:50px;">
			<a href="submit.jsp" data-icon="+" class="button blue brackets">Add Your API</a>  
		</div>
	</div>
	<div class="view-result">
	<h4>APIs Provided</h4>
		<table class="bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Owner</th>
					<th>Homepage</th>
					<th>Contact Email</th>
					<th>Protocol Formats</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
		</table>
	</div>
	<div class="view-result">
	<h4>APIs Consumed</h4>
		<table class="bordered">
			<thead>
				<tr>
					<th>Name</th>
					<th>Owner</th>
					<th>Homepage</th>
					<th>Contact Email</th>
					<th>Protocol Formats</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
		</table>
	</div>
	<div  style="width:100px;height:100px;overflow-x:hidden;overflow-y:hidden;"></div>	
	<jsp:include page="footer.html"></jsp:include>
</body>
</html>