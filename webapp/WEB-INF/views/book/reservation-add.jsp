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
	<h3 align="center" >도서 예약 페이지</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/book/reservation-add.do'/>" method="post">
			
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="bookNo">ISBN</label>
				<div class="col-sm-10">
					${ isbn }
				</div>
			</div>
			
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="bookNo">회원 번호:</label>
				<div class="col-sm-10">
					${ users.userNo }
				</div>
			</div>
			
			<div align="center">
				<input class="btn btn-primary" type="submit" value="예약">	
				<input class="btn btn-outline-primary" type="reset" value="입력한 내용 지우기">	
			</div>
			<input type="hidden" name = "isbn" value ="${ isbn }">
			<input type="hidden" name = "userNo" value ="${ users.userNo }">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	