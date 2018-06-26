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
	
	<div class="container">
		<div class="row" >
			<div class="col">
				<h1>&nbsp;</h1>
				<h3>도서관 이용시간</h3>
				<hr>
				<h1>&nbsp;</h1>
				<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 이용대상</h5>
				<ul>
					<li>희망도서관은 도서관 자료 열람을 원하는 누구나 이용이 가능합니다.</li>
					<li>단,대출/예약/보존서고 이용,PC예약 등 일부 서비스는 대출증을 발급받은 회원만 이용이 가능합니다.</li>
				</ul>
				<h1>&nbsp;</h1>
				<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 이용시간 안내</h5>
				<h5>&nbsp;</h5>
				<table border="1">
					<tr>
						<th>자료실</th>
						<th>평일(화~금)</th>
						<th>주말(토,일)</th>
					</tr>
					<tr>
						<td>어린이자료실</td>
						<td rowspan="2">09:00 ~ 21:00</td>
						<td rowspan="2">09:00 ~ 18:00</td>
					</tr>
					<tr>
						<td>종합자료실</td>
					</tr>
					<tr>
						<td>정보검색실</td>
						<td rowspan="2" colspan="2">09:00 ~ 18:00</td>
					</tr>
					<tr>
						<td>열람실</td>
					</tr>
				</table>
				<h1>&nbsp;</h1>
				<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 휴관일 안내</h5>
				<ul>
					<li>매주 월요일</li>
					<li>법정공휴일 및 관공서의 공휴일</li>
					<li>기타관장이 필요하다고 인정하는 날</li>
				</ul>
				<h1>&nbsp;</h1>
				<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 5월 휴관일</h5>
				<p> 5일		7일		14일		21일			22일			28일
				<h1>&nbsp;</h1>
			</div>
			<div class="col">
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<h1>&nbsp;</h1>
				<img alt="dev" height="400" align="bottom" src="<c:url value='/img/time.jpg'/>">
			</div>
			
		</div>
	</div>
	
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	