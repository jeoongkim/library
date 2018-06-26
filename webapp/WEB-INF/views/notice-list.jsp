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
		<h2 align="center">공지사항</h2>
		<h1>&nbsp;</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성일</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ list }" var="notice">
					<tr>
						<td>${ notice.noticeNo }</td>
						<td><a href="<c:url value='/notice-detail.do?noticeNo=${ notice.noticeNo }'/>">${ notice.title }</a></td>
						<td><fmt:formatDate value="${ notice.regDate }" type="both" pattern="yyyy-MM-dd" /></td>
						<td>${ notice.users.name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	