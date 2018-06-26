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
	 <jsp:include page="../template/header.jsp" flush="false" />
	
	<div class="container">
		<br><br><h1 align="center">예약확정 도서목록</h1><br><br>
		<table class="table table-striped">
				<thead>
					<tr>
						<th>No</th>
						<th>제목</th>
						<th>작가</th>
						<th>예약확정일</th>
						<th>예약마감일</th>
					</tr>
				</thead>
			<tbody>
					<c:forEach items="${ loan }" var="loan">
						<tr>
							<td>${ loan.bookNo }</td>
							<td>${ loan.bookList.book.title }</td>
							<td>${ loan.bookList.book.author }</td>
							<td><fmt:formatDate value="${ loan.reservLoanDate }" type="both" pattern="yyyy-MM-dd" /></td>
							<td><fmt:formatDate value="${ loan.deadline }" type="both" pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
		</table>
		
		<br><br><h1 align="center">예약대기 도서목록</h1><br><br>
		<table class="table table-striped">
				<thead>
					<tr>
						<th>isbn</th>
						<th>제목</th>
						<th>작가</th>
						<th>출판사</th>
					</tr>
				</thead>
			<tbody>
					<c:forEach items="${ reserv }" var="res">
						<tr>
							<td>${ res.isbn }</td>
							<td>${ res.book.title }</td>
							<td>${ res.book.author }</td>
							<td>${ res.book.publisher }</td>
						</tr>
					</c:forEach>
				</tbody>
		</table>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	