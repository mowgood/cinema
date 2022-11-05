<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style type="text/css">
.noticeBoard table {width:100%;}
.noticeBoard table thead th {
	border-top: 1px solid #e5e5e5;
	border-bottom: 1px solid #e5e5e5;
	padding: 5px 0;
	text-align: center;
	background: #faf9fa;
}
.noticeBoard table tbody td {
	padding: 5px 0;
	text-align: center;
	border-bottom: 1px solid #e5e5e5;
}
.ac {text-align:center;}
.ar {text-align:right;}
a {text-decoration:none;}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="noticeBoard">
		<table>
			<colgroup>
				<col width="10%"></col>
				<col width="55%"></col>
				<col width="10%"></col>
				<col width="10%"></col>
				<col width="15%"></col>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일시</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty noticeList }">
				<tr>
					<td colspan="5" class="ac">조회된 데이터가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${!empty noticeList }">
				<c:forEach var="notice" items="${noticeList }">
				<tr>
					<td>${notice.rownum }</td>
					<td>
						<a href="/CinemaPlus/board/view.do?noticeNum=${notice.noticeNum }">${notice.noticeSubject }</a>
					</td>
					<td>${notice.userName }</td>
					<td>${notice.readCount }</td>
					<td>${notice.noticeDate }</td>
				</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<div class="ac">
		<c:forEach var="pageNum" begin="1" end="${totalRows }">
			[
			<c:if test="${pageNum == pageNo }">
				<strong>${pageNum }</strong>
			</c:if>
			<c:if test="${pageNum != pageNo }">
				<a href="/CinemaPlus/board/list.do?pageNo=${pageNum }">${pageNum }</a>
			</c:if>
			]
		</c:forEach>
		</div>
		<div class="ar">
		<c:if test="${!empty id }">
			<a href="/CinemaPlus/board/noticeWrite.jsp">
				<input type="button" value="글쓰기" />
			</a>
		</c:if>
		</div>
	</div>	
</body>
</html>