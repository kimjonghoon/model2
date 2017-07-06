<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="비밀번호 변경 확인" />
<meta name="Description" content="비밀번호 변경 확인" />
<title>비밀번호 변경 확인</title>
<link rel="stylesheet" href="../css/screen.css" type="text/css" />
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

<h2>비밀번호가 변경되었습니다.</h2>

변경된 비밀번호로 다시 로그인하실 수 있습니다.<br />
<input type="button" value="로그인" onclick="javascript:location.href='login.do'" />
		
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