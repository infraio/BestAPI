<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List" 
    import="java.util.Iterator" 
    import="java.util.HashSet"
    import="com.buaa.model.WebService"
    import="com.buaa.model.WebServiceAttribute"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	String user = (String) session.getAttribute("user");
%>
<%
	if(user == null || "".equals(user)) {
		request.setCharacterEncoding("utf-8");
%>
		<form action="login" method="post" onSubmit="return true">
			EMAIL&nbsp;&nbsp;&nbsp;: <input type="text" name="email"><br>
			PASSWORD: <input type="password" name="password"><br>
			<input type="submit" value="LOGIN">
			<input type="reset" value="RESET">
		</form>
<%
		List<String> info = (List<String>)request.getAttribute("info");
		if(info != null) {
			Iterator<String> iter = info.iterator();
			while(iter.hasNext()) {
%>
				<h4><%=iter.next()%></h4>
<%
			}
		}
	} else {
		HashSet<WebService> apis = (HashSet<WebService>)session.getAttribute("apis");
%>
		<h4>welcome back ! <%=session.getAttribute("username") %></h4>
		<h4>web service list :</h4>
<%
		for(WebService api : apis) {
%>
			<a href="update.jsp"><%=api.getAttributeContent(WebServiceAttribute.API_NAME) %></a><br>
<%
		}
%>
		<br><a href="submit.jsp">SUBMIT NEW WEB SERVICE</a><br>
<%
	}


%>
</body>
</html>