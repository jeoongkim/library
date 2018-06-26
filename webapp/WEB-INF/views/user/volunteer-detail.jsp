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
		<br>
		<br>
		<h1 align="center">자원봉사 신청</h1>
		<br>
		<br>
		<table class="table table-striped">
			<tr>
				<th>No</th>
				<td>${ volun.volunNo }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${ volun.title }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${ volun.regDate }" type="both" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ user.name }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${ volun.description }</td>
			</tr>
			<tr>
				<th>신청일</th>
				<td><fmt:formatDate value="${ volun.applyDate }" type="both" pattern="yyyy-MM-dd" /></td>
			</tr>
		</table>

		<div>
			<a href="<c:url value='/user/volunteer-modify.do?volunNo=${ volun.volunNo }' />">수정</a>
			<a href="<c:url value='/user/volunteer-code2.do?volunNo=${ volun.volunNo }' />">삭제</a>
		</div>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	