<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="로그인" />
<meta name="Description" content="로그인" />
<title>로그인</title>
<link rel="stylesheet" href="../css/screen.css" type="text/css" />
<script type="text/javascript">
function check() {
	//var form = document.getElementById("loginForm");
	//TODO 유효성 검사 
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

<div id="url-navi">회원</div>

<h2>로그인</h2>

<c:if test="${not empty param.msg }">
	<p style="color: red;">로그인에 실패했습니다.</p>
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
    <td>비밀번호(Password)</td>
    <td><input type="password" name="passwd" style="width: 99%;" /></td>
</tr>
</table>
<div style="text-align: center;padding: 15px 0;">
    <input type="submit" value="확인" />
    <input type="button" value="회원가입" onclick="location.href='signUp.do'" />
</div>
</form>
		
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