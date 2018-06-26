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
					<a class="nav-link" href="<c:url value='/user/user-history-date.do'/>"><img alt="login" height="30" src="<c:url value='/img/MyPage.png'/>"></a>
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
	</nav>	<!-- 고정 nav End-->
 <nav class="navbar navbar-expand-md navbar-light " style="background-color:#e3f2fd; ">
	 	<div id="navbar" class="collapse navbar-collapse">
	 		<ul class="navbar-nav mr-auto">
	 			<li class="nav-item dropdown">
	 				<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">도서관 안내</a>
	 				<div class="dropdown-menu">
	 					<a class="dropdown-item" href="<c:url value='/introduce_home.do'/>">도서관 프로젝트 소개</a>
	 					<a class="dropdown-item" href="<c:url value='/introduce_status.do'/>">도서관 프로젝트 일정</a>
	 					<a class="dropdown-item" href="<c:url value='/introduce_time.do'/>">도서관 이용시간</a>
	 					<a class="dropdown-item" href="<c:url value='/introduce_user.do'/>">대출ㆍ반납ㆍ연장ㆍ예약</a>
	 				</div>
	 			</li>
	 			<li class="nav-item dropdown">
	 				<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">도서관 이용</a>
	 				<div class="dropdown-menu">
	 					<a class="dropdown-item" href="<c:url value='/introduce_apply_hope_book.do'/>">희망도서 신청</a>
	 					<a class="dropdown-item" href="<c:url value='/book-list.do'/>">도서목록</a>
	 				</div>
	 			</li>
	 			
	 			<li class="nav-item dropdown">
	 				<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">열람실</a>
	 				<div class="dropdown-menu">
	 					<a class="dropdown-item" href="<c:url value='/user/readingRoom.do'/>">좌석정보ㆍ연장ㆍ반납</a>
	 					<a class="dropdown-item" href="<c:url value='/user/readingRoom-add.do'/>">좌석지정</a>
	 					<a class="dropdown-item" href="<c:url value='/user/readingRoom-select.do'/>">좌석조회</a>
	 				</div>
	 			</li>
	 			<li class="nav-item">
	 				<a class="nav-link" href="<c:url value='/notice-list.do'/>" style="color: #000">공지사항</a>
	 			</li>
	 			<li class="nav-item dropdown">
	 				<a class="nav-link dropdown-toggle" href="#" id="dropdown" data-toggle="dropdown" style="color: #000">MyPage</a>
	 				<div class="dropdown-menu">
	 					<a class="dropdown-item" href="<c:url value='/user/user-history-date.do'/>">회원 대출 기록 </a>
	 					<a class="dropdown-item" href="<c:url value='/user/user-loan-history.do'/>">회원 대출 연장 </a>
	 					<a class="dropdown-item" href="<c:url value='/user/user-reserv-list.do'/>">회원 예약 내역 </a>
	 					<a class="dropdown-item" href="<c:url value='/user/users-modify.do'/>">회원정보수정</a>
	 					<a class="dropdown-item" href="<c:url value='/book/book-apply-info.do'/>">희망도서 신청 내역</a>
	 				</div>
	 			</li>
	 		
	 		</ul>
	 		
	 		<form action="<c:url value='/book-search.do'/>"  class="form-inline my-2 my-md-0" method="get">
				<input class="form-control mr-sm-2" type="text" name="input" id="input" placeholder="도서 검색">
				<input class="btn btn-outline-primary my-2 my-sm-0"  type="submit" value="검색">
			</form>
	 	</div>
	 </nav>