<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList" 
    import="java.util.Iterator"
    import="com.buaa.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/table.css">
<title>Search Result</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="view-result">
	<h4>搜索结果</h4>
	<%
	ArrayList<String> thead = (ArrayList<String>) session.getAttribute("thead");
	ArrayList<ArrayList<String>> tbody = (ArrayList<ArrayList<String>>) session.getAttribute("tbody");
	if (thead == null || thead.size() == 0) {
	%>
	<h4>没有找到相关的Web服务</h4>
	<%} else { %>
	<table class="bordered">
		<thead>
			<tr>
				<th><%=thead.get(0) %></th>
				<th><%=thead.get(1) %></th>
				<th><%=thead.get(2) %></th>
				<th><%=thead.get(3) %></th>
				<th><%=thead.get(4) %></th>
			</tr>
		</thead>
		<tbody>
		<%
		for (ArrayList<String> tr : tbody) {
		%>
			<tr>
				<td><%=tr.get(0) %></td>
				<td><%=tr.get(1) %></td>
				<td><a href="tree.jsp?domain=<%=tr.get(2) %>"><%=tr.get(2) %></a></td>
				<td><%=tr.get(3) %></td>
				<th><%=tr.get(4) %></th>
			</tr>
		<% } %>
		</tbody>
	</table>
	<%} %>
	</div>
	<div  style="width:100px;height:100px;overflow-x:hidden;overflow-y:hidden;"></div>	
	<jsp:include page="footer.html"></jsp:include>
</body>
</html>