<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="Keywords" content="Edit Account" />
<meta name="Description" content="Edit Account" />
<title>Edit Account</title>
<link rel="stylesheet" href="/css/screen.css" type="text/css" />
<script>
window.onload = initPage;

function initPage() {
	var del_auth = document.getElementById("del-auth"); 
	var links = del_auth.getElementsByTagName("a");
	
	for (var i = 0; i < links.length; i++) {
		var link = links[i];
		if (link.className == "del-auth-link") {
			link.onclick = function(e) {
				e.preventDefault();
				var authority = this.title;
				var form  = document.getElementById("deleteAuthorityForm");
				form.authority.value = authority;
				form.submit();
			};
		}
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
<div id="content-categories">Admin</div>
<h3>Edit Account</h3>
<form id="editAccountForm" action="editAccount.do" method="post">
    <input type="hidden" name="email" value="${user.email }" />
    <input type="hidden" name="page" value="${param.page }" />
    <input type="hidden" name="search" value="${param.search }" />
    <table>
        <tr>
            <td>Full Name</td>
            <td><input type="text" name="name" value="${user.name }" /></td>
        </tr>
        <tr>
            <td>Mobile</td>
            <td><input type="text" name="mobile" value="${user.mobile }" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>

<hr />

<form id="changePasswdForm" action="changePasswd.do" method="post">
    <input type="hidden" name="email" value="${user.email }" />
    <input type="hidden" name="page" value="${param.page }" />
    <input type="hidden" name="search" value="${param.search }" />
    <table>
        <tr>
            <td>Password</td>
            <td><input type="password" name="passwd" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>

<hr />

<form id="addAuthorityForm" action="addAuthority.do" method="post">
    <input type="hidden" name="email" value="${user.email }" />
    <input type="hidden" name="page" value="${param.page }" />
    <input type="hidden" name="search" value="${param.search }" />
    <table>
        <tr>
            <td>Authorities</td>
            <td id="del-auth">
                <c:forEach var="authority" items="${roles }" varStatus="status">
                    ${authority } <a href="#" title="${authority }" class="del-auth-link">x</a>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>Add authority</td>
            <td>
                <select name="authority">
                    <option value="ROLE_USER" selected="selected">ROLE_USER</option>
                    <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                </select>
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

<div style="text-align: right;">
    <form action="/admin/list.do?page=1">
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="search" value="${param.search }" />
        <input type="submit" value="User List" />
    </form>
</div>

<div style="display: none;">
    <form id="deleteAuthorityForm" action="delAuthority.do" method="get">
        <input type="hidden" name="email" value="${user.email }" />
        <input type="hidden" name="page" value="${param.page }" />
        <input type="hidden" name="search" value="${param.search }" />
        <input type="text" name="authority" />
    </form>
</div>
<!-- content end -->
		</div>
	</div>

	<div id="sidebar">
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