<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="register" method="post" onSubmit="return true">
		EMAIL&nbsp;&nbsp;&nbsp;: <input type="text" name="email"><br>
		USERNAME: <input type="text" name="username"><br>
		PASSWORD: <input type="password" name="password"><br>
		CONFIRM&nbsp;: <input type="password" name="confirm"><br>
		<input type="submit" value="REGISTER"> <input type="reset"
			value="RESET">
	</form>

	<div class="register">
		<h2>注册</h2>
		<div class="row">
			<main id="main" class="col-xs-5">
			<form class="form" action="/api/User/register.json" method="post"
				onsubmit="$(this).ajaxsubmit(Callbacks.redirect('url'), Callbacks.errorAlert);return false;">
				<div class="form-group">
					<label class="sr-only">邮箱地址</label> <input name="email"
						class="form-control input-lg" placeholder="邮箱地址" type="email">
				</div>
				<button type="submit" class="btn btn-primary btn-lg">注册</button>
			</form>
			</main>

			<aside id="side" class="col-xs-5">
			<p>
				推荐您通过以下网站账户<a href="/login">直接登录</a>，无需注册
			</p>
			<a href="/login" class="auth-login"> <span class="google"></span>
				<!-- <span class="sinaweibo"></span>  --> <span class="git"></span>
				<!-- <span class="renren"></span>  --> <span class="qq"></span>
			</a> </aside>

		</div>
	</div>
</body>
</html>