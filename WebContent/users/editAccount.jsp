<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/loginCheck.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="내 정보 수정" />
<meta name="Description" content="내 정보 수정" />
<title>내 정보 수정</title>
<link rel="stylesheet" href="../css/screen.css" type="text/css" />
<script type="text/javascript">
function check() {
    //var form = document.getElementById("editAccountForm");
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

<h2>내 정보 수정</h2>
<p>
비밀번호외의 자신의 계정 정보를 수정할 수 있습니다.<br />
비밀번호는 <a href="changePasswd.do">비밀번호 변경</a>메뉴를 이용하세요.<br />
</p>
<form id="editAccountForm" action="editAccount.do" method="post" onsubmit="return check()">
<table>
<tr>
	<td>이름</td>
	<td><input type="text" name="name" value="${user.name }" /></td>
</tr>
<tr>
	<td>손전화</td>
	<td><input type="text" name="mobile" value="${user.mobile }" /></td>
</tr>
<tr>
	<td>현재 비밀번호</td>
	<td><input type="password" name="passwd" /></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="전송" /></td>
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