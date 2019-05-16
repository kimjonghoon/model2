<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/loginCheck.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Edit Account" />
<meta name="Description" content="Edit Account" />
<title>Edit Account</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
function check() {
	var form = document.getElementById("editAccountForm");
	var name = form.name.value;
	name = name.trim();
	if (name.length == 0) {
		alert('Name is empty!');
		form.name.value = '';
		return false;
	}
	var mobile = form.mobile.value;
	mobile = mobile.trim();
	if (mobile.length == 0) {
		alert('Mobile is empty!');
		form.mobile.value = '';
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
<h3>Edit Account</h3>
<p>
You can edit all your information except your password in this page.<br />
Visit <a href="changePasswd.do">Change Password</a> for changing password.<br />
</p>
<form id="editAccountForm" action="editAccount.do" method="post" onsubmit="return check()">
<table>
<tr>
	<td>Full Name</td>
	<td><input type="text" name="name" value="${userInfo.user.name }" /></td>
</tr>
<tr>
	<td>Mobile</td>
	<td><input type="text" name="mobile" value="${userInfo.user.mobile }" /></td>
</tr>
<tr>
	<td>Current Password</td>
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