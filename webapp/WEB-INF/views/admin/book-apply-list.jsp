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
		<br><br><h1 align="center">희망도서 신청목록</h1><br><br>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>신청자</th>
					<th>신청일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ list }" var="book">
				<tr>
					<td>${ book.applyNo }</td>
					<td><a href="<c:url value='/admin/book-apply-detail.do?applyNo=${ book.applyNo }'/>">${ book.title }</a></td>
					<td>${ book.users.name }</td>
					<td><fmt:formatDate value="${ book.applyDate }" type="both" pattern="yyyy-MM-dd" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>