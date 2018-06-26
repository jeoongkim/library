<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar navbar-expand-md navbar-dark bg-light">
	 	<div id="navbar" class="collapse navbar-collapse">
	 		<ul  class="navbar-nav mr-auto" >
	 			<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/index.do'/>" style="color: #000">홈</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/book/book-apply-info.do'/>" style="color: #000">희망도서신청</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="#" style="color: #000">도서검색</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/user/volunteer-list.do'/>" style="color: #000">봉사활동 신청</a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/user/chat-start.do'/>" style="color: #000">독서토론방</a>
				</li>
				
	 		</ul>
	 	</div>
</nav>
 	<footer class="bg-white text-center" style="color: #000">
	<p><img align="top" height="120" src="<c:url value='/img/Library_icon_main.png'/>">
	희망도서관 | 서울시 금천구 가산디지털1로 186. (가산동. 제이플라츠 5F. 515,516호) <br>
	Copyright &copy; 2018 희망도서관  All Rights Reserved.
</footer>