<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.noticeWrite table {
	width:100%;
	text-align:center;
	border:1px solid #dddddd;
}
.noticeWrite table thead th {
	border-top: 1px solid #e5e5e5;
	border-bottom: 1px solid #e5e5e5;
	padding: 5px 0;
	text-align: center;
	background: #faf9fa;
}

.form {
	background-color:#eee;
	text-align:center;
}
.ar {text-align:right;}
</style>
</head>
<body>
	<form name="editForm" method="post" action="/CinemaPlus/board/modify.do">
		<input type="hidden" name="TYPE" value="BOARD" />
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
						<th colspan="2" class="form">게시판 글쓰기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>글 제목</th>
						<td>
							<input type="text" name="noticeSubject" class="form-control" placeholder="글 제목" name="bbsTitle" style="width:100%" value="${notice.noticeSubject }" />
						</td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td>
							<textarea name="noticeContent" class="form-control" placeholder="글 내용" name="bbsContent" style="width:100%; height:200px;">${notice.noticeContent }</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="ar">
				<input type="submit" class="btn btn-primary ar" value="수정">
			</div>
		</div>
	</form>
</body>
</html>