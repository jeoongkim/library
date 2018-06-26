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
		<h1>&nbsp;</h1>
		<h3 align="center">&#60; 도서 상세 보기 &#62;</h3>
		<h1>&nbsp;</h1>
		<h3>예약하기</h3>
		<ul>
			<li>예약 가능권수: 1인당 3권</li>
			<li>'대출중'인 도서를 예약하면, 예약순서에 따라 도서가 반납되는 즉시 개별 통보(mail)합니다.</li>
			<li>예약자가 대출가능 통보를 받은 후 4일 이내에 대출하지 않으면 자동 취소됩니다.</li>
		</ul>
		<form action="<c:url value='/book/reservation-add.do'/>" method="get">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>isbn</th>
						<th>책 제목</th>
						<th>저자</th>
						<th>출판사</th>
						<th>파일</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${ book.isbn }</td>
						<td>${ book.title }</td>
						<td>${ book.author }</td>
						<td>${ book.publisher }</td>
						<td>
							<c:if test="${ !empty imgPath }">
								<img src="${ imgPath }" width="100" alt="이미지 파일 출력위치">
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div align="center">
				<input class="btn btn-primary col-sm-1" type="submit" value="예약">
			</div>
			
			<input type="hidden" name="isbn" value="${ book.isbn }"> 
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
		<hr>
		<h1>&nbsp;</h1>
		<h3 align="center">&#60; 별점 평가 &#62;</h3>
		<h1>&nbsp;</h1>
		<ul>
			<li>도서에 대하여 자유롭게 별점 평가 할 수 있습니다.</li>
			<li>한번만 평가 가능합니다.</li>
		</ul>
		<div align="center">
		<form action="<c:url value='score.do'/>" method="get">
			<h1 class="display-5"> ${ bookscore } 점</h1>
			<c:if test="${ !empty countUsers }">
				<p>이책을 평가한 사람은 ${ countUsers }명 입니다.</p>
			</c:if>
			<c:if test="${ empty countUsers }">
				<p>이책을 평가한 사람은 0명 입니다.</p>
			</c:if>
				<div>
					<sec:authorize access="hasRole('ADMIN') or hasRole('USERS')">
						<select name='bookScore'>
							<option value="" selected disabled>-------------</option>
							<option value='10'>★★★★★　</option>
							<option value='9'>★★★★☆　</option>
							<option value='8'>★★★★　</option>
							<option value='7'>★★★☆　</option>
							<option value='6'>★★★　　</option>
							<option value='5'>★★☆　　</option>
							<option value='4'>★★　　　</option>
							<option value='3'>★☆　　　</option>
							<option value='2'>★　　　　</option>
							<option value='1'>☆　　　　</option>
							<option value='0'>　　　　　</option>
						</select>
						<input type="hidden" name="isbn" value="${ book.isbn }"> 
						<input type="submit" value="별점 주기">	
					</sec:authorize>
				</div>
			</form>
		</div>
		
		<hr>
		<h1>&nbsp;</h1>
		<h3 align="center">&#60; 도서 목록 &#62;</h3>
		<h1>&nbsp;</h1>
		<ul>
			<li>동일 ISBN의 모든 책을 볼 수 있습니다.</li>
			
		</ul>
		<h3>&nbsp;</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>책 번호</th>
						<th>입고일</th>
						<th>출판일</th>
						<th>대출여부</th>
					</tr>
				</thead>
				<c:forEach items="${ bookList }" var="item">
				<tbody>
					<tr>
						<td>${ item.bookNo }</td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${ item.wearingDay }" /></td>
						<td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${ item.publicationDay }" /></td>
						<td>${ item.whether }</td>
					</tr>

				</tbody>
				</c:forEach>
			</table>
			<h1>&nbsp;</h1>
			<h1>&nbsp;</h1>
			<div align="center">
				<a class="btn btn-primary" href="<c:url value='/book-list.do'/>">도서 목록으로 이동</a> 
			</div>
			<h1>&nbsp;</h1>
		
	

	</div>
	 
	<jsp:include page="template/footer.jsp" flush="false" />

	</div>	
	<script src="<c:url value='/js/popper.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>	
</body>
</html>	