<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%session.setAttribute("id", "userID" ); %>
<%session.setAttribute("pw", "userPassword"); %>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
