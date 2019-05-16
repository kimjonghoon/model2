<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.java_school.user.*"%>
<%@page import="java.net.URLEncoder"%>
<%   
UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
if (userInfo == null) {
    String uri = request.getRequestURI();
    String query = request.getQueryString();
    String url = uri;
    if (query != null) url += "?" + query;
    url = URLEncoder.encode(url, "UTF-8");
    response.sendRedirect("/users/login.jsp?url=" + url);
    return;
}
%>