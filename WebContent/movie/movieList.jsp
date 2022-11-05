<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 목록</title>
</head>
<body>
	<div class="movieList">
		<table>
			<colgroup>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="40%"></col>
			</colgroup>
			<thead>
				<tr>
					<th>영화 번호</th>
					<th>영화 제목</th>
					<th>감독</th>
					<th>회사</th>					
					<th>배우</th>
					<th>내용</th>
					<th>예매</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty  movieList }">
				<tr>
					<td colspan="7" class="ac">조회된 데이터가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty movieList }">
				<c:forEach var="movie" items="${movieList }">
				<tr>
					<td>${movie.MNUM }</td>
					<td>${movie.MTITLE }</td>
					<td>${movie.MDIRECTOR }</td>
					<td>${movie.MCOMPANY }</td>
					<td>${movie.MACTOR }</td>
					<td>${movie.MCONTENTS }</td>
					<td><a href="/CinemaPlus/movie/reservationForm.do?MTITLE=${movie.MTITLE }">예매하기</a></td>
				</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>	
	
	<form name="movieDeleteForm" method="post" action="/CinemaPlus/movie/delete.do">
		<label>삭제할 영화 번호 입력</label>
		<input type="text" name="MNUM" class="form-control" placeholder="영화 번호" name="MNUM" style="width:100%" />
	</form>
</body>
</html>