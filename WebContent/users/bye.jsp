<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/loginCheck.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Bye" />
<meta name="Description" content="Bye" />
<title>Bye</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
function check() {
	var form = document.getElementById("byeForm");
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
<h3>Bye</h3>
<form id="byeForm" action="bye.do" method="post" onsubmit="return check()">
<table>
<tr>
	<td>Email</td>
	<td><input type="text" name="email" /></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="passwd" /></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="Submit" /></td>
</tr>
</table>
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