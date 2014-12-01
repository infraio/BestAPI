<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.buaa.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="http://static.hihocoder.com/styles/home.css?1406796137.css">
<title>BestAPI</title>
</head>
<body>
<div id="wrap">
	<jsp:include page="head.jsp"></jsp:include>
	
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
	
	<div  style="width:100px;height:350px;overflow-x:hidden;overflow-y:hidden;"></div>
	<jsp:include page="footer.html"></jsp:include>
</div>
</body>
</html>