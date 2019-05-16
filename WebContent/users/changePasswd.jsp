<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Change Password" />
<meta name="Description" content="Change Password" />
<title>Change Password</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
function check() {
	var form = document.getElementById("changePassworddForm");
	//currentPasswd,	newPasswd, confirm
	var currentPasswd = form.currentPasswd.value;
	currentPasswd = currentPasswd.trim();
	if (currentPasswd.length == 0) {
		alert('Current Password is empty!');
		form.currentPasswd.value = '';
		return false;
	}
	var newPasswd = form.newPasswd.value;
	newPasswd = newPasswd.trim();
	if (newPasswd.length == 0) {
		alert('New Password is empty!');
		form.newPasswd.value = '';
		return false;
	}
	var confirm = form.confirm.value;
	confirm = confirm.trim();
	if (confirm.length == 0) {
		alert('New Password confirm is empty!');
		form.confirm.value = '';
		return false;
	}
	if (newPasswd != confirm) {
		alert("[New Password] and [New Password Confirm] are not the same!");
		form.newPasswd.value = '';
		form.confirm.value = '';
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
<h3>Change Password</h3>
<p>
${userInfo.user.name }<br />
${userInfo.user.mobile }
</p>
<form id="changePassworddForm" action="changePasswd.do" method="post" onsubmit="return check()">
<table>
<tr>
	<td>Current Password</td>
	<td><input type="password" name="currentPasswd" /></td>
</tr>
<tr>
	<td>New Password</td>
	<td><input type="password" name="newPasswd" /></td>
</tr>
<tr>
	<td>New Password Confirm</td>
	<td><input type="password" name="confirm" /></td>
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