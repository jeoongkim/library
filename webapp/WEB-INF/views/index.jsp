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
		<img style='height: 100%; width: 100%; object-fit: contain' alt="main" src="<c:url value='/img/Library_main_1.png'/>">
	 <nav class="navbar navbar-expand-md navbar-light" style="background-color:#e3f2fd; ">
		<div id="navbar" class="navbar-collapse collapse justify-content-center">
			<ul class="navbar-nav navbar-center">
	 			<li class="nav-item ">
	 				<a class="nav-link" data-toggle="modal" href="#modal-add-user"><img alt="home" height="100" src="<c:url value='/img/quick_01.png'/>"><br>&emsp;도서검색</a>
	 			</li>
	 			<li class="nav-item ">
	 				<a class="nav-link" href="<c:url value='/book-list.do'/>"><img alt="home" height="100" src="<c:url value='/img/quick_02.png'/>"><br>&emsp;도서목록</a>
	 			</li>
	 			<li class="nav-item ">
	 				<a class="nav-link" href="<c:url value='/notice-list.do'/>"><img alt="home" height="100" src="<c:url value='/img/quick_03.png'/>"><br>&emsp;공지사항</a>
	 			</li> 
	 			<li class="nav-item ">
	 				<a class="nav-link" href="<c:url value='/book-new.do'/>"><img alt="home" height="100" src="<c:url value='/img/quick_05.png'/>"><br>&emsp;신착도서</a>
	 			</li>
	 			<li class="nav-item ">
	 				<a class="nav-link" href="<c:url value='/user/volunteer-list.do'/>"><img alt="home" height="100" src="<c:url value='/img/quick_06.png'/>"><br>&emsp;봉사활동</a>
	 			</li>
	 			<li class="nav-item ">
	 				<a class="nav-link" href="<c:url value='/book-audio-list.do'/>"><img alt="home" height="100" src="<c:url value='/img/quick_07.png'/>"><br>&emsp;오디오북</a>
	 			</li>
	 		</ul>
		</div>
	</nav>
	<br><br>
		<div class="container">
	 	<div class="row">
	 		<div class="col">
			<h3>대출이 많은 도서</h3>	
			<table class="table table-striped">
				<thead>
					<tr>
						<th>도서</th>
						<th>도서 제목</th>
					</tr>
				</thead>
				<c:forEach items="${ history }" var="list">
					<tbody>
						<tr>
							<td><img width="100" height="100"
								src="${ uploadPath }/${ list.bookList.book.attachment }"></td>
						
							<td><a href="<c:url value='/book-detail.do?isbn=${ list.bookList.book.isbn }'/>">${ list.bookList.book.title }</a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			</div>
	 		<div class="col">
			<h3>공지사항</h3>	
			<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성일</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ list }" var="notice">
					<tr>
						<td>${ notice.noticeNo }</td>
						<td><a href="<c:url value='/notice-detail.do?noticeNo=${ notice.noticeNo }'/>">${ notice.title }</a></td>
						<td><fmt:formatDate value="${ notice.regDate }" type="both" pattern="yyyy-MM-dd" /></td>
						<td>${ notice.users.name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	 		</div>
	 	</div>
	 </div>
	 <div class="modal fade" id="modal-add-user" tabindex="-1" role="dialog">
	 	<div class="modal-dialog">
	 		<div class="modal-content">
	 			<!-- 창 헤더 부분 -->
				<div class="modal-header">
					<h5 class="modal-title" id="modal">도서검색</h5>
					<button class="close" type="button" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>	 
				<!-- 창 body 부분 -->
				<div class="modal-body">
					<form action="<c:url value='/book-search.do'/>"  class="form-inline my-2 my-md-0" method="get">
						<div class="form-group">
							<input class="form-control mr-sm-2" type="text" name="input" id="input" placeholder="도서 검색">
							<input class="btn btn-outline-primary my-2 my-sm-0"  type="submit" value="검색">
						</div>
					</form>
				</div>		
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