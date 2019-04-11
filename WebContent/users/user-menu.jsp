<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Membership</h1>

<c:choose>
	<c:when test="${empty user }">
		<ul>
			<li><a href="login.do">Login</a></li>
			<li><a href="signUp.do">SignUp</a></li>
			<li><a href="#">Forgot ID?</a></li>
			<li><a href="#">Forgot Password?</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul>
			<li><a href="logout.do">Logout</a></li>
			<li><a href="editAccount.do">Edit Account</a></li>
			<li><a href="changePasswd.do">Change Password</a></li>
			<li><a href="bye.do">Bye</a></li>
		</ul>
	</c:otherwise>
</c:choose>
