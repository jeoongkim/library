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
		<br><br><h1 align="center">회원 상세 정보 확인 페이지</h1><br><br>
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
						<th>대출 번호</th>
						<th>도서 번호</th>
						<th>도서 제목</th>
						<th>대출일</th>
						<th>반납예정일</th>
						<th>대출 연장</th>
					</tr>
				</thead>
				<c:forEach items="${ loan }" var="list">
				<tbody>

					<tr>
						<td>${list.loanNo }</td>
						<td>${list.bookNo }</td>
						<td>${list.bookList.book.title }</td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${list.loanDate }" /></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${list.deadline }" /></td>
						<td><c:choose>
							<c:when test="${list.extension eq 'false' }">
								<form action="<c:url value='/user/user-loan-history1.do'/>" method="get">
									<input type="hidden" name="bookNo" value="${ list.bookNo }">
									<input type="hidden" name="deadline" value="${ list.deadline }">
									<input type="hidden" name="userNo" value="${ users.userNo }">
									<input type="submit" value="대출 연장" class="btn btn-primary"/>
									<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
								</form>
							</c:when>
							<c:when test="${list.extension eq 'true' }">
								대출 연장 불가
							</c:when>
						</c:choose></td>
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