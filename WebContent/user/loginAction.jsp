<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% String id = request.getParameter("userID"); %>
<% String pw = request.getParameter("userPassword"); %>
<%session.setAttribute("id", "userID" ); %>
<%session.setAttribute("pw", "userPassword"); %>
<jsp:useBean id="user" class="user.User" scope="page">
	<jsp:setProperty name="user" property="userID"/>
	<jsp:setProperty name="user" property="userPassword"/>
	<jsp:setProperty name="user" property="userName"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%				
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(id, pw);
		
		if (result == -1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('해당하는 아이디가 없습니다.')");
			script.println("history.back()");
			script.println("</script>");
			
		}else if(result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'index2.html'");
			script.println("location.reload()");
			script.println("</script>");
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setMaxInactiveInterval(12 * 60 * 60);
			}
		else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀렸습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
%>
</body>
</html>