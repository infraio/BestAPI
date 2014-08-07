<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List" 
    import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<String> info = (List<String>)request.getAttribute("info");
	if(info != null) {
		Iterator<String> iter = info.iterator();
		while(iter.hasNext()) {
			String s = iter.next();
%>
				<h4><%=s%></h4>
<%
		}
}
%>	
</body>
</html>