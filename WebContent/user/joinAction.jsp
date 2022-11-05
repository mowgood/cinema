<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% request.getParameter("userID"); %>
<% request.getParameter("userPassword"); %>
<% request.getParameter("userName"); %>
<% request.getParameter("userMobile"); %>
<% request.getParameter("userEmail"); %>
<jsp:useBean id="user" class="user.User" scope="page">
	<jsp:setProperty name="user" property="userID"/>
	<jsp:setProperty name="user" property="userPassword"/>
	<jsp:setProperty name="user" property="userName"/>
	<jsp:setProperty name="user" property="userMobile"/>
	<jsp:setProperty name="user" property="userEmail"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
		if(user.getUserID() == null || user.getUserPassword() == null ||
				user.getUserName()==null || user.getUserMobile() == null || user.getUserEmail()==null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('정보를 모두 입력하세요.')");
		script.println("history.back()");
		script.println("</script>");
		}
		else {
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
		
		if (result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디 입니다.')");
			script.println("history.back()");
			script.println("</script>");
			
		}else if(result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'index2.html'");
			script.println("</script>");
			
			}
		} 
%>
</body>
</html>