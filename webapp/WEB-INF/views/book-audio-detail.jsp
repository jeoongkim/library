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
	 <jsp:include page="template/header.jsp" flush="false" />

	<div class="container">
		<br>
		<br>
		<h1 align="center">오디오 도서 상세 보기</h1>
		<br>
		<br>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="name">isbn</label>
				<div class="col-sm-10">
					<sapn id="isbn" name="isbn">${ book.isbn }</sapn>
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="title">제목</label>
				<div class="col-sm-10">
					<span id="title" name="title">${ book.title }</span>
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="title">오디오</label>
				<div class="col-sm-10">
					<audio controls controlsList="nodownload">
						<source src="${ filePath }" type="audio/mp3" />
					</audio>
				</div>
			</div>
			<hr>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>입고일</th>
						<th>출판일</th>
						<th>저자</th>
						<th>출판사</th>
						<th>이미지</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${ audio.wearingDay }" /></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${ audio.publicationDay }" /></td>
						<td>${ book.author }</td>
						<td>${ book.publisher }</td>
						<td>
							<c:if test="${ !empty imgPath }">
								<img src="${ imgPath }" width="100" alt="이미지 파일 출력위치">
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			
		<a href="<c:url value='/book-audio-list.do'/>">오디오 도서 목록으로 이동</a> 
		
		<div>
			<sec:authorize access="hasRole('ADMIN') and !hasRole('USERS')">
				<a href="<c:url value='/admin/book-audio-modify.do?isbn=${ book.isbn }' />">수정</a>
				<a href="<c:url value='/admin/book-audio-remove.do?isbn=${ book.isbn }' />">삭제</a>
			</sec:authorize>
		</div>
	</div>
	
	<jsp:include page="template/footer.jsp" flush="false" />
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>