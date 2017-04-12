<%@page import="model.ReportDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
						<th> 번호 </th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=categoryList&cate=추천">추천</a>/<a href="${pageContext.request.contextPath}/DispatcherServlet?command=categoryList&cate=비추천">비추천</a></th>
						<th> 업종 </th>
						<th> 제목 </th>
						<th> 작성자 </th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=list">작성일▼</a></th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=hitsList">조회▼</a></th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=sympathyList">공감▼</a></th>
					</tr>
				</thead>
				<tbody>
			<c:forEach var="svo" items="${requestScope.svo.list}">			
			<tr>
			    <td>${svo.board_no }</td>
			    <td>${svo.category }</td>
			    <td>${svo.job }</td>		
				<td>
				<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=showContent&no=${svo.board_no }&memberId=${sessionScope.mvo.member_Id}">
				${svo.title } (${svo.replycount })</a>
				</c:when>
				<c:otherwise>
				${svo.title } (${svo.replycount })
				</c:otherwise>
				</c:choose>
				</td>
				<td>${svo.memberVO.nickName }</td>
				<td>${svo.timePosted }</td>
				<td>${svo.hits }</td>
				<td>${svo.likes }</td>
			</tr>	
			</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br></br>	
	<c:if test="${sessionScope.mvo!=null}">
	<a href="${pageContext.request.contextPath}/board/write.jsp" style="margin-left: 85%">
	<img src="${pageContext.request.contextPath}/img/write.jpg" border="0" width="60px" ></a>
	</c:if>
	<br><br>	
	<p class="paging" style="margin-left: 50%">
	<c:set var="pb" value="${requestScope.svo.pagingBean}"></c:set>
	<c:if test="${pb.previousPageGroup}">
	<a href="DispatcherServlet?command=search&pageNo=${pb.startPageOfPageGroup-1}">
	<!-- <img src="img/left_arrow_btn.gif"> -->
	◀&nbsp; </a>	
	</c:if>
	<c:forEach var="i" begin="${pb.startPageOfPageGroup}" 
	end="${pb.endPageOfPageGroup}">
	<c:choose>
	<c:when test="${pb.nowPage!=i}">
	<a href="DispatcherServlet?command=search&pageNo=${i}">${i}</a> 
	</c:when>
	<c:otherwise>
	${i}
	</c:otherwise>
	</c:choose>
	&nbsp;
	</c:forEach>	 
	<c:if test="${pb.nextPageGroup}">
	<a href="DispatcherServlet?command=search&pageNo=${pb.endPageOfPageGroup+1}">
	▶<!-- <img src="img/right_arrow_btn.gif"> --></a>
	</c:if>
	</p>
</body>
</html>