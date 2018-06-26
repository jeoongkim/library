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
	
	<br><br><h1 align="center" >공지사항 수정</h1><br><br>
	<div class="container">
		<form action="<c:url value='/admin/notice-modify.do'/>" method="post">
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="writer">작성자 : ${ user.name }</label>
				<input type="hidden" value="${ user.userNo }" name="userNo">
			</div>
			<div class="form-group row">	
				<label class="col-sm-2 col-form-lable" for="title">제목</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="title" name="title" value="${ notice.title }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="description">내용</label>
				<div class="col-sm-10">
					<textarea name="description" cols="128" rows="20">${ notice.description }</textarea>
				</div>
			</div>
			<div align="center">
				<input class="btn btn-primary" type="submit" value="수정완료">	
				<input class="btn btn-primary" type="reset" value="입력한 내용 삭제"><br>
			</div>
			<input type="hidden" name="noticeNo" value="${ notice.noticeNo }">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
	
	
	<jsp:include page="../template/footer_admin.jsp" flush="false" />	

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>