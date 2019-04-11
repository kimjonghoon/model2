<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Change password" />
<meta name="Description" content="Change Password" />
<title>Change Password</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
function check() {
    var form = document.getElementById("changePassworddForm");
    if (form.passwd.value == form.confirm.value) {
    	return true;
    } else {
    	alert("[New Password] and [New Password Confirm] values are not the same!");
    	return false;
    }
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
<div id="content-categories">Member</div>
<h3>Change Password</h3>
<p>
${user.name }<br />
${user.mobile }
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