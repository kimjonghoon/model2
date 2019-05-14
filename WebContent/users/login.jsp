<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Login" />
<meta name="Description" content="Login" />
<title>Login</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
function check() {
	var form = document.getElementById("loginForm");
	var email = form.email.value;
	email = email.trim();
	if (email.length == 0) {
		alert('Email is empty!');
		form.email.value = '';
		return false;
	}
	var passwd = form.passwd.value;
	passwd = passwd.trim();
	if (passwd.length == 0) {
		alert('Password is empty!');
		form.passwd.value = '';
		return false;
	}
	return true;
}
</script>
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
<h3>Login</h3>
<c:if test="${not empty param.msg }">
	<p style="color: red;">Login failed</p>
</c:if>
<form id="loginForm" action="login.do" method="post" onsubmit="return check()">
<p style="margin: 0; padding: 0;">
<input type="hidden" name="url" value="${param.url }" />
</p>
<table>
<tr>
    <td style="width: 200px;">Email</td>
    <td style="width: 390px"><input type="text" name="email" style="width: 99%;" /></td>
</tr>
<tr>
    <td>Password</td>
    <td><input type="password" name="passwd" style="width: 99%;" /></td>
</tr>
</table>
<div style="text-align: center;padding: 15px 0;">
    <input type="submit" value="Submit" />
    <input type="button" value="Sign Up" onclick="location.href='signUp.do'" />
</div>
</form>
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