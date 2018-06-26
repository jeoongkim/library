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
	
	 <jsp:include page="template/header.jsp" flush="false" />
	 <h1>&nbsp;</h1>
	<h3>프로젝트 일정</h3>
	<h3>&nbsp;</h3>
	<nav class="navbar navbar-expand-sm bg-white ">
	 <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="#">연혁</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="#">자료 현황</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="#">시설 현황</a>
	    </li>
  	</ul>
  	</nav>
	<hr>
	<p>개발자 중심의 도서관이 탄생하였습니다.</p>
	<p>개발 정보 중심, 서울의 개발자 대표 도서관으로 자리매김할 것입니다.</p>
	<p>2018 - 3월 16일  - 세미프로젝트 착수</p>
	<p>2018 - 3월 23일  - 세미프로젝트 완성</p>
	<p>2018 - 5월 9일  - 파이널프로젝트 착수</p>
	<p>2018 - 6월 1일  - 파이널프로젝트 완성</p>
	 <div class="container">
		<div class="row" >
			<div class="col">
			</div>
			
			<div class="col">
				<img alt="dev" height="400" align="top" src="<c:url value='/img/deadline.jpg'/>">
			</div>
			
		</div>
	</div>
	<h1>&nbsp;</h1>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	