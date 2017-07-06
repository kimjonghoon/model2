<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/loginCheck.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="탈퇴" />
<meta name="Description" content="탈퇴" />
<title>탈퇴</title>
<link rel="stylesheet" href="../css/screen.css" type="text/css" />
<script type="text/javascript">
function check() {
    //var form = document.getElementById("byeForm");
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
		<div id="content" style="min-height: 800px;">
		
<div id="url-navi">회원</div>

<h2>탈퇴</h2>

<form id="byeForm" action="bye.do" method="post" onsubmit="return check()">
<table>
<tr>
	<td>이메일</td>
	<td><input type="text" name="email" /></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="passwd" /></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="확인" /></td>
</tr>
</table>
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