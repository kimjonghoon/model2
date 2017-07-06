<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${empty user }">
		<ul>
			<li><a href="logout.do">로그아웃</a></li>
			<li><a href="editAccount.do">내정보수정</a></li>
			<li><a href="changePasswd.do">비밀번호 변경</a></li>
			<li><a href="bye.do">탈퇴</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul>
			<li><a href="login.do">로그인</a></li>
			<li><a href="signUp.do">회원가입</a></li>
			<li><a href="#">ID 찾기</a></li>
			<li><a href="#">비밀번호 찾기</a></li>
		</ul>
	</c:otherwise>
</c:choose>
