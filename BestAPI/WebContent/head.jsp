<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.buaa.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
</head>
<body>
	<div class="collapse navbar-collapse">
		<div class="container">
			<a class="navbar-brand" href="/BestAPI">BestAPI</a>
			<ul class="nav navbar-nav navbar-right">
				<%
					User user = (User) session.getAttribute("user");
					if (user == null) {
				%>
				<li><a href="login.jsp" class="login">登录</a></li>
				<li><a href="register.jsp" class="register">注册</a></li>
				<%
					} else {
				%>
				<li><a class="login"><%=user.getUsername() %>，你好</a></li>
				<%} %>
			</ul>
		</div>
	</div>
</body>
	