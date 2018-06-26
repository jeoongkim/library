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
		<h1 align="center">열람실 좌석 상세 보기</h1>
		<br>
		<br>
		<form action="<c:url value='/user/readingRoom-select.do'/>" method="get">
		<h2>${ name }님의 열람실 좌석 정보</h2>
			<table class="table table-striped">
				<tbody>
					<tr>
						<td>${ readingRoom.roomNo } 열람실</td>
						<td>${ readingRoom.seatNo } 번 좌석</td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:SS" value = "${ readingRoom.time }" /></td>
						
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	