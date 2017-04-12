<%@page import="model.ReportDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script type="text/javascript">
function checkAll(){
    if( $("#th_checkAll").is(':checked') ){
      $("input[name=checkRow]").prop("checked", true);
    }else{
      $("input[name=checkRow]").prop("checked", false);
    }
}
function deleteAction(){
	  var checkRow = "";
	  $( "input[name='checkRow']:checked" ).each (function (){
	    checkRow = checkRow + $(this).val()+"," ;
	  });
	  checkRow = checkRow.substring(0,checkRow.lastIndexOf( ",")); //맨끝 콤마 지우기
	 
	  if(checkRow == ''){
	    alert("삭제할 대상을 선택하세요.");
	    return false;
	  }
	  console.log("### checkRow => {}"+checkRow);
	 
	  if(confirm("게시글을 삭제 하시겠습니까?")){
	    location.href="${pageContext.request.contextPath}/DispatcherServlet?command=adminBoardDelete&idx="+checkRow;
	  }
	}
</script>
</head>
<body>
	<%@ include file="admin_top.jsp" %>
	<div class="container">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th> 번호 </th>
						<th> <input type="checkbox" name="checkAll" id="th_checkAll" onclick="checkAll()"/></th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=categoryList&cate=추천">추천</a>/<a href="${pageContext.request.contextPath}/DispatcherServlet?command=categoryList&cate=비추천">비추천</a>/<a href="${pageContext.request.contextPath}/DispatcherServlet?command=list">전체보기</a></th>
						<th> 업종 </th>
						<th> 제목 </th>
						<th> 작성자 </th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=list">작성일▼</a></th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=hitsList">조회▼</a></th>
						<th><a href="${pageContext.request.contextPath}/DispatcherServlet?command=sympathyList">공감▼</a></th>
					</tr>
				</thead>
				<tbody>
			<c:forEach var="bvo" items="${requestScope.lvo.list}">			
			<tr>
			    <td>${bvo.board_no }</td>
			    <td><input type="checkbox" name="checkRow" value="${bvo.board_no }"></td>
			    <td>${bvo.category }</td>
			    <td>${bvo.job }</td>		
				<td>
				<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
				<a href="${pageContext.request.contextPath}/DispatcherServlet?command=showContent&no=${bvo.board_no }&memberId=${sessionScope.mvo.member_Id}">
				${bvo.title } (${bvo.replycount })</a>
				</c:when>
				<c:otherwise>
				${bvo.title } (${bvo.replycount })
				</c:otherwise>
				</c:choose>
				</td>
				<td>${bvo.memberVO.nickName }</td>
				<td>${bvo.timePosted }</td>
				<td>${bvo.hits }</td>
				<td>${bvo.likes }</td>
			</tr>	
			</c:forEach>
				</tbody>
			</table>
			<input type="button" class="btn btn-warning" value="게시글 삭제" onclick="deleteAction()">
		</div>
	</div>
	<br></br>	
	<c:if test="${sessionScope.mvo!=null}">
	<a href="${pageContext.request.contextPath}/board/write.jsp" style="margin-left: 85%">
	<img src="${pageContext.request.contextPath}/img/write.jpg" border="0" width="60px" ></a>
	</c:if>
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