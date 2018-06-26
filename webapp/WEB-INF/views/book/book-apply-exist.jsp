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
	
	<h1>&nbsp;</h1>
	<h3 align="center">이미 존재하는 도서입니다.</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>isbn</th>
					<th>책 제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>파일</th>
					
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${ book.isbn }</td>
					<td>${ book.title }</td>
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
	</div>
	<div align="center">
		<a href="<c:url value='/index.do'/>">홈으로 이동</a>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	