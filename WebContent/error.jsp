<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.java_school.user.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="Error" />
<meta name="Description" content="Error" />
<title>Error</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
</head>
<body>
<%
//Analyze the servlet exception
Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

if (servletName == null) {
  servletName = "Unknown";
}

String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

if (requestUri == null) {
  requestUri = "Unknown";
}
%>
<div id="wrap">

    <div id="header">
		<%@ include file="./inc/header.jsp" %>
    </div>
    
    <div id="main-menu">
		<%@ include file="./inc/main-menu.jsp" %>
    </div>
    
	<div id="container">
		<div id="content">
<!-- content begin -->		
<div id="content-categories">Error</div>
<h3>Error Page</h3>
<%
if(statusCode != 500){
    out.write("<h3>Error Details</h3>");
    out.write("<ul><li>Status Code</strong>:" + statusCode + "</li>");
    out.write("<li>Requested URI</strong>:"+requestUri + "</li></ul>");
}    
if (throwable != null) {
    out.write("<h3>Exception Details</h3>");
    out.write("<ul><li>Servlet Name:" + servletName + "</li>");
    out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
    out.write("<li>Requested URI:" + requestUri + "</li>");
    out.write("<li>Exception Message:" + throwable.getMessage() + "</li></ul>");
}
%>
<!-- content end -->		
		</div>
    </div>
    
	<div id="sidebar">
		<h1>Error</h1>
	</div>
    
	<div id="extra">
		<%@ include file="./inc/extra.jsp" %>    
	</div>
    
    <div id="footer">
		<%@ include file="./inc/footer.jsp" %>
    </div>
        
</div>

</body>
</html>