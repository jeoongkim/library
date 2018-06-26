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
			<h5>희망도서 신청</h5>
			<hr>
				<a href="book/book-apply.do" class="btn btn-primary btn-lg" role="button">희망도서 신청하기</a>
			     
			<h3>&nbsp;</h3>
				<img alt="dev" height="300" align="right" src="<c:url value='/img/hopeBook.png'/>">
				
			<h5><span class="glyphicon glyphicon-circle-arrow-right"></span>신청자격</h5>
			<ul>
				<li>도서관 회원만 신청이 가능합니다.</li>
			</ul>
			<h5>신청방법</h5>
			<ul>
				<li>희망 도서관 홈페이지 로그인 → 나의 공간 메뉴의 희망도서 클릭 또는 홈페이지 중앙의 희망도서신청 클릭</li>
			</ul>
			<h5>희망도서 신청 메뉴</h5>
			<ul>
				<li>희망도서 입력시 제목, 작가, 출판사, ISBN(국제표준도서번호), 발행년도 등을 정확하게 입력하시기 바랍니다.</li>
				<li>도서정보가 부정확할 경우 구입이 지연 또는 구입불가
			</ul>
			<h5>희망도서 신청권수</h5>
			<ul>
				<li>1인당 월 3권으로 제한</li>
			</ul>
			<h5>희망도서 진행절차(도서예약)</h5>
			<ul>
				<li>신청한 도서가 선정절차를 거쳐 구입되어 이용되기까지는 2~3주 간의 기간이 소요됩니다.</li>
				<li>희망도서가 자료실에서 이용가능한 상태가 되면, 신청자가 먼저 보실수 있도록 "도서예약"을 신청해 드립니다.</li>
				<li>단,"도서예약" 신청시 도서연체나, 예약권수가 초가할 경우에는 예약이 취소됨을 주의 부탁드립니다.</li>
			</ul>
			
			<h5>선정 제외 도서</h5>
			<ul>
				<li>문제집,수험서,중고등 참고서</li>
				<li>판타지,로맨스소설,무협지</li>
				<li>웹툰,라이트노벨 등 각종 만화류</li>
				<li>연감,백서,보고서 등 참고도서류</li>
				<li>영리목적ㆍ정치목적 자료</li>
				<li>미풍양속이나 정서 등에 문제를 유발할 수 있는 유해자료, 19세 이상 선정적인 도서</li>
				<li>정기간행물과 전자자료(전자책,DVD등 비도서) </li>
				<li>외국도서와 유아도서, 특정분야 전문도서</li>
				<li>고가도서(5만원이상),외국도서, 3권을 초과하는 시리즈 또는 전집도서, 기타다른기준을 적용하기 어려운 도서</li>
				<li>소장자료나 구입중 또는 정리중 도서, 신청 또는 주문중복도서</li>
			</ul>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	