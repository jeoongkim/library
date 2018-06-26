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
	<h2 align="center" >희망도서 신청</h2>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/book/book-apply.do'/>" method="post" onsubmit="return confirm()">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="writer">신청자</label>
				<span class="col-sm-5">${ user.name }</span>
				<input type="hidden" value="${ user.userNo }" name="userNo">
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="title">신청 책 제목</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="title" name="title" placeholder="제목을 입력하세요">
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="author">신청 책저자</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="author" name="author" placeholder="저자를 입력하세요">
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="publisher">신청 책 출판사</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="publisher" name="publisher" placeholder="출판사를 입력하세요">
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="isbn">ISBN</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="isbn" name="isbn" placeholder="ISBN을 입력하세요">
				</div>
			</div>
			<h3>&nbsp;</h3>
			<div align="center">
				<input class="btn btn-primary" type="submit" value="작성완료">	
			</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
			<div>
			<h1>&nbsp;</h1>
			<hr>
				<div class="col-sm-8">
				<h3>&nbsp;</h3>
				<h4>YES24에 검색</h4>
				<h3>&nbsp;</h3>
					<script type="text/javascript">
					   $(function(){
					      $('#search').keydown(function( e ){
					         if( e.keyCode==13 ){
					            var val=$('#search').val();
					            window.location="http://localhost:8080/Library/book-apply.do?q="+val;
					         }
					      });
					   });
					</script>
					<script>
					  (function() {
					    var cx = '006929256569426607787:ekgjvona16k';
					    var gcse = document.createElement('script');
					    gcse.type = 'text/javascript';
					    gcse.async = true;
					    gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
					    var s = document.getElementsByTagName('script')[0];
					    s.parentNode.insertBefore(gcse, s);
					  })();
					</script>
					<gcse:search></gcse:search>

				</div>
			</div>
			
	</div>
	 <h1>&nbsp;</h1>
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script>
		function confirm() {
		    
		    var title = $("#title").val();
		    var author = $("#author").val();
		    var publisher = $("#publisher").val();
		    var isbn = $("#isbn").val();
		    
		    if(title.length == 0){
		        alert("제목을 입력하세요"); 
		        $("#title").focus();
		        return false;
		    }
		    if(author.length == 0){
		        alert("저자를 입력하세요"); 
		        $("#author").focus();
		        return false;
		    }
		    if(publisher.length == 0){
		        alert("출판사를 입력하세요");
		        $("#publisher").focus();
		        return false;
		    }
		    if(isbn.length == 0){
		        alert("ISBN을 입력하세요");
		        $("#isbn").focus();
		        return false;
		    }
		}
	</script>
</body>
</html>	