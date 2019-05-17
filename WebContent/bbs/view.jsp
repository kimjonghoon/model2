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
<script>
window.onload = initPage;

function initPage() {
	var file_list = document.getElementById("file-list");
	var fileLinks = file_list.getElementsByTagName("a");
  
	for (var i = 0; i < fileLinks.length; i++) {
		var fileLink = fileLinks[i];
		if (fileLink.className != "download") {
			fileLink.onclick = function() {
				var attachFileNo = this.title;
				var chk = confirm("Are you sure you want to delete it?");
				if (chk === true) {
					var form = document.getElementById("deleteAttachFileForm");
					form.attachFileNo.value = attachFileNo;
					form.submit();
					return false;
				}
			};
		}
	}

  function commentUpdate(e) {
	  var me = getActivatedObject(e);
	  var form = me.parentNode;
	  while (form.className != "comment-form") {
	    form = form.parentNode;
	  }
	  form.submit();
	  return false;
	}
	function modifyCommentToggle(e) {
	  var me = getActivatedObject(e);
	  var comments = me.parentNode;
	  while (comments.className != "comments") {
	    comments = comments.parentNode;
	  }
	  var div = comments.getElementsByTagName("div")[0];
	  var form = comments.getElementsByTagName("form")[0];
	  if (div.style.display) {
	    div.style.display = '';
	    form.style.display = 'none';
	  } else {
	    div.style.display = 'none';
	    form.style.display = '';
	  }
	  return false; 
	}

	function getActivatedObject(e) {
	  var obj;
	  if (!e) {
	    obj = window.event.srcElement;
	  } else if (e.srcElement) {
	    obj = e.srcElement;
	  } else {
	    obj = e.target;
	  }
	  return obj;
	}  

	var allComments = document.getElementById("all-comments");
	var divs = allComments.getElementsByTagName("div");

	for (i = 0; i < divs.length; i++) {
	  if (divs[i].className == "comments") {
	    var comments = divs[i];
	    var spans = comments.getElementsByTagName("span");
	    for (var j = 0; j < spans.length; j++) {
	      if (spans[j].className === "modify-del") {
	        var md = spans[j];
	        var commentModifyLink = md.getElementsByTagName("a")[0];
	        commentModifyLink.onclick = modifyCommentToggle;
	        var commentDelLink = md.getElementsByTagName("a")[1];
	        commentDelLink.onclick = function() {
	          var commentNo = this.title;
	          var chk = confirm("Are you sure you want to delete it?");
	          if (chk === true) {
	            var form = document.getElementById("deleteCommentForm");
	            form.commentNo.value = commentNo;
	            form.submit();
	            return false;
	           }
	         };
	      }
	      var form = comments.getElementsByTagName("form")[0];
	      var div = form.getElementsByTagName("div")[0];
	      commentModifyLink = div.getElementsByTagName("a")[0];
	      commentModifyLink.onclick = commentUpdate;
	      var cancelLink = div.getElementsByTagName("a")[1];
	      cancelLink.onclick = modifyCommentToggle;
	    }
	  }  
	}

	var next_prev_links = document.getElementById("next-prev-links");
	links = next_prev_links.getElementsByTagName("a");
	for (i = 0; i < links.length; i++) {
	  links[i].onclick = function() {
	    var form = document.getElementById("viewForm");
	    form.articleNo.value = this.title;
	    form.submit();
	    return false;      
	  };
	}

	var modifyBtns = document.getElementsByClassName("goModify");
	i = modifyBtns.length;
	while (i--) {
	  modifyBtns[i].onclick = function() {
	    var form = document.getElementById("modifyForm");
	    form.submit();
	  };
	}

	var deleteBtns = document.getElementsByClassName("goDelete");
	i = deleteBtns.length;
	while (i--) {
	  deleteBtns[i].onclick = function() {
	    var chk = confirm('Are you suer you want to delete it?');
	    if (chk === true) {
	      var form = document.getElementById("delForm");
	      form.submit();
	    }
	  };
	}

	var nextArticleBtns = document.getElementsByClassName("next-article");
	i = nextArticleBtns.length;
	while (i--) {
	  nextArticleBtns[i].onclick = function() {
	          var form = document.getElementById("viewForm");
	          form.articleNo.value = this.title;
	          form.submit();
	  };
	}

	var prevArticleBtns = document.getElementsByClassName("prev-article");
	i = prevArticleBtns.length;
	while (i--) {
	  prevArticleBtns[i].onclick = function() {
	    var form = document.getElementById("viewForm");
	    form.articleNo.value = this.title;
	    form.submit();
	  };
	}

	var listBtns = document.getElementsByClassName("goList");
	i = listBtns.length
	while (i--) {
	  listBtns[i].onclick = function() {
	    var form = document.getElementById("listForm");
	    form.page.value = this.title;
	    form.submit();
	  };
	}  

	var writeBtns = document.getElementsByClassName("goWrite");
	i = writeBtns.length;
	while(i--) {
	  writeBtns[i].onclick = function() {
	      var form = document.getElementById("writeForm");
	      form.submit();
	  };
	}

	var listTable = document.getElementById("list-table");
	links = listTable.getElementsByTagName("a");
	for (i = 0; i < links.length; i++) {
	  links[i].onclick = function() {
	    var form = document.getElementById("viewForm");
	    form.articleNo.value = this.title;
	    form.submit();
	    return false;
	  };
	}

	var paging = document.getElementById("paging");
	links = paging.getElementsByTagName("a");
	for (i = 0; i < links.length; i++) {
	  links[i].onclick = function() {
	    var form = document.getElementById("listForm");
	    form.page.value = this.title;
	    form.submit();
	    return false;
	  };
	}

	var listMenu = document.getElementById("list-menu");
	writeBtn = listMenu.getElementsByTagName("input")[0];
	writeBtn.onclick = function() {
	  var form = document.getElementById("writeForm");
	  form.submit();
	};
	
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
    <c:if test="${(userInfo.user.email == email) || (userInfo.admin) }">
    <div class="fl">
        <input type="button" value="Modify" class="goModify" />
        <input type="button" value="Del" class="goDelete" />
    </div>
    </c:if>        
    <div class="fr">
		<c:if test="${nextArticle != null }">    
        <input type="button" value="Next Article" title="${nextArticle.articleNo }" class="next-article" />
		</c:if>
		<c:if test="${prevArticle != null }">        
        <input type="button" value="Prev Article" title="${prevArticle.articleNo }" class="prev-article" />
		</c:if>        
        <input type="button" value="List" title="${param.page }" class="goList" />
        <input type="button" value="New" class="goWrite" />
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
    <div id="article-content">${content }</div>
    <div id="file-list" style="text-align: right">
    	<c:forEach var="file" items="${attachFileList }" varStatus="status">
	    	<div class="attach-file">
		    	<a href="${uploadPath }${file.filename }" class="download">${file.filename }</a>
				<c:if test="${userInfo.user.email == file.email || userInfo.admin }">
		    	<a href="#" title="${file.attachFileNo }">x</a>
				</c:if>
			</div>	
		</c:forEach>
    </div>
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
<div id="all-comments">
<c:forEach var="comment" items="${commentList }" varStatus="status">
<div class="comments">
    <span class="writer">${comment.name }</span>
    <span class="date">${comment.regdate }</span>
	<c:if test="${userInfo.user.email == comment.email || userInfo.admin}">    
    <span class="modify-del">
        <a href="#">Modify</a> | <a href="#" title="${comment.commentNo }">Del</a>
    </span>
	</c:if>    
    <div class="comment-memo">${comment.memo }</div>
    <form class="comment-form" action="updateComment.do" method="post" style="display: none;">
        <input type="hidden" name="commentNo" value="${comment.commentNo }" />
        <input type="hidden" name="boardCd" value="${param.boardCd }" />
        <input type="hidden" name="articleNo" value="${param.articleNo }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="searchWord" value="${param.searchWord }" />
	    <div class="fr">
	        <a href="#">Submit</a> | <a href="#">Cancel</a>
	    </div>
	    <div>
	        <textarea class="comment-textarea" name="memo" rows="7" cols="50">${comment.memo }</textarea>
	    </div>
    </form>
</div>
</c:forEach>
</div>
<!--  comments end -->
<div class="next-prev" id="next-prev-links">
    <c:if test="${nextArticle != null }">
    <p>Next Article : <a href="#" title="${nextArticle.articleNo }">${nextArticle.title }</a></p>
    </c:if>
    <c:if test="${prevArticle != null }">
    <p>Prev Article : <a href="#" title="${prevArticle.articleNo }">${prevArticle.title }</a></p>
    </c:if>
</div>
<div class="view-menu">
    <c:if test="${(userInfo.user.email == email) || (userInfo.admin) }">
    <div class="fl">
        <input type="button" value="Modify" class="goModify" />
        <input type="button" value="Del" class="goDelete" />
    </div>
    </c:if>        
    <div class="fr">
		<c:if test="${nextArticle != null }">    
        <input type="button" value="Next Article" title="${nextArticle.articleNo }" class="next-article" />
		</c:if>
		<c:if test="${prevArticle != null }">        
        <input type="button" value="Prev Article" title="${prevArticle.articleNo}" class="prev-article" />
		</c:if>        
        <input type="button" value="List" title="${param.page }" class="goList" />
        <input type="button" value="New" class="goWrite" />
    </div>
</div>
<!-- BBS List in detailed Article -->
<table class="bbs-table" id="list-table">
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
		<a href="#" title="${article.articleNo }">${article.title }</a>
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
		<a href="#">1</a>
		<a href="#" title="${prevPage }">[Prev]</a>
	</c:if>
	<c:forEach var="i" begin="${firstPage }" end="${lastPage }">
		<c:choose>
			<c:when test="${param.page == i }">
				<span class="bbs-strong">${i }</span>
			</c:when>	
			<c:otherwise>	
				<a href="#" title="${i }">${i }</a>
			</c:otherwise>
		</c:choose>			
	</c:forEach>
	<c:if test="${nextPage > 0 }">	
		<a href="#" title="${nextPage }">[Next]</a>
		<a href="#" title="${totalPage }">[Last]</a>
	</c:if>
</div>
<div id="list-menu">
	<input type="button" value="New" />
</div>
<div id="search">
	<form action="list.do" method="get">
		<input type="hidden" name="boardCd" value="${param.boardCd }" />
		<input type="hidden" name="page" value="1" />
		<input type="text" name="searchWord" size="15" maxlength="30" />
		<input type="submit" value="Search" />
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