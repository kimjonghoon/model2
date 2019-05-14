<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Sign Up" />
<meta name="Description" content="Sign Up" />
<title>Sign Up</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
function check() {
	var form = document.getElementById("signUpForm");
	//name,passwd,confirm,email,mobile
	var name = form.name.value;
	name = name.trim();
	if (name.length == 0) {
		alert('Full Name is empty!');
		form.name.value = '';
		return false;
	}
	var passwd = form.passwd.value;
	passwd = passwd.trim();
	if (passwd.length == 0) {
		alert('Password is empty!');
		form.passwd.value = '';
		return false;
	}
	var confirm = form.confirm.value;
	confirm = confirm.trim();
	if (confirm.length == 0) {
		alert('Password confirm is empty!');
		form.confirm.value = '';
		return false;
	}
	var email = form.email.value;
	email = email.trim();
	if (email.length == 0) {
		alert('Email is empty!');
		form.email.value = '';
		return false;
	}
	var mobile = form.mobile.value;
	mobile = mobile.trim();
	if (mobile.length == 0) {
		alert('Mobile is empty!');
		form.mobile.value = '';
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
<h3>Sign Up</h3>
<form id="signUpForm" action="signUp.do" method="post" onsubmit="return check()">
<table>
<tr>
	<td style="width: 200px;">Full Name</td>
	<td style="width: 390px;"><input type="text" name="name" /></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="passwd" /></td>
</tr>
<tr>
	<td>Password Confirm</td>
	<td><input type="password" name="confirm" /></td>
</tr>
<tr>
	<td>Email</td>
	<td><input type="text" name="email" /></td>
</tr>
<tr>
	<td>Mobile</td>
	<td><input type="text" name="mobile" /></td>
</tr>
</table>
<div style="text-align: center;padding-bottom: 15px;">
	<input type="submit" value="Submit" />
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