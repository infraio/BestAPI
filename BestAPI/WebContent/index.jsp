<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse">
		<div class="container">
			<a class="navbar-brand" href="/BestAPI">BestAPI</a>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login.jsp" class="login">登录</a></li>
				<li><a href="register.jsp" class="register">注册</a></li>
			</ul>
		</div>
	</div><!-- /.navbar-collapse -->
	
	<div class="container">
		<div class="site-intro-register">
			<form action="index" method="post" onSubmit="return true">
				<div class="input-group fn-auto">
					<input name="keyword" class="form-control input-lg" placeholder="api name" type="text">
			        <span class="input-group-btn">
			        	<button class="btn btn-success" type="submit">搜索</button>
			      	</span>
		      	</div>
	      	</form>
		</div>
	</div>
	
	
	<div  style="width:100px;height:250px;overflow-x:hidden;overflow-y:hidden;"></div>
	
	<div class="container tl-footer-lg">
		<div class="row">
			<div class="col-xs-8">
				<div class="row">
					<div class="col-xs-3">
						<h4>关于我们</h4>
						<ul>
							<li><a href="/about-us#website-desc">网站介绍</a></li>
							<li><a href="/about-us#team-desc">团队介绍</a></li>
							<li><a href="/about-us#connect-us">联系方式</a></li>
						</ul>
					</div>
					<div class="col-xs-3">
						<h4>帮助中心</h4>
						<ul>
							<li><a href="/BestAPI">Web服务</a></li>
							<li><a href="/BestAPI">推荐系统</a></li>
							<li><a href="/BestAPI">评价系统</a></li>
						</ul>
					</div>
					<div class="col-xs-3">
						<h4>友情链接</h4>
						<ul>
							<li><a href="https://www.github.com" target="_blank">Github</a></li>
							<li><a href="http://www.google.com" target="_blank">Google</a></li>
							<li><a href="http://www.baidu.com" target="_blank">Baidu</a></li>
						</ul>
					</div>
					<div class="col-xs-3">
						<h4>关注我们</h4>
						<ul>
							<li><a href="http://weibo.com/u/1276885594" target="_blank" title="hihoCoder新浪微博" class="connect-icon sina-weibo">新浪微博</a></li>
							<li><a href="https://github.com/G308/BestAPI" target="_blank">fork in github</a></li>
						</ul>
					</div>			
				</div>
			</div>
			<div class="col-xs-4 side">
				<h4>联系我们</h4>
				<address>
					<p>北京市海淀区北京航空航天大学新主楼G308</p>
					<a href="mailto:#">zghaobac@qq.com</a>
				</address>
			</div>
		</div>
	</div>
	
	<div class="tl-footer-sm">
		<p>© 2014 BestAPI</p>
	</div>

</div>
</body>
</html>