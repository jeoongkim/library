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
	 <jsp:include page="template/header.jsp" flush="false" />
	
	<h1>&nbsp;</h1>
	<h3 align="center">회원탈퇴</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<h5> 그 동안 서비스를 이용해 주셔서 감사합니다.</h5> 
		<h3>&nbsp;</h3>
		<p>아래 내용을 잘 확인 하시고 탈퇴를 해주세요.</p>
		<hr>
		<ul>
			<li>희망도서관에서 정보가 삭제되며 더 이상 서비스를 이용할 수 없게 됩니다.</li>
			<li>부정 가입을 방지하기 위해 탈퇴 후 동일한 이메일로 재가입이 되지 않습니다.</li>
			<li>궁금한 사항이 있으시면 관리자에게 문의주세요</li>
		</ul>
		<hr>
	
		<h1>&nbsp;</h1>
		<form action="<c:url value='/users-withdraw.do'/>" method="post" onsubmit="confirm()">
			<div class="form-group row">		
				<label class="col-sm-2 col-form-lable" for="email">Email</label>
				<div class="col-sm-5">
					<input class="form-control-plaintext" type="text" id="email" name="email" value=${ user.email } readonly>
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="password">비밀번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="password" name="password" placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			<c:if test="${ param.error == 'password' }">
				<div style="color:#ff0000">비밀번호가 틀렸습니다. 다시 입력해주세요.</div>
			</c:if>
			<c:if test="${ param.error == 'email' }">
				<div style="color:#ff0000">이메일을 확인해주세요.</div>
			</c:if>
			<div align="center">
				<input class="btn btn-danger col-sm-1" type="submit" value="탈퇴">	
			</div>
				<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		</form>
	</div>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script>
		function confirm()
		{
			var password = $("#password").val();

			if(password.length==0)
			{
				alert("비밀번호를 입력하세요 ");
				$("#password").focus;
				return false;
			}
		}
	</script>
</body>
</html>	