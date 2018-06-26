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
	<h3 align="center" >도서 수정 삭제</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		
		<br>
		<form action="<c:url value='/admin/book-modify1.do'/>" method="post" onsubmit="return confirm();">			
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="isbn">도서번호</label>
				<div class="col-sm-3" >
					<input class="form-control" type="text" id="isbn" name="isbn" placeholder="수정할 책 번호를 입력하세요"
					value="${ isbn }">
				</div>
					<input class="btn btn-primary" type="submit" value="도서수정" >	
			</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
		
		<table id="table1"></table>
		
	</div>
	<h1>&nbsp;</h1>
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script >
		function confirm()
		{
			var isbn= $("#isbn").val();
			if(isbn.length==0)
			{
				alert("ISBN을 입력하세요");
				$("#isbn").focus();
				return false;
			}
		}
	</script>
</body>
</html>