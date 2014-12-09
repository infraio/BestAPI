<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"
	import="java.util.Iterator" import="java.util.HashSet"
	import="com.buaa.model.WebService"
	import="com.buaa.model.WebServiceAttribute"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div id="content" class="container">
		<div class="login">
			<h2>登录</h2>
			<p class="auth-des">您可以通过第三方帐号登录，无需注册</p>
			<div class="auth-login">
				<a href="javascript:api.githubLogin(Callbacks.redirect('url'));"
					class="btn btn-default btn-lg"><span class="git"></span>Github</a> <a
					href="javascript:api.googleLogin(Callbacks.redirect('url'));"
					class="btn btn-default btn-lg"><span class="google"></span>Google</a>
				<a href="javascript:api.sinaWeiBoLogin(Callbacks.redirect('url'));"
					class="btn btn-default btn-lg"><span class="sinaweibo"></span>新浪微博</a>
				<a href="javascript:api.QQLogin(Callbacks.redirect('url'));"
					class="btn btn-default btn-lg"><span class="qq"></span>腾讯QQ</a> <a
					href="javascript:api.liveLogin(Callbacks.redirect('url'));"
					class="btn btn-default btn-lg"><span class="live"></span>Live ID</a>
			</div>
			<p class="login-des">或者使用本站帐号登录</p>
			<form class="form-inline" action="login" method="post"
				onsubmit="return true;">
				<div class="form-group">
					<label class="sr-only">邮箱地址</label> <input name="email"
						class="form-control input-lg" placeholder="邮箱地址" type="email">
				</div>
				<div class="form-group">
					<label class="sr-only">登录密码</label> <input name="password"
						class="form-control input-lg" placeholder="登录密码" type="password">
				</div>
				<button type="submit" class="btn btn-primary btn-lg">登录</button>
			</form>
			<%
				String info = (String) request.getAttribute("info");
				if (info != null && info != "") {
			%>
			<div>
				<h4>错误提示：<%=info %></h4>
			</div>
			<%} %>
		</div>
	</div>
	
	<div  style="width:100px;height:100px;overflow-x:hidden;overflow-y:hidden;"></div>	
	<jsp:include page="footer.html"></jsp:include>
</body>
</html>