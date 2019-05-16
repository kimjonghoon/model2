<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Boards</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="Keywords" content="Boards" />
<meta name="Description" content="Boards" />
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
window.onload = initPage;

function initPage() {
	var board_list = document.getElementById("board-list"); 
	var links = board_list.getElementsByTagName("a");
	
	for (var i = 0; i < links.length; i++) {
		var link = links[i];
		link.onclick = function(e) {
			e.preventDefault();
			var boardCd = this.text;
			var boardNm = this.title;
			var boardNm_ko = this.className;
			var form  = document.getElementById("editBoard");
			form.boardCd.value = boardCd;
			form.boardNm.value = boardNm;
			form.boardNm_ko.value = boardNm_ko;
		};
	}
}
</script>
</head>
<body>
<div id="wrap">
	
	<div id="header">
		<%@ include file="../inc/header.jsp" %>
	</div>
	
	<div id="main-menu" lang="en">
		<%@ include file="../inc/main-menu.jsp" %>
	</div>
	
	<div id="container">
		<div id="content">
<!-- content begin -->
<h3>Boards</h3>
<table class="bbs-table" id="board-list">
    <tr>
        <th style="text-align: left;">Board Code</th>
        <th style="text-align: left;">Board Name</th>
        <th style="text-align: left;">Board Korean Name</th>
    </tr>
    <c:forEach var="board" items="${boards }" varStatus="status">
        <tr>
            <td><a href="#" title="${board.boardNm }" class="${board.boardNm_ko }">${board.boardCd }</a></td>
            <td>${board.boardNm }</td>
            <td>${board.boardNm_ko }</td>
        </tr>
    </c:forEach>
</table>

<h2>Edit Board</h2>

<form id="editBoard" action="/admin/editBoard.do" method="post">
    <table class="bbs-table">
        <tr>
            <td>Board Code</td>
            <td><input type="text" name="boardCd" readonly=readonly /></td>
        </tr>
        <tr>
            <td>Board Name</td>
            <td><input type="text" name="boardNm" /></td>
        </tr>
        <tr>
            <td>Board Korean Name</td>
            <td><input type="text" name="boardNm_ko" /></td>
        </tr>
    </table>
    <div>
        <input type="submit" value="Submit" />
    </div>
</form>

<h2>New Board</h2>

<form id="createBoard" action="/admin/createBoard.do" method="post">
    <table class="bbs-table">
        <tr>
            <td>Board Code</td>
            <td><input type="text" name="boardCd" />
        </tr>
        <tr>
            <td>Board Name</td>
            <td><input type="text" name="boardNm" />
        </tr>
        <tr>
            <td>Board Korean Name</td>
            <td><input type="text" name="boardNm_ko" />
        </tr>
    </table>
    <div>
        <input type="submit" value="Submit" />
    </div>
</form>
<!-- content end -->
		</div>
	</div>

	<div id="sidebar" lang="en">
		<%@ include file="menu.jsp" %>
	</div>

	<div id="extra">
		<%@ include file="../inc/extra.jsp" %>
	</div>
	
	<div id="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	
</div>
</body>
</html>