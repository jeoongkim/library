<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 <nav class="navbar navbar-expand-md navbar-light " style="background-color:#e3f2fd; ">
	 	<div id="navbar" class="collapse navbar-collapse">
	 		<ul class="navbar-nav mr-auto">
	 			<li class="nav-item dropdown">
	 				<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">도서 관리</a>
	 				<div class="dropdown-menu">
	 					<a class="dropdown-item" href="<c:url value='/admin/book-add1.do'/>">도서 추가</a>
						<a class="dropdown-item" href="<c:url value='/admin/book-modify1.do'/>">도서 수정</a>
						<a class="dropdown-item" href="<c:url value='/admin/book-apply-list.do'/>">도서 신청 목록</a>
	 				</div>
	 			</li>
	 			<li class="nav-item dropdown">
	 				<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">대출 관리</a>
	 				<div class="dropdown-menu">
	 					<a class="dropdown-item" href="<c:url value='/admin/loan-add.do'/>">도서 대출 승인</a>
						<a class="dropdown-item" href="<c:url value='/admin/re-loan-add.do'/>">예약 대출 승인</a>
						<a class="dropdown-item" href="<c:url value='/admin/loan-delete.do'/>">도서 대출 반환</a>
	 				</div>
	 			</li>
	 			
	 			
	 			<li class="nav-item">
					<a class="nav-link" href="<c:url value='/admin/users-list.do'/>" style="color: #000">회원목록 보기</a>
				</li>
	 			<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">공지사항 관리</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="<c:url value='/admin/notice-add.do'/>" style="color: #000">공지사항 추가</a>
					</div>
				</li>
	 			
	 			<li class="nav-item">
	 				<a class="nav-link" href="<c:url value='/book/book-apply.do'/>"></a>
	 			</li>
	 		</ul>
	 		<form action="<c:url value='/book-search.do'/>"  class="form-inline my-2 my-md-0" method="get">
				<input class="form-control mr-sm-2" type="text" name="input" id="input" placeholder="도서 검색">
				<input class="btn btn-outline-primary my-2 my-sm-0"  type="submit" value="검색">
			</form>
	 	</div>
	 </nav>