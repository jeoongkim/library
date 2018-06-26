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
		<h1>&nbsp;</h1>
		<h3 align="center">회원 상세 정보 확인 페이지</h3>
		<h1>&nbsp;</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>회원번호</th>
					<th>회원 이메일</th>
					<th>회원 이름</th>
					<th>회원 전화번호</th>
					<th>회원 권한</th>
					<th>회원 사진</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${users.userNo }</td>
					<td>${users.email }</td>
					<td>${users.name }</td>
					<td>${users.phoneNum }</td>
					<td>${users.authorities }</td>
					<td><img width="100" height="100" src="${ uploadPath }/${ users.attachment }"></td>
				</tr>
			</tbody>
		</table>

		<hr>
		<div>대출 기록</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>isbn</th>
						<th>도서 번호</th>
						<th>도서 제목</th>
						<th>대출일</th>
						<th>반납일</th>
					</tr>
				</thead>
				<c:forEach items="${ historyList }" var="list">
				<tbody>

					<tr>
						<td>${ list.bookList.isbn }</td>
						<td>${list.bookNo }</td>
						<td><a href="<c:url value='/book-detail.do?isbn=${ list.bookList.isbn }'/>">${list.bookList.book.title }</a></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${list.loanDate }" /></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${list.returnDate }" /></td>
					</tr>

				</tbody>
				</c:forEach>
			</table>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	