<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="welcome" />
<meta name="Description" content="welcome" />
<title>welcome</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
</head>
<body>

<div id="wrap">

    <div id="header">
		<%@ include file="../inc/header.jsp" %>
    </div>
    
    <div id="main-menu">
 		<%@ include file="../inc/main-menu.jsp" %>
    </div>
    
	<div id="container">
		<div id="content">
<!-- content begin -->
<div id="content-categories">Membership</div>
<h3>Welcome</h3>
<p>
<input type="button" value="Login" onclick="javascript:location.href='login.do'" />
</p>
<!-- content end -->
		</div>
    </div>
    
    <div id="sidebar">
		<h1>Welcome</h1>
    </div>
    
    <div id="extra">
		<%@ include file="../inc/extra.jsp" %>
    </div>
    
    <div id="footer">
		<%@ include file="../inc/footer.jsp" %>
    </div>
        
</div>

</body>
</html>