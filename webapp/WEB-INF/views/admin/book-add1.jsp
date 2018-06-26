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
	<h3 align="center">&#60; 도서 추가 &#62;</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/admin/book-add1.do'/>" method="post" enctype="multipart/form-data" onsubmit="return confirm()">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="isbn">ISBN</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="isbn" name="isbn" placeholder="책 ISBN을 입력하세요">
				</div>
					<input class="btn btn-info" type="button" value="중복확인" onclick="isbnConfirm();">
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="title">책 제목</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="title" name="title" placeholder="책 제목을 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="author">저자</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="author" name="author" placeholder="저자를 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="publisher">출판사</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="publisher" name="publisher" placeholder="출판사를 입력하세요">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="groupName">도서분류</label>
				<div class="col-sm-5">
					<select name="groupName" id="groupName">
					 	<option selected disabled>선택해주세요</option>
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
			<div align="center">
				<input class="btn btn-primary" id="button" type="submit" value="제출하기">
				<input class="btn btn-secondary" type="reset" value="입력한 내용 지우기">	
			</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
	
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<script>
		var isbnCheck= false;
		function isbnConfirm()
		{
			var isbn = $("#isbn").val();

			$.ajax({
				async: true,
				type: 'GET',
				url: "isbnCheck.do"+"?isbn="+isbn,
				dataType: "json",
				contentType: "application/json charset=UTF-8",
				success: function(data)
				{
					alert("ISBN이 중복됩니다. ");
					$('#isbn').val(data.book.isbn);
					$('#title').val(data.book.title);
					$('#author').val(data.book.author);
					$('#publisher').val(data.book.publisher);
					isbnCheck= true;
				},
				error: function(error)
				{
					alert("신규등록 책입니다.");	
					isbnCheck= true;
				}
			});
		}
		
		function confirm()
		{
			var isbn = $("#isbn").val();
			var title = $("#title").val();
			var author = $("#author").val();
			var publisher = $("#publisher").val();
			var groupName = $("#groupName").val();

			if(isbn.length==0)
			{
				alert("ISBN을 입력하세요");
				$("#isbn").focus();
				return false;
			}
			if(!isbnCheck)
			{
				alert("ISBN 중복확인후 등록가능합니다.")
				return false;
			}
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
			if(groupName.length != 1)
			{
				alert("도서분류를 선택해주세요");
				$("#groupName").focus();
				return false;
			}
		}
	</script>	
</body>
</html>