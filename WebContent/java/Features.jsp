<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.java_school.user.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Java Home" />
<meta name="Description" content="Java Home" />
<title>Java Home</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
</head>
<body>

	<div id="wrap">

		<div id="header">
			<%@ include file="../inc/header.jsp"%>
		</div>

		<div id="main-menu">
			<%@ include file="../inc/main-menu.jsp"%>
		</div>

		<div id="container">
			<div id="content">
<!-- content begin -->
<div id="content-categories">Java</div>
<article>

<h1>자바의 특징</h1>

<h2>플랫폼 독립적</h2>

<p>
자바 컴파일러는 자바 소스 파일(.java)를 컴파일해서 자바 클래스 파일(.class)을 만든다.
자바 클래스 파일은 자바 실행 환경(JRE)를 가진 모든 플랫폼에서 동일하게 실행된다.
자바 클래스 파일(.class)은, 자바 인터프리터가 바이트 단위로 해석하므로, 자바 바이트코드라 부른다.
</p>

<dl class="note">
<dt>Java Runtime Environment (JRE)</dt>
<dd>
JRE는 자바 프로그램을 실행하기 위해 필요한 모든 것을 뜻한다.
JRE는 JVM(Java Virtual Machine)과 자바 API와 JVM에서 자바 파일을 실행할 때 도움을 주는 파일로 구성된다.
사실상 JVM이 자바 바이트코드를 실행한다.
</dd>
<dt>Java Development Kit (JDK)</dt>
<dd>
JDK는 JRE와 자바 프로그램 개발에 필요한 도구 프로그램으로 구성된다.
</dd>
</dl>

<h2>객체 지향 프로그래밍 언어</h2>

<p>
자바는 객체 지향 프로그래밍 언어다.
자바는 객체 지향 프로그래밍의 다음 기본 개념을 지원한다.
</p>

<ul>
	<li>객체</li>
	<li>캡슐화</li>
	<li>상속</li>
	<li>다형성</li>
</ul>
</article>
<div id="next-prev">
	<ul>
		<li>Next : <a href="/java/Class-Object">객체와 클래스</a></li>
		<li>Prev : <a href="/">자바 설치</a></li>
	</ul>
</div>				
<!-- content end -->
			</div>
		</div>

		<div id="sidebar">
			<%@ include file="menu.jsp" %>
		</div>

		<div id="extra">
			<%@ include file="../inc/extra.jsp"%>
		</div>

		<div id="footer">
			<%@ include file="../inc/footer.jsp"%>
		</div>

	</div>

</body>
</html>