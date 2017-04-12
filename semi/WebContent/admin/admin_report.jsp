<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 신고</title>
</head>
<body>
<%@ include file="admin_top.jsp" %>
<div class="container">
<table class="table">
	<tr>
		<th>회원아이디</th><th>게시글 번호</th><th>이유</th>
	</tr>
	<c:forEach var="rp" items="${requestScope.report}">
	<tr>
		<td>${rp.memberId }</td><td>${rp.boardNo }</td><td>${rp.content }</td>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>