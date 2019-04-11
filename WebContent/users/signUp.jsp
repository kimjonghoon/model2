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
<form id="signUpForm" action="signUp.do" method="post">
<table>
<tr>
	<td style="width: 200px;">Full Name</td>
	<td style="width: 390px;"><input type="text" name="name" value="tester" /></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="passwd" value="1111" /></td>
</tr>
<tr>
	<td>Password Confirm</td>
	<td><input type="password" name="confirm" value="1111" /></td>
</tr>
<tr>
	<td>Email</td>
	<td><input type="text" name="email" value="tester@example.com" /></td>
</tr>
<tr>
	<td>Mobile</td>
	<td><input type="text" name="mobile" value="2222" /></td>
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