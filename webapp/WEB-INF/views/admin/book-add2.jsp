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
	<h3 align="center">&#60; 도서 추가 &#62;</h3>
	<h1>&nbsp;</h1>
	<form action="<c:url value='/admin/book-add2.do'/>" method="post" enctype="multipart/form-data">
		
		<div class="form-group row">
			<label class="col-sm-2 col-form-lable" for="isbn"> ISBN</label>
			<div class="col-sm-5">
				${ isbn }
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-lable" for="date">입고일</label>
			<div class="col-sm-5">
				<input type="date" name="wearingDay">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-lable" for="title">출판일</label>
			<div class="col-sm-5">
				<input type="date" name="publicationDay">
			</div>
		</div >
		<h1>&nbsp;</h1>
		<div align="center">
			<input class="btn btn-primary" type="submit" value="도서 추가">
			<input class="btn btn-secondary" type="reset" value="입력한 내용 지우기">
			<a class="btn btn-info" role="button" href="<c:url value='/book/book-list.do'/>">도서 목록으로 이동</a>
		</div>
		<input type="hidden" name="isbn" value="${ isbn }">
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	
		<h1>&nbsp;</h1>
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>