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
	<nav class="navbar navbar-expand-md navbar-dark bg-white ">

		<!-- 네비게이션 브랜드 부분 -->
		<a class="navbar-brand" href="<c:url value='/index.do'/>"><img alt="index" height="42" src="<c:url value='/img/Library_icon_logo.png'/>"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
	 		<!-- 토글버튼 아이콘 -->
	 		<span class="navbar-toggler-icon"></span>
	 	</button>
		<div id="navbarCollapse" class="collapse navbar-collapse justify-content-end" >
			<ul class="navbar-nav ">
			<sec:authorize access="!hasRole('ADMIN') and !hasRole('USERS')"><!-- 비로그인 상태 -->
				<li class="nav-item">
					<a class="nav-link" href="<c:url value='/login.do'/>"><img alt="login" height="30" src="<c:url value='/img/login.png'/>"></a>
				</li>
				<li class="nav-item"><!-- 회원가입 -->
					<a class="nav-link" href="<c:url value='/join.do'/>"><img alt="join" height="27" src="<c:url value='/img/join.png'/>"></a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('USERS')"><!-- User 로그인 상태 -->
				<li class="nav-item">
					<a class="nav-link" href="<c:url value='/user-history.do'/>"><img alt="login" height="30" src="<c:url value='/img/MyPage.png'/>"></a>
				</li>
				<li class="nav-item"><!-- 회원가입 -->
					<a class="nav-link" href="<c:url value='/logout.do'/>"><img alt="join" height="30" src="<c:url value='/img/logout.png'/>"></a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN')"><!-- Admin 로그인 상태 -->
				<li class="nav-item">
					<a class="nav-link" href="<c:url value='/admin/admin-index.do'/>"><img alt="login" height="30" src="<c:url value='/img/admin.png'/>"></a>
				</li>
				<li class="nav-item"><!-- 회원가입 -->
					<a class="nav-link" href="<c:url value='/logout.do'/>"><img alt="join" height="30" src="<c:url value='/img/logout.png'/>"></a>
				</li>
			</sec:authorize>
			</ul>
		</div>
	</nav>
	 <jsp:include page="../template/header_admin.jsp" flush="false" />
	
	<div class="container">
		<h1>오디오북 수정</h1>
		<form action="<c:url value='/admin/book-modify3.do'/>" method="post" enctype="multipart/form-data">
			
			<div>
				<label>isbn: ${ audio.isbn }</label>
			</div>
			<div>
				<label class="col-sm-2 col-form-label" for="publicationDay">출판일</label>
				<input type="text" onfocus="(this.type='date')" id="publicationDay" name="publicationDay" 
				value=<fmt:formatDate value="${ audio.publicationDay }" type="both" pattern="yyyy-MM-dd" /> />
			</div>
			<div>
				<label class="col-sm-2 col-form-label" for="wearingDay">입고일</label>
				<input type="text" onfocus="(this.type='date')" id="wearingDay" name="wearingDay" 
				value=<fmt:formatDate value="${ audio.wearingDay }" type="both" pattern="yyyy-MM-dd" /> />
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="file">오디오 파일</label>
				<div>
				<input class="form-control" type="file" id="file" name="file">
			</div>
			</div>
			
			<div align="center">
				<input class="btn btn-primary" type="submit" value="수정완료">	
				<input class="btn btn-primary" type="reset" value="전체삭제">
			</div>
			
			<a href="<c:url value='/book/book-audio-list.do'/>">오디오 도서 목록으로 이동</a>
			<input type="hidden" name="isbn" value="${ audio.isbn }">
			<input type="hidden" name="audioNo" value="${ audio.audioNo }">
			<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		</form>
	</div>
	
	<!-- Footer -->
	<footer class="bg-white text-center" style="color: #000">
		<p><img align="top" height="120" src="<c:url value='/img/Library_icon_main.png'/>">
		희망도서관 | 서울시 금천구 가산디지털1로 186. (가산동. 제이플라츠 5F. 515,516호) <br>
		Copyright &copy; 2018 희망도서관  All Rights Reserved.
	</footer>

   
	</div>
	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>