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
		div.joinForm{
			width: 800px;
		}
	</style>
		
</head>
<body>
	<div class="container" style="margin: 100">
	 <jsp:include page="template/header.jsp" flush="false" />
	 <h1>&nbsp;</h1>	
	<h3 align="center" >회원 가입 페이지</h3>
	<h1>&nbsp;</h1>
	<div class="joinForm">
		<form action="<c:url value='/join.do'/>" method="post" enctype="multipart/form-data" onsubmit="return confirm()" >
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="email">이메일</label>
				<div class="col-sm-5">
					<input class="form-control" type="email" id="email" name="email" placeholder="email를 입력하세요">
				</div>
				<div class="col-sm-2">
					<input class="btn btn-primary" type="button"  onclick="checkEmail();" value="중복확인">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="password">비밀번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="password" name="password" placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="password">비밀번호확인</label>
				<div class="col-sm-5">
					<input class="form-control" type="password" id="passwordCf" name="passwordCf" placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="name" >이름</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="name" name="name" placeholder="이름을 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="phoneNum">휴대전화번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="tel" id="phoneNum" name="phoneNum" required pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" title="010-####-####" placeholder="전화번호를 입력하세요 (ex:010-####-####)">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="attachment">첨부파일</label>
				<div>
					<input class="form-control" type="file" id="attachment" name="attachment">
				</div>
			</div>
			<div align="center">
				<input class="btn btn-primary" id="button" type="submit" value="회원가입" >	
			</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>	
	<jsp:include page="template/footer.jsp" flush="false" />
	</div>	
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script> 
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script>
		var buttonCheckEmail =false;
		function checkEmail()
		{
			var email= $('#email').val();

			$.ajax({
				async: true,
				type: 'GET',
				url: "emailCheck.do"+"?email="+email,
				dataType : "json",
				contentType: "application/json charset=UTF-8",
				success: function(data)
				{
					if(data.cnt>0)
					{
						alert("아이디가 존재합니다. 다른 아이디를 입력해주세요");
					}else{
						alert("사용가능한 아이디입니다.");
						buttonCheckEmail=true;
					}
				},
				error : function(error)
				{
					alert("error: "+error);
				}
			});
		}
		function confirm() {
		    
		    var email = $("#email").val();
		    var password = $("#password").val();
		    var passwordCf = $("#passwordCf").val();
		    var name = $("#name").val();
		    var phoneNum = $("#phoneNum").val();
		    
		    if(email.length == 0){
		        alert("이메일을 입력해 주세요"); 
		        $("#email").focus();
		        return false;
		    }
		    if(!buttonCheckEmail)
			{
				alert("중복확인이 필요합니다.");
				return false;
			}
		    if(password.length == 0){
		        alert("비밀번호를 입력해 주세요"); 
		        $("#password").focus();
		        return false;
		    }
		    if(password != passwordCf){
		        alert("비밀번호가 서로 다릅니다. 비밀번호를 확인해 주세요."); 
		        $("#passwordCf").focus();
		        return false; 
		    }
		    if(name.length == 0){
		        alert("이름을 입력해주세요");
		        $("#name").focus();
		        return false;
		    }
		    if(phoneNum.length == 0){
		        alert("전화번호를 입력해주세요");
		        $("#phoneNum").focus();
		        return false;
		    }
		   
		}
		 
	</script>
	
</body>
</html>	