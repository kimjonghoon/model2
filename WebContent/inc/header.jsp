<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1 style="float: left; width:150px;"><a href="/"><img src="/images/ci.gif" alt="" /></a></h1>
<div id="memberMenu" style="float: right;position: relative; top: 7px;">
<c:choose>
	<c:when test="${empty userInfo }">
		<input type="button" value="Login" onclick="location.href='/users/login.do'" />
		<input type="button" value="Sign Up" onclick="location.href='/users/signUp.do'" />
	</c:when>
	<c:otherwise>
		<input type="button" value="Logout" onclick="location.href='/users/logout.do'" />
		<input type="button" value="Edit Account" onclick="location.href='/users/editAccount.do'" />
	</c:otherwise>
</c:choose>	
</div>