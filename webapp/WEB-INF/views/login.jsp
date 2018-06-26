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
	<style>
		div.loginForm{
			width: 800px;
		}
	</style>
</head>
<body>
	<div class="container" style="margin: 100">
	 <jsp:include page="template/header.jsp" flush="false" />
	
		<h1>&nbsp;</h1>
		<h3>로그인 페이지</h3>
		<h1>&nbsp;</h1>
		<div class="loginForm">
			<form action="<c:url value='/login-processing'/>" method="post" onsubmit="return confirm()" >
				<div class="form-group row" >	
					<label class="col-sm-2 col-form-lable" for="email">이메일</label>
					<div class="col-sm-5">
						<input class="form-control" type="email" id="email" name="email" placeholder="email를 입력하세요" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label" for="password">비밀번호</label>
					<div class="col-sm-5">
						<input class="form-control" type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
					</div>
				</div>
					<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
					<h1>&nbsp;</h1> 
				<div align="center">
					<input class="btn btn-primary" type="submit" value="로그인">	
				</div>
			</form>
		</div>	
		
		<c:if test="${ param.error == 'login' }">
			<div style="color:#ff0000">이메일과 비밀번호를 확인해주세요.</div>
		</c:if>
	 <h1>&nbsp;</h1>
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script >
		function confirm(){
			var email =$("#email").val();
			var password=$("#password").val();
							
			if(email.length==0)
			{
				alert("이메일을 입력하세요");
				$("#email").focus();
				return false;
			}
			if(password.length==0)
			{
				alert("비밀번호를 입력하세요");
				$("#password").focus();
				return false;
			}
		};
	</script>
</body>
</html>	