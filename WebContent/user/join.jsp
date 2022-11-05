<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script language="javascript" src="check.js"></script>
<style>
.content{
	border:2px solid #cce5e9;	
	margin-left:150px;
	margin-bottom:0px;
	font-size:1.0em;
	width: 800px;
}
label{
	font-family: 'Noto Sans KR', sans-serif;
	font-size:14px;
	line-height:1;
	color: #222;
	font-weight:normal;
}
</style>
</head>
<body>
<div class="content">
		<form action="joinAction.jsp" method="post" name="join" onSubmit="checkForm()">
			<label>아이디 : <input type="text" name="userID" size="15" maxlength="12" id="userID"
            						style="width:113px; height:20px; border:1px solid #ccc;"></label>
        	
            						※ 영문(대소문자 구분), 숫자를 포함한 4~12자 이하. 한글사용불가<br><br>
         						
        	<label>비밀번호: <input type="password" name="userPassword" size="20" maxlength="12" id="userPassword"  
            									style="height:20px; border:1px solid #ccc;">
            								※ 4~12자 이하로 입력합니다.</label><br><br>
         	<label>비밀번호 확인: <input type="password" name="pw2" size="20" maxlength="12" id="pw2"
                								style="height:20px; border:1px solid #ccc;"></label><br><br>
         	<label>이름 : <input type="text" name="userName" size="20" maxlength="12" id="userName"
                							style="width:150px; height:20px; border:1px solid #ccc;"></label><br><br>
         	<label>전화번호 :  <input type="tel" id="userMobile" name="userMobile" placeholder="전화번호" 
         						style="width:150px; height:20px; border:1px solid #ccc;"></label><br><br>
        	<label>이메일 : <input type="email" id="userEmail" name="userEmail" placeholder="이메일"
        						style="width:150px; height:20px; border:1px solid #ccc;"></label><br><br>

        	<input type="submit"  value="회원가입">
        	<input type=reset value="재입력" class="bu">
        	<span id="value"> </span>							
		</form>
	</div>
</body>
</html>