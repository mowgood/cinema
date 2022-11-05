<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매 내역</title>
</head>
<body>
	<div class="reservationList">
		<table>
			<colgroup>
				<col width="10%"></col>
				<col width="20%"></col>
				<col width="20%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="20%"></col>
			</colgroup>
			<thead>
				<tr>
					<th>예매 번호</th>
					<th>영화 제목</th>
					<th>예매 날짜</th>
					<th>예매 시간</th>					
					<th>예매 인원</th>
					<th>좌석 번호</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty  reservationList }">
				<tr>
					<td colspan="6" class="ac">조회된 데이터가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty reservationList }">
				<c:forEach var="reservation" items="${reservationList }">
				<tr>
					<td>${reservation.resIdx }</td>
					<td>%{reservation.resTitle }</td>
					<td>${reservation.resDate }</td>
					<td>${reservation.resTime }</td>
					<td>${reservation.resAmount }</td>
					<td>${reservation.resSeat }</td>
				</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>	
</body>
</html>