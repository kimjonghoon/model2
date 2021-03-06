<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul id="nav">
	<li><a href="/java/Features.jsp">Java</a></li>
	<li><a href="/jdbc">JDBC</a></li>
	<li><a href="/jsp">JSP</a></li>
	<li><a href="/css-layout">CSS Layout</a></li>
	<li><a href="/jsp-pjt">JSP Project</a></li>
	<li><a href="/spring">Spring</a></li>
	<li><a href="/javascript">JavaScript</a></li>
	<li><a href="/bbs/list.do?boardCd=chat&amp;page=1">BBS</a></li>
	<c:if test="${userInfo.admin }">
		<li><a href="/admin/list.do?page=1">Admin</a></li>
	</c:if>
</ul>