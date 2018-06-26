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
	<h3 align="center">회원정보 수정</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/user/users-modify.do'/>" method="post" enctype="multipart/form-data" onsubmit="return confirm()">
			<div class="form-group row">		
				<label class="col-sm-2 col-form-lable" for="email">Email</label>
				<div class="col-sm-10">
					<input class="form-control-plaintext" type="text" id="email" value=${ users.email } readonly>
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="oldPassword">기존 비밀번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="oldPassword" name="oldPassword" placeholder="기존 비밀번호를 입력하세요">
				</div>
				<input class="btn btn-info col-sm-2" type="text" value="비밀번호 확인" onclick="confirmpsw();">
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="newPassword">변경 비밀번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="newPassword" name="newPassword" placeholder="새로운 비밀번호를 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="newPassword">변경 비밀번호 확인</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="newPasswordCf" name="newPassword" placeholder="비밀번호를 다시 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="name">이름</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="name" name="name" placeholder="변경할 이름을 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="phoneNum">전화번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="phoneNum" name="phoneNum" placeholder="변경할 전화번호를 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="attachment">첨부파일</label>
				<div>
					<input class="form-control" type="file" id="attachment" name="attachment">
				</div>
			</div>
			<h1>&nbsp;</h1>
			<div align="center">
				<input class="btn btn-primary" type="submit" value="제출하기" >	
			</div>
			
				<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		</form>
		<c:if test="${ param.error == 'password' }	">
			<div style="color: #ff0000">기존 비밀번호를 다시 입력해주세요.</div>
		</c:if>
		<h1>&nbsp;</h1>
		<hr>
		<h1>&nbsp;</h1>
		<div align="center">
		<h3>회원 탈퇴</h3>
		<h1>&nbsp;</h1>
		<p style="color: red;"> 탈퇴하시면 복구가 불가능 합니다.<br></p>
			<a  href="<c:url value='/users-withdraw.do'/>" class="btn btn-danger" role="button">회원 탈퇴</a>
		</div>
		<h3>&nbsp;</h3>
		
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script>
		var confirmPassword=false;

		function confirmpsw()
		{
			var password = $("#oldPassword").val();
			$.ajax({
				async: true,
				type: 'GET',
				url:"passwordCheck.do" +"?password="+password,
				dataType:"json",
				contentType: "application/json; charset=UTF-8",
				success: function(data)
				{
					if(data.psw)
					{
						alert("비밀번호가 확인되었습니다.");
						confirmPassword = true;
					}else{
						alert('기존 비밀번호가 틀립니다. 다시 확인해주세요');
					}
				},
				error: function(error,data)
				{
					alert("error: "+error);
					console.log(data);
				}
			});
		}
		function confirm(){
			var oldPassword = $("#oldPassword").val();
			var newPassword = $("#newPassword").val();
			var newPasswordCf=$("#newPasswordCf").val();
			var newName=$("#name").val();
			var phoneNum=$("#phoneNum").val();

			if(oldPassword.length==0)
			{
				alert("기본 비밀번호를 입력하세요");
				$("#oldPassword").focus();
				return false;
			}
			if(!confirmPassword)
			{
				alert("비밀번호 확인을 해주세요");
				return false;
			}
			if(newPassword.length==0)
			{
				alert("새로운 비밀번호를 입력하세요");
				$("#newPassword").focus();
				return false;
			}
			if(newPassword != newPasswordCf)
			{
				alert("비밀번호 확인이 다릅니다.");
				$("#newPasswordCf").focus();
				return false;
			}
			if(newName.length==0)
			{
				alert("새로운 이름을 입력하세요");
				$("#newName").focus();
				return false;
			}
			if(phoneNum.length==0)
			{
				alert("새로운 전화번호를 입력하세요");
				$("#phoneNum").focus();
				return false;
			}
		};
	</script>
	
</body>
</html>	