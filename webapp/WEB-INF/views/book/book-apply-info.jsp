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
	 <jsp:include page="../template/header.jsp" flush="false" />
	
	<div class="container">
		<br><br><h1 align="center">희망도서 신청</h1><br><br>
		<h4>희망도서 신청안내</h4><br>
		<div>
			<ul>
				<li>희망도서 신청 대상은 우리도서관 관외대출회원만 가능합니다. </li>
				<li>희망도서는 월 3~4회 처리하고 있습니다.</li>
				<li>희망도서는 매월 1인 3권까지 신청할 수 있습니다.</li>
				<li>희망도서로 취소된 자료라도 양서의 경우, 정기구입 시 참고하겠습니다. </li>
			</ul>
		</div>
		<div>
			<br>
			<h6>아래의 자료는 구입을 지양하고 있으니 양해하여 주시기 바랍니다.</h6>
			<ul>
				<li>도서관 소장자료, 구입ㆍ정리 중인 자료, 품절 및 절판된 자료</li>
				<li>책에 대한 정보가 불명확한 자료, 시리즈, 전집, 문제집, 수험서, 중고생 참고서, 대학교재, 비도서 등 </li>
				<li>출판된 지 5년 이상된 자료</li>
				<li>판타지소설, 로맨스소설, 공포소설, 무협소설, 인터넷소설, 만화, 외국어도서(원서)</li>
				<li>특정 출판사의 자료만 집중적으로 신청한 경우</li>
				<li>특정 종교 및 단체의 관련 자료를 집중적으로 신청한 경우</li>
				<li>특정 주제분야 및 고가(50,000원 이상)의 자료, 3권 이상의 시리즈, 전집류</li>
				<li>만19세 이상 등급의 성인자료는 제한</li>
				<li>이 외에도 도서관자료로 부적합하다고 판단되는 자료</li>
			</ul>
		</div>
		<br><br><h4>희망도서 신청내역</h4><br>
		<form action="<c:url value='/book/book-apply.do'/>" method="get">
		<table class="table table-striped">
				<thead>
					<tr>
						<th></th>
						<th>isbn</th>
						<th>제목</th>
						<th>신청일</th>
						<th>진행상황</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ list }" var="book">
						<tr>
							<td></td>
							<td>${ book.isbn }</td>
							<td>${ book.title }</td>
							<td><fmt:formatDate value="${ book.applyDate }" type="both" pattern="yyyy-MM-dd" /></td>
							<td>
							 <c:choose>
							 	<c:when test="${ book.warehousing=='false' }">신청완료</c:when>
							 	<c:otherwise>입고완료</c:otherwise>
							 </c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br />
			<div align="center">
				<input class="btn btn-primary" type="submit" value="희망도서신청" />	
			</div>
		</form>
	</div>
	 
	<jsp:include page="../template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	