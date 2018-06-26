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
		<h1>&nbsp;</h1>
		<h2 align="center">오디오 도서 목록</h2>
		<h1>&nbsp;</h1>
		<table class="table table-striped">
				<thead>
					<tr>
						<th>isbn</th>
						<th>제목</th>
						<th>저자</th>
						<th>출판사</th>
					</tr>
				</thead>
			<tbody>
					<c:forEach items="${ list }" var="book">
						<tr>
							<td>${ book.isbn }</td>
							<td><a href="<c:url value='/book-audio-detail.do?isbn=${ book.isbn }'/>">${ book.title }</a></td>
							<td>${ book.author }</td>
							<td>${ book.publisher }</td>
						</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>
	<div>
		<sec:authorize access="hasRole('ADMIN') and !hasRole('USERS')">
			<a href="<c:url value='/admin/book-add1.do' />">글 작성</a>
		</sec:authorize>
	</div>
	
	<!-- Footer -->
	<jsp:include page="template/footer.jsp" flush="false" />
	


	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>