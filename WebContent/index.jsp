<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.java_school.user.User"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="홈페이지" />
<meta name="Description" content="홈페이지" />
<title>홈페이지</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
</head>
<body>

	<div id="wrap">

		<div id="header">
			<%@ include file="./inc/header.jsp"%>
		</div>

		<div id="main-menu">
			<%@ include file="./inc/main-menu.jsp"%>
		</div>

		<div id="container">
			<div id="content">
				<!-- content begin -->
				<div id="content-categories">홈페이지</div>
				<!-- content end -->
			</div>
		</div>

		<div id="sidebar">
			<h1>Home</h1>
		</div>

		<div id="extra">
			<%@ include file="./inc/extra.jsp"%>
		</div>

		<div id="footer">
			<%@ include file="./inc/footer.jsp"%>
		</div>

	</div>

</body>
</html>