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
	<c:import url="/template/header.jsp"></c:import>
	<div class="container">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th class="no">번호</th>
						<th class="category">분류</th>
						<th class="title">제목</th>
						<th class="name">작성자</th>
						<th class="date">작성일</th>
						<th class="hits">조회</th>
						<th class="ghits">공감</th>
					</tr>
				</thead>
				<tbody>
			<c:forEach var="bvo" items="${requestScope.lvo.list}">				
			<tr>
			    <td>${bvo.no }</td>
			    <td>${bvo.category }</td>		
				<td>
				<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=showContent&no=${bvo.no }">
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
				<td>${bvo.ghits }</td>
			</tr>	
			</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br></br>	
	<c:if test="${sessionScope.mvo!=null}">
	<a href=""><img src="${pageContext.request.contextPath}/img/write.jsp" border="0"></a>
	</c:if>
	<br><br>	
<p class="paging" style="margin-left: 50%">
	<%-- 코드를 줄이기 위해 pb 변수에 pagingBean을 담는다. --%>
	<c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>
	<!-- 
			step2 1) 이전 페이지 그룹이 있으면 이미지 보여준다. (img/left_arrow_btn.gif)
				   		페이징빈의 previousPageGroup 이용 
				   2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
				   	    hint)   startPageOfPageGroup-1 하면 됨 		 
	 -->      
	<c:if test="${pb.previousPageGroup}">
	<a href="DispatcherServlet?command=list&pageNo=${pb.startPageOfPageGroup-1}">
	<!-- <img src="img/left_arrow_btn.gif"> -->
	◀&nbsp; </a>	
	</c:if>
	<!-- step1. 1)현 페이지 그룹의 startPage부터 endPage까지 forEach 를 이용해 출력한다
				   2) 현 페이지가 아니면 링크를 걸어서 서버에 요청할 수 있도록 한다.
				      현 페이지이면 링크를 처리하지 않는다.  
				      PagingBean의 nowPage
				      jstl choose 를 이용  
				      예) <a href="DispatcherServlet?command=list&pageNo=...">				   
	 -->		
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
	<!-- 
			step3 1) 다음 페이지 그룹이 있으면 이미지(img/right_arrow_btn.gif) 보여준다. 
				   		페이징빈의 nextPageGroup 이용 
				   2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
				   	    hint)   endPageOfPageGroup+1 하면 됨 		 
	 -->   
	<c:if test="${pb.nextPageGroup}">
	<a href="DispatcherServlet?command=list&pageNo=${pb.endPageOfPageGroup+1}">
	▶<!-- <img src="img/right_arrow_btn.gif"> --></a>
	</c:if>
	</p>
</body>
</html>