<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-md navbar-dark bg-light">
	 	<div id="navbar" class="collapse navbar-collapse">
	 		<ul  class="navbar-nav mr-auto" >
	 			<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/admin/admin-index.do'/>" style="color: #000">관리자 홈</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/admin/loan-add.do'/>" style="color: #000">도서대출승인</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/admin/re-loan-add.do'/>" style="color: #000">예약대출승인</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/admin/loan-delete.do'/>" style="color: #000">도서대출반환 </a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/admin/book-add1.do'/>" style="color: #000">도서추가</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/admin/notice-add.do'/>" style="color: #000">공지사항추가</a>
				</li>
				
	 		</ul>
	 	</div>
</nav>

 	<footer class="bg-white text-center" style="color: #000">
	<p><img align="top" height="120" src="<c:url value='/img/Library_icon_main.png'/>">
	희망도서관 | 서울시 금천구 가산디지털1로 186. (가산동. 제이플라츠 5F. 515,516호) <br>
	Copyright &copy; 2018 희망도서관  All Rights Reserved.
</footer>