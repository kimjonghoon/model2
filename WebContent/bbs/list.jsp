<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="게시판 목록" />
<meta name="Description" content="게시판 목록" />
<title>BBS</title>
<link rel="stylesheet" href="../css/screen.css" type="text/css" />
<script type="text/javascript">
function goList(page) {
    var form = document.getElementById("listForm");
    form.page.value = page;
    form.submit();
}
function goView(articleNo) {
    var form = document.getElementById("viewForm");
    form.articleNo.value = articleNo;
    form.submit();
}
function goWrite() {
    var form = document.getElementById("writeForm");
    form.submit();
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
			
<div id="url-navi">BBS</div>

<h2>${boardNm }</h2>

<table class="bbs-table">
<tr>
	<th style="width: 60px;">NO</th>
	<th>TITLE</th>
	<th style="width: 84px;">DATE</th>
	<th style="width: 60px;">HIT</th>
</tr>
<!--  bbs list begin -->
<c:forEach var="article" items="${list }" varStatus="status">	
<tr>
	<td style="text-align: center;">${listItemNo - status.index }</td>
	<td>
		<a href="javascript:goView('${article.articleNo }')">${article.title }</a>
		<c:if test="${article.attachFileNum > 0 }">
		<img src="../images/attach.png" alt="첨부파일" />
		</c:if>
		<c:if test="${article.commentNum > 0 }">
		<span class="bbs-strong">[${article.commentNum }]</span>
		</c:if>
	</td>
	<td style="text-align: center;">${article.regdate }</td>
	<td style="text-align: center;">${article.hit }</td>
</tr>
</c:forEach>
<!--  bbs list end -->
</table>
               
<div id="paging">

	<c:if test="${prevPage > 0 }">
		<a href="javascript:goList('${prevPage }')">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${firstPage }" end="${lastPage }">
		<c:choose>
			<c:when test="${param.page == i }">
			<span class="bbs-strong">${i }</span>
			</c:when>
			<c:otherwise>
			<a href="javascript:goList('${i }')">${i }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${nextPage > 0 }">
		<a href="javascript:goList('${nextPage }')">[다음]</a>
	</c:if>
</div>

<div id="list-menu">
	<input type="button" value="새글쓰기" onclick="goWrite()" />
</div>

<div id="search">
	<form action="list.do" method="get">
	<div>
		<input type="hidden" name="boardCd" value="${param.boardCd }" />
		<input type="hidden" name="page" value="1" />
		<input type="text" name="searchWord" size="15" maxlength="30" />
		<input type="submit" value="검색" />
	</div>
	</form>
</div>

		</div><!-- content end -->
	</div><!-- container end -->
   
	<div id="sidebar">
		<%@ include file="bbs-menu.jsp" %>
	</div>
	   
	<div id="extra">
		<%@ include file="../inc/extra.jsp" %>
	</div>
	    
	<div id="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>

</div><!-- wrap end -->

<div id="form-group" style="display: none">
    <form id="listForm" action="list.do" method="get">
    <p>
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </p>
    </form>
    <form id="viewForm" action="view.do" method="get">
    <p>
        <input type="hidden" name="articleNo" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </p>
    </form>
    <form id="writeForm" action="write.do" method="get">
    <p>
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </p>
    </form>
</div>

</body>
</html>