<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
	String id=(String)session.getAttribute("id");
	String pw=(String)session.getAttribute("pw");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
#s1 {height="10px";}
#s2 {height="10px";}
#s3 {font-size: 0.8em; color: grey;}
a:link, a:visited {color: grey;}
body {margin-left:350px;}
</style>
<script type="text/javascript">
function changeContent(loc, target) {
	document.getElementById("frame").src = loc;
}
</script>
</head>
<body>
	<form action="loginAction.jsp" method="post" name="login">
		<span id="s1">
			<input type="text" name="userID" id="userID" size="20" maxlength="12"  placeholder="아이디를 입력하세요." 
				style="height:18px; border:1px solid #ccc;" /><br>
			<input type="password" name="userPassword" id="userPassword" size="21" maxlength="12"  placeholder="비밀번호를 입력하세요." 
				style="height:18px; border:1px solid #ccc;" />
		</span>
		<span id="s2">
			<input type="submit" name="login" value="로그인" style="height:50px; background-color:#4ABFD3; border:1px solid #ccc;"><br>	
		</span>
		
		<span id="s3">
			<br>
			<a href="javascript:changeContent('./join.jsp')" target="_parent" target="_blank">회원가입</a>
			<a href="main.jsp" target="_blank">아이디 찾기</a>
			<a href="main.jsp" target="_blank">비밀번호 찾기</a>
		</span>
	</form>		
</body>
</html>