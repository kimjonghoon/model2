<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Board Write" />
<meta name="Description" content="Board Write" />
<title>BBS</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script type="text/javascript">
function check() {
    //var form = document.getElementById("writeForm");
    //TODO 
    return true;
}
function goList() {
    var form = document.getElementById("listForm");
    form.submit();
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
<h3>New</h3>
<form id="writeForm" action="write.do" method="post" enctype="multipart/form-data" onsubmit="return check();">
<input type="hidden" name="articleNo" value="${param.articleNo }" />
<input type="hidden" name="boardCd" value="${param.boardCd }" />
<input type="hidden" name="page" value="${param.page }" />
<input type="hidden" name="searchWord" value="${param.searchWord }" />
<table id="write-form" class="bbs-table">
<tr>
    <td>Title</td>
    <td><input type="text" name="title" style="width: 90%"/></td>
</tr>
<tr>
    <td colspan="2">
        <textarea name="content" rows="17" cols="50"></textarea>
    </td>
</tr>
<tr>
    <td>Attach File</td>
    <td><input type="file" name="attachFile" /></td>
</tr>
</table>
<div style="text-align: center;padding-bottom: 15px;">
    <input type="submit" value="Submit" />
    <input type="button" value="List" onclick="goList()" />
	<c:if test="${not empty param.articleNo }">    
    <input type="button" value="Detailed View" onclick="goView()" />
	</c:if>
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
    <form id="listForm" action="list.do" method="get">
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>   
</div>

</body>
</html>