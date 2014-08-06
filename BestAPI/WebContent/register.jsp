<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginServlet" method="post" onSubmit="return true">
		EMAIL&nbsp;&nbsp;&nbsp;: <input type="text" name="email"><br>
		USERNAME: <input type="text" name="username"><br>
		PASSWORD: <input type="password" name="password"><br>
		CONFIRM&nbsp;: <input type="password" name="confirm"><br>
		<input type="submit" value="REGISTER">
		<input type="reset" value="RESET">
	</form>
</body>
</html>