<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>BBS</h1>
<ul>
<c:forEach var="board" items="${boards }" varStatus="status">
	<li><a href="list.do?boardCd=${board.boardCd }&page=1">${board.boardNm }</a></li>
</c:forEach>	
</ul>