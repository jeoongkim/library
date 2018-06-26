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
	
	<h1>&nbsp;</h1>
	<h3 align="center" >도서 대출 반납 확인되었습니다. </h3>
	<h1>&nbsp;</h1>
	<div class="col-sm-6" >
	 <table class="table" align="center">
	 	<tr>
	 		<th>사용자</th>
	 		<th>도서명</th>
	 	</tr>
	 	<tr>
	 		<td>${ users.name } </td>
	 		<td>${ loan.bookList.book.title }</td>
	 	</tr>
	 </table>
	</div>
	
		<div align="center">
		<a href="<c:url value='/admin/loan-add.do'/>" class="btn btn-primary" role="button">도서 대출 페이지로</a>
		<a href="<c:url value='/admin/admin-index.do'/>" class="btn btn-primary" role="button">관리자 페이지로</a>
		
	</div>
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>