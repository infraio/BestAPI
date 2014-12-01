<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
</head>
<body>
	<div class="collapse navbar-collapse">
		<div class="container">
			<a class="navbar-brand" href="/BestAPI">BestAPI</a>
		</div>
	</div>
	<div id="content" class="container">
		<div class="register">
			<h2>注册</h2>
			<div class="row">
				<main id="main" class="col-xs-5">
				<form class="form" action="register" method="post"
					onsubmit="return true">
					<div class="form-group">
						<label class="sr-only">邮箱地址</label> <input name="email"
							class="form-control input-lg" placeholder="邮箱地址" type="email">
					</div>
					<div class="form-group">
						<label class="sr-only">用户名</label> <input name="username"
							class="form-control input-lg" placeholder="用户名" type="text">
					</div>
					<div class="form-group">
						<label class="sr-only">密码</label> <input name="password"
							class="form-control input-lg" placeholder="密码" type="text">
					</div>
					<div class="form-group">
						<label class="sr-only">确认密码</label> <input name="confirm"
							class="form-control input-lg" placeholder="确认密码" type="text">
					</div>
					<button type="submit" class="btn btn-primary btn-lg">注册</button>
				</form>
				<%
				String info = (String) request.getAttribute("info");
				if (info != null && info != "") {
				%>
				<div>
					<h4>错误提示：<%=info %></h4>
				</div>
				<%} %>
				</main>
			</div>
		</div>
	</div>
	<div  style="width:100px;height:100px;overflow-x:hidden;overflow-y:hidden;"></div>
	<jsp:include page="footer.html"></jsp:include>
</body>
</html>