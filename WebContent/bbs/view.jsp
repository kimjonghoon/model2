<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Detailed View" />
<meta name="Description" content="Detailed View" />
<title>BBS</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script type="text/javascript">
function modifyCommentToggle(articleNo) {
	var p_id = "comment" + articleNo;
	var p = document.getElementById(p_id);
	
	var form_id = "modifyCommentForm" + articleNo;
	var form = document.getElementById(form_id);

	var p_display;
	var form_display;
	
	if (p.style.display) {
		p_display = '';
		form_display = 'none';
	} else {
		p_display = 'none';
		form_display = '';
	}

	p.style.display = p_display;
	form.style.display = form_display;
}

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

function goModify() {
    var form = document.getElementById("modifyForm");
    form.submit();
}

function goDelete() {
    var check = confirm("Are you sure you want to delete it?");
    if (check) {
        var form = document.getElementById("delForm");
        form.submit();
    }
}

function deleteAttachFile(attachFileNo) {
    var check = confirm("Are you sure you want to delete it?");
    if (check) {
        var form = document.getElementById("deleteAttachFileForm");
        form.attachFileNo.value = attachFileNo;
        form.submit();
    }
}

function deleteComment(commentNo) {
    var check = confirm("Are you sure you want to delete it?");
    if (check) {
        var form = document.getElementById("deleteCommentForm");
        form.commentNo.value = commentNo;
        form.submit();
    }
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
<div class="view-menu" style="margin-bottom: 5px;">
    <c:if test="${user.email == email }">
    <div class="fl">
        <input type="button" value="Modify" onclick="goModify()" />
        <input type="button" value="Del" onclick="goDelete()" />
    </div>
    </c:if>        
    <div class="fr">
		<c:if test="${nextArticle != null }">    
        <input type="button" value="Next Article" onclick="goView('${nextArticle.articleNo }')" />
		</c:if>
		<c:if test="${prevArticle != null }">        
        <input type="button" value="Prev Article" onclick="goView('${prevArticle.articleNo}')" />
		</c:if>        
        <input type="button" value="List" onclick="goList('${param.page }')" />
        <input type="button" value="New" onclick="goWrite()" />
    </div>
</div>
<table class="bbs-table">
<tr>
    <th style="width: 37px;text-align: left;vertical-align: top;">TITLE</th>
    <th style="text-align: left;color: #555;">${title }</th>
</tr>
</table>
<div id="detail">
    <span id="date-writer-hit">edited ${regdate } by ${name } hit ${hit }</span>
    <p>${content }</p>
    <p id="file-list" style="text-align: right">
    	<c:forEach var="file" items="${attachFileList }" varStatus="status">
	    	<a href="${uploadPath }${file.filename }">${file.filename }</a>
			<c:if test="${user.email == file.email }">
	    	<a href="javascript:deleteAttachFile('${file.attachFileNo }')">x</a>
			</c:if>
			<br />    	
		</c:forEach>
    </p>
</div>
<form id="addCommentForm" action="addComment.do" method="post">
<p style="margin: 0;padding: 0">
	<input type="hidden" name="articleNo" value="${param.articleNo }" />
	<input type="hidden" name="boardCd" value="${param.boardCd }" />
	<input type="hidden" name="page" value="${param.page }" />
	<input type="hidden" name="searchWord" value="${param.searchWord }" />
</p>
   <div id="addComment">
       <textarea name="memo" rows="7" cols="50"></textarea>
   </div>
   <div style="text-align: right;">
       <input type="submit" value="Submit" />
   </div>
</form>
<!-- comments begin -->
<c:forEach var="comment" items="${commentList }" varStatus="status">
<div class="comments">
    <span class="writer">${comment.name }</span>
    <span class="date">${comment.regdate }</span>
	<c:if test="${user.email == comment.email }">    
    <span class="modify-del">
        <a href="javascript:modifyCommentToggle('${comment.commentNo }')">Modify</a>
         | <a href="javascript:deleteComment('${comment.commentNo }')">Del</a>
    </span>
	</c:if>    
    <p id="comment${comment.commentNo }" class="view-comment">${comment.memo }</p>
    <form id="modifyCommentForm${comment.commentNo }" class="comment-form" action="updateComment.do" method="post" style="display: none;">
        <input type="hidden" name="commentNo" value="${comment.commentNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
	    <div class="fr">
	            <a href="javascript:document.forms.modifyCommentForm${comment.commentNo }.submit()">Submit</a>
	            | <a href="javascript:modifyCommentToggle('${comment.commentNo }')">Cancel</a>
	    </div>
	    <div>
	        <textarea class="comment-textarea" name="memo" rows="7" cols="50">${comment.memo }</textarea>
	    </div>
    </form>
</div>
</c:forEach>
<!--  comments end -->
<div id="next-prev">
    <c:if test="${nextArticle != null }">
    <p>Next Article : <a href="javascript:goView('${nextArticle.articleNo }')">${nextArticle.title }</a></p>
    </c:if>
    <c:if test="${prevArticle != null }">
    <p>Prev Article : <a href="javascript:goView('${prevArticle.articleNo }')">${prevArticle.title }</a></p>
    </c:if>
</div>
<div class="view-menu">
    <c:if test="${user.email == email }">
    <div class="fl">
        <input type="button" value="Modify" onclick="goModify()" />
        <input type="button" value="Del" onclick="goDelete()" />
    </div>
    </c:if>        
    <div class="fr">
		<c:if test="${nextArticle != null }">    
        <input type="button" value="Next Article" onclick="goView('${nextArticle.articleNo }')" />
		</c:if>
		<c:if test="${prevArticle != null }">        
        <input type="button" value="Prev Article" onclick="goView('${prevArticle.articleNo}')" />
		</c:if>        
        <input type="button" value="List" onclick="goList('${param.page }')" />
        <input type="button" value="New" onclick="goWrite()" />
    </div>
</div>
<!-- BBS List in detailed Article -->
<table id="list-table" class="bbs-table">
<tr>
	<th style="width: 60px;">NO</th>
	<th>TITLE</th>
	<th style="width: 84px;">DATE</th>
	<th style="width: 60px;">HIT</th>
</tr>
<c:forEach var="article" items="${list }" varStatus="status">        
<tr>
	<td style="text-align: center;">
	<c:choose>
		<c:when test="${param.articleNo == article.articleNo }">	
		<img src="../images/arrow.gif" alt="Current Page" />
		</c:when>
		<c:otherwise>
		${listItemNo - status.index }
		</c:otherwise>
	</c:choose>	
	</td>
	<td>
		<a href="javascript:goView('${article.articleNo }')">${article.title }</a>
		<c:if test="${article.attachFileNum > 0 }">		
		<img src="../images/attach.png" alt="Attach File" />
		</c:if>
		<c:if test="${article.commentNum > 0 }">		
		<span class="bbs-strong">[${article.commentNum }]</span>
		</c:if>		
	</td>
	<td style="text-align: center;">${article.regdate }</td>
	<td style="text-align: center;">${article.hit }</td>
</tr>
</c:forEach>
</table>
<div id="paging">
	<c:if test="${prevPage > 0 }">
		<a href="javascript:golist('1')">1</a>
		<a href="javascript:golist('${prevPage }')">[Prev]</a>
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
		<a href="javascript:goList('${nextPage }')">[Next]</a>
		<a href="javascript:goList('${totalPage }')">[Last]</a>
	</c:if>
</div>
<div id="list-menu">
	<input type="button" value="New" onclick="goWrite()" />
</div>
<div id="search">
	<form action="list.do" method="get">
	<p style="margin: 0;padding: 0;">
		<input type="hidden" name="boardCd" value="${param.boardCd }" />
		<input type="hidden" name="page" value="1" />
		<input type="text" name="searchWord" size="15" maxlength="30" />
		<input type="submit" value="Search" />
	</p>
	</form>
</div>
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

<div id="form-group">
    <form id="listForm" action="list.do" method="get">
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>
    <form id="viewForm" action="view.do" method="get">
        <input type="hidden" name="articleNo" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>
    <form id="writeForm" action="write.do" method="get">
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>
    <form id="modifyForm" action="modify.do" method="get">
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>
    <form id="delForm" action="del.do" method="post">
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>
    <form id="deleteCommentForm" action="deleteComment.do" method="post">
        <input type="hidden" name="commentNo" />
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>   
    <form id="deleteAttachFileForm" action="deleteAttachFile.do" method="post">
        <input type="hidden" name="attachFileNo" />
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
    </form>       
</div>

</body>
</html>