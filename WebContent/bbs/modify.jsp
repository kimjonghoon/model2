<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="게시판 수정하기 폼" />
<meta name="Description" content="게시판 수정하기 폼" />
<title>BBS</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script type="text/javascript">
function check() {
    //var form = document.getElementById("modifyForm");
    //TODO 유효성 검사 
    return true;
}
function goView() {
    var form = document.getElementById("viewForm");
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
		<div id="content">
<!-- content begin -->
<div id="content-categories">${boardNm }</div>
<h3>수정</h3>
<form id="modifyForm" action="modify.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
<p style="margin: 0;padding: 0;">
<input type="hidden" name="articleNo" value="${param.articleNo }" />
<input type="hidden" name="boardCd" value="${param.boardCd }" />
<input type="hidden" name="page" value="${param.page }" />
<input type="hidden" name="searchWord" value="${param.searchWord }" />
</p>
<table id="write-form" class="bbs-table">
<tr>
    <td>제목</td>
    <td><input type="text" name="title" style="width: 90%" value="${title }" /></td>
</tr>
<tr>
    <td colspan="2">
        <textarea name="content" rows="17" cols="50">${content }</textarea>
    </td>
</tr>
<tr>
    <td>파일첨부</td>
    <td><input type="file" name="attachFile" /></td>
</tr>
</table>
<div style="text-align: center;padding-bottom: 15px;">
    <input type="submit" value="전송" />
    <input type="button" value="상세보기" onclick="goView()" />
</div>
</form>
<!-- content end -->		
		</div>
    </div>
    
	<div id="sidebar">
		<%@ include file="bbs-menu.jsp" %>	
	</div>
    
	<div id="extra">
		<%@ include file="../inc/extra.jsp" %>
	</div>
    
	<div id="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
        
</div>

<div id="form-group" style="display: none">
    <form id="viewForm" action="view.do" method="get">
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>
</div>

</body>
</html>