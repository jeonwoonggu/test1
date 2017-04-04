<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/template/header.jsp"/>
	<div class="container">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th class="no">번호</th>
						<th class="category" onclick="">분류</th>
						<th class="title">제목</th>
						<th class="name">작성자</th>
						<th class="date" onclick="">작성일</th>
						<th class="hits" onclick="">조회</th>
						<th class="likes" onclick="">공감</th>
					</tr>
				</thead>
				<tbody>
			<c:forEach var="bvo" items="${requestScope.lvo.list}">				
			<tr>
			    <td>${bvo.board_no }</td>
			    <td>${bvo.category }</td>		
				<td>
				<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=showContent&no=${bvo.board_no }">
				${bvo.title }</a>
				</c:when>
				<c:otherwise>
				${bvo.title }
				</c:otherwise>
				</c:choose>
				</td>
				<td>${bvo.memberVO.name }</td>
				<td>${bvo.timePosted }</td>
				<td>${bvo.hits }</td>
				<td>${bvo.likes }</td>
			</tr>	
			</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br></br>	
	<a href="${pageContext.request.contextPath}/board/write.jsp">
	<img src="${pageContext.request.contextPath}/img/write.jpg" border="0" width="60px" style="margin-left: 80%"></a>
	<%-- <c:if test="${sessionScope.mvo!=null}">
	<a href=""><img src="${pageContext.request.contextPath}/img/write.jsp" border="0"></a>
	</c:if> --%>
	<br><br>	
<p class="paging" style="margin-left: 50%">
	<c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>
	<c:if test="${pb.previousPageGroup}">
	<a href="DispatcherServlet?command=list&pageNo=${pb.startPageOfPageGroup-1}">
	<!-- <img src="img/left_arrow_btn.gif"> -->
	◀&nbsp; </a>	
	</c:if>
	<c:forEach var="i" begin="${pb.startPageOfPageGroup}" 
	end="${pb.endPageOfPageGroup}">
	<c:choose>
	<c:when test="${pb.nowPage!=i}">
	<a href="DispatcherServlet?command=list&pageNo=${i}">${i}</a> 
	</c:when>
	<c:otherwise>
	${i}
	</c:otherwise>
	</c:choose>
	&nbsp;
	</c:forEach>	 
	<c:if test="${pb.nextPageGroup}">
	<a href="DispatcherServlet?command=list&pageNo=${pb.endPageOfPageGroup+1}">
	▶<!-- <img src="img/right_arrow_btn.gif"> --></a>
	</c:if>
	</p>
</body>
</html>