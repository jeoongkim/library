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
		<h1>&nbsp;</h1>
		<h3 align="center">&#60; 도서 예약 내역 &#62;</h3>
		<h1>&nbsp;</h1>
		<p>전체 사용자 예약 내역</p>
		<h1>&nbsp;</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>예약번호</th>
						<th>도서 제목</th>
						<th>회원 이름</th>
					</tr>
				</thead>
			<c:forEach items="${ reservation }" var="list">
				<tbody>

					<tr>
						<td>${list.reserNo }</td>
						<td>${list.book.title }</td>
						<td>${list.users.name }</td>
					</tr>
				</tbody>
			</c:forEach>
			</table>
			
		<h1>&nbsp;</h1>
		<h3 align="center">&#60; 도서 대출 내역 &#62;</h3>
		<h1>&nbsp;</h1>
		<p>전체 사용자 대출 내역</p>
		<h1>&nbsp;</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>대출 번호</th>
						<th>회원 이름</th>
						<th>도서 번호</th>
						<th>도서 제목</th>
						<th>대출 일</th>
						<th>반납 예정일</th>
					</tr>
				</thead>
			<c:forEach items="${ loan }" var="list">
				<tbody>

					<tr>
						<td>${list.loanNo }</td>
						<td>${list.users.name }</td>
						<td>${list.bookNo }</td>
						<td>${list.bookList.book.title }</td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${list.loanDate }" /></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${list.deadline }" /></td>
					</tr>
				</tbody>
			</c:forEach>
			</table>
	</div>
	
		<h1>&nbsp;</h1>
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>