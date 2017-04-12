<%@page import="model.MemberVO"%>
<%@page import="model.MemberDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 회원관리</title>
<script type="text/javascript">
function fn_deleteMember(id) {
	if(confirm(id+ " 회원을 삭제하시겠습니까?")){
		location.href="${pageContext.request.contextPath}/DispatcherServlet?command=adminDeleteMember&memId="+id;
	}else{
		return;
	}
}
</script>
</head>
<body>
	<%@ include file="admin_top.jsp" %>
	<div class="container">
	<table class="table">
		<tr>
			<th>NO</th><th>아이디</th><th>회원명</th><th>닉네임</th><th>삭제 여부</th><th>삭제</th>
		</tr>
 		<c:forEach var="mem" items="${requestScope.memlist}" varStatus="order">
			<tr>
				<td>${order.count}</td>
				<td>${mem.member_Id}</td>
				<td>${mem.name}</td>
				<td>${mem.nickName }</td>
				<td>${mem.deleteMember }</td>
				<td><input type="button" class="btn btn-warning" value="회원삭제" onclick="fn_deleteMember('${mem.member_Id}')"></td>
			</tr>
		</c:forEach>
	</table>	
	</div>
	<%@ include file="admin_bottom.jsp" %>
	
</body>
</html>