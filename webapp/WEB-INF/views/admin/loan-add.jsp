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
	<h3 align="center">&#60; 도서 대출 페이지 &#62;</h3>
	<h1>&nbsp;</h1>
	<div class="container">
		<form action="<c:url value='/admin/loan-add.do'/>" method="post" onsubmit="return confirm();">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="userNo">회원번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="userNo" name="userNo" placeholder="회원번호를 입력하세요">
				</div>
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="bookNo">도서번호</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" id="bookNo" name="bookNo" placeholder="도서 번호를 입력해주세요">
				</div>
			</div>
			
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="bookNo">대출일</label>
				<div class="col-sm-10">
					<fmt:formatDate pattern = "yyyy-MM-dd" value = "${ loan.loanDate }" />
				</div>
			</div>
			
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="bookNo">반납기한 일</label>
				<div class="col-sm-10">
					<fmt:formatDate pattern = "yyyy-MM-dd" value = "${ loan.deadline }" />
				</div>
			</div>
			
			<div align="center">
				<input class="btn btn-primary" type="submit" value="대출승인">	
				<input class="btn btn-outline-primary" type="reset" value="입력한 내용 지우기">	
			</div>
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
	
	<div class="modal fade" id="modal-add-user" tabindex="-1" role="dialog">
	 	<div class="modal-dialog">
	 		<div class="modal-content">
	 			<!-- 창 헤더 부분 -->
				<div class="modal-header">
					<h5 class="modal-title" id="modal">회원찾기</h5>
					<button class="close" type="button" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>	 
				<!-- 창 body 부분 -->
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="email">이메일</label>
							<input class="form-control " type="text" id="email" name="email">
							<input class="btn btn-primary "  type="submit" value="검색" >
							
						</div>
						<div class="form-group">
							<label for="content01">이름</label>
							<input class="form-control " type="text" id="name" name="name">
							<input class="btn btn-primary" type="submit" value="검색">
						</div>
						<div class="form-group">
							<label for="content01">전화번호</label>
							<input class="form-control " type="text" id="name" name="name">
							<input class="btn btn-primary" type="submit" value="검색">
						</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
						</div>
					</form>
				</div>		
	 		</div>
	 	</div>
	 </div>
	
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
	<script>
		function findMember()
		{
			var userNo = $("#userNo").val();

			$.ajax({
				async: true,
				type: 'GET',
				url: "userNoCheck.do"+"?userNo="+userNo,
				dataType:"json",
				contentType: "application/json charset=UTF-8",
				success: function(data)
				{
					if(data.userNo==null)
					{
						alert("회원번호를 다시 확인해주세요")
					}
				},
				error: function(error)
				{
					alert("error : "+error);
				}
			});
		}
		function findBookNum()
		{
			var bookInfo = $("#bookNo").val();

			$.ajax({
				async: true,
				type: 'GET',
				url : "../book-search-ajax.do"+"?bookInfo="+bookInfo,
				dataType:"json",
				contentType:"application/json charset=UTF-8",
				success: function(data)
				{
					$('#isbn').val(data.list.isbn);
				},
				error: function(error)
				{
					alert("검색 결과가 없습니다.");
				}
			});
		}
		function confirm()
		{
			var userNo = $("#userNo").val();
			var bookNo = $("#bookNo").val();

			if(userNo.length==0)
			{
				alert("회원번호를 입력하세요");
				$("#userNo").focus();
				return false;
			}
			if(bookNo.length==0)
			{
				alert('도서번호를 입력하세요');
				$("#bookNo").focus();
				return false;
			}
		}
	</script>
</body>
</html>