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
	<h3>대출ㆍ반납ㆍ연장ㆍ예약</h3>
	<br>
	<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 도서대출</h5>
	<ul>
		<li>대출자격: 희망도서관 회원</li>
		<li>대출권수 및 기간</li>
	</ul>
	<table border="1">
		<tr>
			<th>구분</th>
			<th>도서<br>(일반도서/세계/장애인자료)</th>
		</tr>
		<tr>
			<td>권수/기간</td>
			<td>5권 15일</td>
		</tr>
	</table>
	<h3>&nbsp;</h3>
	<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 도서반납</h5>
	<dl>
		<dt>반납장소</dt>
		 <dd>
		 	<ul>
		 		<li>도서: 자료실 대출반납 데스크(일반열람실), 무인반납기</li>
		 		<li>비도서: 정보검색실로 반납</li>
		 	</ul>
		 </dd>
		 <dt>무인반납기 운영</dt>
		 <dd>
		 	<ul>
		 		<li>설치 위치 : 희망도서관 후문 옆</li>
		 		<li>운영시간: 도서관 폐관 시간(펴일 21:00 ~ 익일 09:00, 휴일 18:00 ~ 익일 09:00,휴관일)</li>
		 		<li>주의사항: 무인반납기는 부록이 없는 도서만 반납가능합니다</li>
		 	</ul>
		 </dd>
	</dl>
	<h3>&nbsp;</h3>
	<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 대출연장</h5>
	<ul>
		<li>도서 대출 기간은 기본 15일이나, 1회 (7일)에 한해 연장 신청이 가능합니다. </li>
		<li>연체도서가 있는 경우 연장이 불가합니다.</li>
	</ul>
	<h3>&nbsp;</h3>
	<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 도서 예약</h5>
	<ul>
		<li>예약 가능권수: 1인당 3권</li>
		<li>'대출중'인 도서를 예약하면, 예약순서에 따라 도서가 반납되는 즉시 개별 통보(mail)합니다.</li>
		<li>예약자가 대출가능 통보를 받은 후 4일 이내에 대출하지 않으면 자동 취소됩니다.</li>
	</ul>
	<h3>&nbsp;</h3>
	<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 연쳬 반납/도서 재대출</h5>
	<ul>
		<li>도서를 연체반납시 관리자 권한으로 일정기간 대출이 중지됩니다.</li>
	</ul>
	<h3>&nbsp;</h3>
	<h5><img src="<c:url value='/img/arrow-thick-right-2x.png'/>"> 자료 분실(훼손)</h5>
	<ul>
		<li>자료의 분실이나 훼손시 동일 도서로 변상하여야 합니다.</li>
		<li>동일 도서로 변상할 수 없는 경우에는 대체자료를 구입하여 변상하여야 하며, 사전에 해당 자료실로 문의 바랍니다.</li>
	</ul>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	