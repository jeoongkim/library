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
	<h3>인사말</h3><hr>
	<h3>&nbsp;</h3>
	<p>희망도서관은 2018년 개관  [책읽는 개발자]을 구현하기 위하여 <br>
	3명의 개발자가 모여 시작한 작은 프로젝트입니다.  </p>
	<p>아울러 4차 산업혁명시대를 맞아 개발자 누구나 창의성과 통찰력을 키우고, <br>
	자신의 꿈을 이루고, 삶을 누리는데 도움이 되도록 '책으로 개발자의 힘을 키우는' 지식정보 허브로서의 역할에 더욱 매진하겠습니다.</p>
	<p>여러분의 많은 관심과 사랑을 부탁드립니다.</p>
	<p>감사합니다.</p>
	<h1>&nbsp;</h1>
	<div class="container">
		<div class="row" >
			<div class="col">
			</div>
			<div class="col">
				<img alt="dev" height="250" align="top" src="<c:url value='/img/dev.png'/>">
			</div>
			<div class="col">
			</div>
		</div>
	</div>
	<h1>&nbsp;</h1>
	<h1>&nbsp;</h1>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	