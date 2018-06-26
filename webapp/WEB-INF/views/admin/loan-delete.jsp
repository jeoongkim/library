<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 페이지 인코딩 설정 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<title>희망도서관</title>

</head>
<body>
	<div class="container" style="margin: 100">

		<jsp:include page="../template/header_admin.jsp" flush="false" />
		<h1>&nbsp;</h1>
		<h3 align="center">&#60; 도서 반납 페이지 &#62;</h3>
		<h1>&nbsp;</h1>

		<div class="container">
			<form action="<c:url value='/admin/loan-delete.do'/>" method="post"
				onsubmit="return confirm()">
				<div class="form-group row">
					<label class="col-sm-2 col-form-lable" for="bookNo">도서번호</label>
					<div class="col-sm-3">
						<input class="form-control" type="text" id="bookNo" name="bookNo"
							placeholder="도서번호를 입력하세요">
					</div>
				</div>
				<h3>&nbsp;</h3>
				<div align="center">
					<input class="btn btn-primary" type="submit" value="반납확인">
				</div>
				<input type="hidden" name="${_csrf.parameterName }"
					value="${_csrf.token }">

				<c:if test="${ param.error == 'number' }">
					<div style="color: #ff0000">도서번호를 다시 입력해주세요.</div>
				</c:if>
				<c:if test="${ param.error == 'booknumber' }">
					<div style="color: #ff0000">존재하지 않는 도서번호입니다.</div>
				</c:if>
			</form>
		</div>

		<h1>&nbsp;</h1>
		<jsp:include page="../template/footer_admin.jsp" flush="false" />


	</div>

	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script>

		
		function confirm() {
			var bookNo = $("#bookNo").val();
			
			if (bookNo.length == 0) {
				alert("도서번호를 입력하세요");
				$("#bookNo").focus();
				return false;
			}
		}
		
	</script>
</body>
</html>