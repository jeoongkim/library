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
		<h1 align="center">공지사항 상세 보기</h1>
		<br>
		<br>
		<table class="table table-striped">
			<tr>
				<th>No</th>
				<td>${ notice.noticeNo }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${ notice.title }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${ notice.regDate }" type="both" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ notice.users.name }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${ notice.description }</td>
			</tr>
		</table>

		<a href="<c:url value='/notice-list.do'/>">목록으로 이동</a>
		<div>
			<sec:authorize access="hasRole('ADMIN') and !hasRole('USERS')">
				<a href="<c:url value='/admin/notice-modify.do?noticeNo=${ notice.noticeNo }' />">수정</a>
				<a href="<c:url value='/admin/notice-remove.do?noticeNo=${ notice.noticeNo }' />">삭제</a>
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