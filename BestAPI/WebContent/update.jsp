<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.buaa.model.WebService"
    import="com.buaa.model.DataSource"
    import="com.buaa.dao.WebServiceDAOFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add an API</title>
<link type="text/css" rel="stylesheet" href="css/main_ori.css">
<link href="http://static.hihocoder.com/images/hiho.ico" rel="shortcut icon" type="image/x-icon">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="container single">
	<div class="submit box">
	<div class="form">
		<div class="form-heading">
			<h3>Update your API</h3>
		</div>
		<%
			String apiname = request.getParameter("apiname");
			WebService ws = WebServiceDAOFactory.getWebServiceDAOInstance(DataSource.MYSQL).getWebServiceByName(apiname);
		%>
		<form name="submit" action="update" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="title" class="col-md-3 col-sm-3 control-label ">API Name</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="name" value="<%=ws.getName() %>" readonly="readonly" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="author" class="col-md-3 col-sm-3 control-label ">Owner Name</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="owner" value="<%=ws.getOwner() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-md-3 col-sm-3 control-label ">API Endpoint</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="endpoint" value="<%=ws.getEndpoint() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-md-3 col-sm-3 control-label ">API Homepage</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="homepage" value="<%=ws.getHomepage() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-md-3 col-sm-3 control-label with-note">Contact Email</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="email" value="<%=ws.getContactEmail() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="title" class="col-md-3 col-sm-3 control-label ">Category</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="category" value="<%=ws.getCategory() %>" required="" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-md-3 col-sm-3 control-label ">Protocol Formats</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="protocol" value="<%=ws.getProtocolFormats() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="link" class="col-md-3 col-sm-3 control-label with-note">API Hub URL</label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="huburl" value="<%=ws.getHubUrl() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-md-3 col-sm-3 control-label with-note">Authentication Mode</span></label>
				<div class="col-md-7 col-sm-8">
					<input type="text" name="authentication" value="<%=ws.getAuthenticationMode() %>" required="" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-7 col-sm-offset-3 col-sm-8">
					<button type="submit" class="btn btn-blue pull-right">Submit for Approval</button>
				</div>
			</div>
		</form>
	</div>
	</div>
	</div>
	<jsp:include page="footer.html"></jsp:include>
</body>
</html>