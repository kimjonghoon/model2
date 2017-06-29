<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="회원가입 환영" />
<meta name="Description" content="회원가입 환영" />
<title>회원가입이 완료되었습니다.</title>
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
		
<!-- 본문 시작 -->
<div id="url-navi">회원</div>
<h1>환영합니다.</h1>
회원가입시 입력한 Email이 아이디로 사용됩니다.<br />
<input type="button" value="로그인" onclick="javascript:location.href='login.do'" />
<!-- 본문 끝 -->
		
		</div>
    </div>
    
    <div id="sidebar">
		<h1>Welcome</h1>
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