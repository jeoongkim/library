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
	
	<br><br><h1 align="center">삭제하시겠습니까?</h1><br><br>
	<div class="container">
		<form action="<c:url value='/user/volunteer-remove.do'/>" method="post">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="textpassword">비밀번호</label>
				<div class="col-sm-10">
					<input class="form-control" type="password" id="textpassword" name="textpassword">
				</div>
			</div>
			<input type="hidden" name="volunNo" id="volunNo" value="${ volunNo }">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<div align="center">
				<input class="btn btn-primary" type="submit" value="삭제">	
			</div>
		</form>
		<c:if test="${ param.error == 'password' }">
			<div style="color:#ff0000">비밀번호를 다시 입력해주세요.</div>
		</c:if>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	