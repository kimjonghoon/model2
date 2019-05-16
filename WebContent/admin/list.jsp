<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.java_school.user.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="Keywords" content="Users" />
<meta name="Description" content="Users" />
<title>Users</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
window.onload = initPage;

function initPage() {
	var user_list = document.getElementById("user-list"); 
	var links = user_list.getElementsByTagName("a");
	
	for (var i = 0; i < links.length; i++) {
		var link = links[i];
		if (link.className == "del-user-link") {
			link.onclick = function(e) {
				e.preventDefault();
				var check = confirm('Are you sure you want to delete it?')
				if (check) {
					var email = this.title;
					var form  = document.getElementById("delUserForm");
					form.email.value = email;
					form.submit();
				}
			};
		}
	}
}
</script>
</head>
<body>

	<div id="wrap">

		<div id="header">
			<%@ include file="../inc/header.jsp"%>
		</div>

		<div id="main-menu">
			<%@ include file="../inc/main-menu.jsp"%>
		</div>

		<div id="container">
			<div id="content">
<!-- content begin -->
<div id="content-categories">Admin</div>
<h3>User List</h3>
<table class="bbs-table" id="user-list">
    <tr>
        <th style="text-align: left;">No</th>
        <th style="text-align: left;">Full Name</th>
        <th style="text-align: left;">Email</th>
        <th style="text-align: left;">Mobile</th>
        <th style="text-align: left;">Roles</th>
        <th>&nbsp;</th>
    </tr>
    <c:forEach var="userInfo" items="${list }" varStatus="status">
        <tr>
            <td>${listItemNo - status.index }</td>
            <td>${userInfo.user.name }</td>
            <td>${userInfo.user.email }</td>
            <td>${userInfo.user.mobile }</td>
            <td>${userInfo.roles }</td>
            <td>
                <a href="/admin/editAccount.do?email=${userInfo.user.email }&page=${param.page }&search=${param.search }">Edit</a> |
                <a href="#" title="${userInfo.user.email }" class="del-user-link">Del</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div id="paging">
    <c:if test="${prev > 0 }">
        <a href="/admin?page=1">1</a>
        <a href="/admin?page=${prev }&search=${search }">[ Prev ]</a>
    </c:if>
    <c:forEach var="i" begin="${firstPage }" end="${lastPage }" varStatus="stat">
        <c:choose>
            <c:when test="${param.page == i}">
                <span class="bbs-strong">${i }</span>
            </c:when>
            <c:otherwise>
                <a href="/admin?page=${i }&search=${param.search }">[ ${i } ]</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${next > 0 }">
        <a href="/admin?page=${next }&search=${search }">[ Next ]</a>
        <a href="/admin?page=${totalPage }">[ Last ]</a>
    </c:if>
</div>

<form>
    <input type="hidden" name="page" value="1" />
    <input type="text" name="search" />
    <input type="submit" value="Search" />
</form>

<form id="delUserForm" action="/admin/delUser.do" method="post">
    <input type="hidden" name="page" value="${param.page }" />
    <input type="hidden" name="search" value="${param.search }" />
    <input type="hidden" name="email" />
</form>
<!-- content end -->
			</div>
		</div>

		<div id="sidebar">
			<%@ include file="menu.jsp" %>
		</div>

		<div id="extra">
			<%@ include file="../inc/extra.jsp"%>
		</div>

		<div id="footer">
			<%@ include file="../inc/footer.jsp"%>
		</div>

	</div>

</body>
</html>