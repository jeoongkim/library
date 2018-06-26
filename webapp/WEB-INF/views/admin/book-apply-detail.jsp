<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">	<!-- 페이지 인코딩 설정 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
	<title>희망도서관</title>
	
</head>
<body>
	<div class="container" style="margin: 100">
	
	 <jsp:include page="../template/header_admin.jsp" flush="false" />
	
	<div class="container">
		<br>
		<br>
		<h1 align="center">희망도서 신청내역</h1>
		<br>
		<br>
		<form action="<c:url value='/admin/book-apply-warehousing.do'/>" method="post" enctype="multipart/form-data">
			<table class="table table-striped">
				<tr>
					<th>No</th>
					<td>${ book.applyNo }</td>
				</tr>
				<tr>
					<th>신청일</th>
					<td><fmt:formatDate value="${ book.applyDate }" type="both" pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${ book.users.name }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input class="form-control" type="text" id="title" name="title" value="${ book.title }"></td>
				</tr>
				<tr>
					<th>저자</th>
					<td><input class="form-control" type="text" id="author" name="author" value="${ book.author }"></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td><input class="form-control" type="text" id="publisher" name="publisher" value="${ book.publisher }"></td>
				</tr>
				<tr>
					<th>ISBN</th>
					<td><input class="form-control" type="text" id="isbn" name="isbn" value="${ book.isbn }"></td>
				</tr>
				<tr>
					<th>도서분류</th>
					<td>
						<select name="groupName">
							<option value="A">A : 어른</option>
							<option value="C">C : 아동</option>
							<option value="O">O : 오디오</option>
						</select>
					</td>
				</tr>
			</table>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="attachment">첨부파일</label>
				<div>
					<input class="form-control" type="file" id="attachment" name="attachment">
				</div>
			</div>
			<div>
				<label>출판일</label>
				<span><input type="date" name="publicationDay"></span>
			</div>
			<div>
				<label>입고일</label>
				<span><input type="date" name="wearingDay"></span>
			</div>
			
			<input type="hidden" name="applyNo" value="${ book.applyNo }">
			<input type="hidden" name="userNo" value="${ book.userNo }">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<div align="center">
				<input class="btn btn-primary" type="submit" value="입고완료">		
			</div>
		</form>
		<a href="<c:url value='book-apply-list.do'/>">목록으로 이동</a>
	</div>
	
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>