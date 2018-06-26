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
	<h3 align="center" >&#60; 열람실 자리 지정 &#62;</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/user/readingRoom-add.do'/>" method="post" onsubmit="return confirm();">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="roomNo">열람실 번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="roomNo" name="roomNo" placeholder="열람실 번호를 입력하세요">
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="seatNo">자리 번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="seatNo" name="seatNo" placeholder="자리 번호를 입력하세요">
				</div>
			</div>
			<div align="center">
				<input class="btn btn-primary" type="submit" value="자리 지정" >	
			</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script>
		function confirm()
		{
			var roomNo = $("#roomNo").val();
			var seatNo = $("#seatNo").val();

			if(roomNo.length==0)
			{
				alert("열람실 번호를 입력하세요");
				$("#roomNo").focus();
				return false;
			}
			if(seatNo.length==0)
			{
				alert("자리번호를 입력하세요");
				$("#seatNo").focus();
				return false;
			}
		}
	</script>
	
</body>
</html>	