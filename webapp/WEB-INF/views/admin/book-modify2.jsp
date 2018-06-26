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
	<h3 align="center" > &#60; 도서 수정 &#62;</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/admin/book-modify2.do'/>" method="post" enctype="multipart/form-data" onsubmit="return confirm();">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="title">책 제목</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" id="title" name="title" value="${ book.title }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="author">저자</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" id="author" name="author" value="${ book.author }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="publisher">출판사</label>
				<div class="col-sm-7">
					<input class="form-control" type="text" id="publisher" name="publisher" value="${ book.publisher }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="groupName">도서분류</label>
				<div class="col-sm-5" >
					<select name="groupName">
						<option value="A">A : 어른</option>
						<option value="C">C : 아동</option>
						<option value="O">O : 오디오</option>
					</select>
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
				<input class="btn btn-primary" type="submit" value="수정완료">	
				<input class="btn btn-secondary" type="reset" value="이전상태로">
			</div>
				<input type="hidden" name="isbn" value="${ book.isbn }">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
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
			var title = $("#title").val();
			var author = $("#author").val();
			var publisher = $("#publisher").val();

			if(title.length==0)
			{
				alert("책 제목을 입력하세요");
				$("#title").focus();
				return false;
			}
			if(author.length==0)
			{
				alert("저자를 입력하세요");
				$("#author").focus();
				return false;
			}
			if(publisher.length==0)
			{
				alert("출판사를 입력하세요");
				$("#publisher").focus();
				return false;
			}
		}
	</script>
</body>
</html>