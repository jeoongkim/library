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
	
	<br><br><h1 align="center" >${ users.name } 님의 ${ loan.bookList.book.title } 도서 대출 반납 확인되었습니다.</h1><br><br>
	<div class="container">
		<form action="<c:url value='/admin/stop-date.do'/>" method="post">
			<p> 현재 반납된 도서의 반납 예정일은 <fmt:formatDate pattern = "yyyy-MM-dd" value = "${loan.deadline}" /> 입니다.
			 대출 정지 기간을 정해주세요</p>
			<input type="date" name="stopday" id="stopday">
			<div align="center">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
				<input type="hidden" name="userNo" value="${ users.userNo }">			
				<input class="btn btn-primary" type="submit" value="제출하기">	
			</div>
		</form>
	</div>
	
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>