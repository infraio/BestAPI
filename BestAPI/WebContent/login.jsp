<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"
	import="java.util.Iterator" import="java.util.HashSet"
	import="com.buaa.model.WebService"
	import="com.buaa.model.WebServiceAttribute"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String user = (String) session.getAttribute("user");
	%>
	<%
		if (user == null || "".equals(user)) {
			request.setCharacterEncoding("utf-8");
	%>
	<form action="login" method="post" onSubmit="return true">
		EMAIL&nbsp;&nbsp;&nbsp;: <input type="text" name="email"><br>
		PASSWORD: <input type="password" name="password"><br> <input
			type="submit" value="LOGIN"> <input type="reset"
			value="RESET">
	</form>


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
		<form class="form-inline" action="/api/User/login.json" method="post"
			onsubmit="$(this).ajaxsubmit(Callbacks.redirect('url'), Callbacks.errorAlert);return false;">
			<div class="form-group">
				<label class="sr-only">邮箱地址</label> <input name="email"
					class="form-control input-lg" placeholder="邮箱地址" type="email">
			</div>
			<div class="form-group">
				<label class="sr-only">登录密码</label> <input name="passwd"
					class="form-control input-lg" placeholder="登录密码" type="password">
			</div>
			<button type="submit" class="btn btn-primary btn-lg">登录</button>
		</form>
	</div>
	<%
		List<String> info = (List<String>) request.getAttribute("info");
			if (info != null) {
				Iterator<String> iter = info.iterator();
				while (iter.hasNext()) {
	%>
	<h4><%=iter.next()%></h4>
	<%
		}
			}
		} else {
			HashSet<WebService> apis = (HashSet<WebService>) session
					.getAttribute("apis");
	%>
	<h4>
		welcome back !
		<%=session.getAttribute("username")%></h4>
	<h4>web service list :</h4>
	<%
		for (WebService api : apis) {
	%>
	<a href="update.jsp"><%=api
							.getAttributeContent(WebServiceAttribute.API_NAME)%></a>
	<br>
	<%
		}
	%>
	<br>
	<a href="submit.jsp">SUBMIT NEW WEB SERVICE</a>
	<br>
	<%
		}
	%>
</body>
</html>