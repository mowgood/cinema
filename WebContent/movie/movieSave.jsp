<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="movieSaveForm" method="post" action="/CinemaPlus/movie/save.do">
		<label>영화 코드</label>
			<input type="text" name="MCODE" class="form-control" placeholder="영화 코드" name="MCODE" style="width:100%" />
		<label>영화 제목</label>
			<input type="text" name="MTITLE" class="form-control" placeholder="영화 제목" name="MTITLE" style="width:100%" />
		<label>영화 감독</label>
			<input type="text" name="MDIRECTOR" class="form-control" placeholder="영화 감독" name="MDIRECTOR" style="width:100%" />
		<label>영화 회사</label>
			<input type="text" name="MCOMPANY" class="form-control" placeholder="영화 회사" name="MCOMPANY" style="width:100%" />
		<label>영화 배우</label>
			<input type="text" name="MACTOR" class="form-control" placeholder="영화 배우" name="MACTOR" style="width:100%" />
		<label>영화 내용</label>
			<input type="text" name="MACTOR" class="form-control" placeholder="영화 배우" name="MACTOR" style="width:100%" />
		<label>영화 포스터</label>
			<input type="text" name="MIMAGE" class="form-control" placeholder="영화 이미지" name="MIMAGE" style="width:100%" />
		
		<input type="submit" class="btn btn-primary ar" value="등록">
	</form>
</body>
</html>