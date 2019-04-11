<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Confirm password change" />
<meta name="Description" content="Confirm password change" />
<title>Confirm password change</title>
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
<h3>Your password has been changed.</h3>
<p>
You can login with the changed password.<br />
<input type="button" value="Login" onclick="javascript:location.href='login.do'" />
</p>
<!-- content end -->		
		</div>
    </div>
    
    <div id="sidebar">
		<%@ include file="user-menu.jsp" %>
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