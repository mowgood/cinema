<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.noticeBoard table {
	width:100%;
	text-align:center;
	border:1px solid #dddddd;
}
.noticeBoard table thead th {
	border-top: 1px solid #e5e5e5;
	border-bottom: 1px solid #e5e5e5;
	padding: 5px 0;
	text-align: center;
	background: #faf9fa;
}

.noticeBoard table tbody td {
	text-align:left;
}
.form {
	background-color:#eee;
	text-align:center;
}
.ar {text-align:right;}
.none {text-decoration:none;}
</style>
<script type="text/javascript">
function fn_delete() {
	if (confirm("게시글을 삭제하시겠습니까?")) {
		var form = document.noticeBoard;
		form.action = "/CinemaPlus/board/delete.do";
		form.submit();
	}
}
</script>
</head>
<body>
	<form name="noticeBoard" method="post" action="/CinemaPlus/board/edit.do">
		<input type="hidden" name="noticeNum" value="${notice.noticeNum }" />
		<input type="hidden" name="noticeId" value="${notice.noticeId }" />
		<div class="noticeWrite" style="width:100%">
			<table class="table table-striped">
				<colgroup>
					<col width="10%"></col>
					<col width="90%"></col>
				</colgroup>
				<thead>
					<tr>
						<th colspan="2" class="form">게시글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>작성자</th>
						<td>
							${notice.userName }
						</td>
					</tr>
					<tr>
						<th>글 제목</th>
						<td>
							${notice.noticeSubject }
						</td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td>
							${notice.noticeContent }
						</td>
					</tr>
				</tbody>
			</table>
			<div class="ar">
				<c:if test="${id eq notice.noticeId || id eq 'admin' }">
					<input type="submit" class="btn btn-primary ar" value="수정">
					<input type="button" class="btn btn-primary ar" value="삭제" onclick="fn_delete();">
				</c:if>
				<a href="/CinemaPlus/board/list.do" class="none">
					<input type="button" value="목록" />
				</a>
			</div>
		</div>
	</form>
</body>
</html>