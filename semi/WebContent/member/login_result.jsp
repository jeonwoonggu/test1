<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
input[type=button] {
	width: 400px;
	background-color: #ffd100;
	color: black;
	padding: 14px 0;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #FFE87E;
}
</style>
<script type="text/javascript">
	function pageOut() {
		location.href = "${pageContext.request.contextPath}/index.jsp";
	}
	function keypress(){
		if(event.keyCode==13){
			location.href = "${pageContext.request.contextPath}/index.jsp";
		}
	}
</script>
</head >
<body onkeypress="keypress()">
	<c:choose>
		<c:when test="${sessionScope.mvo==null}">
			<div style="padding: 150px; text-align: center;">
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					src="${pageContext.request.contextPath}/img/logo.png">
				</a>
				<h1>등록된 회원이 없습니다.</h1>
				<input type="button" value="확인" onclick="pageOut()">
			</div>
			<!-- <script type="text/javascript">
				alert("맞는 아이디 혹은 비밀번호가 없습니다.");
				location.href = "${pageContext.request.contextPath}/index.jsp";
			</script> -->
		</c:when>
		<c:otherwise>
			<div style="padding: 150px; text-align: center;">
				<a href="${pageContext.request.contextPath}/index.jsp"> <img
					src="${pageContext.request.contextPath}/img/logo.png">
				</a>
				<h1>${sessionScope.mvo.name}님 로그인 ok!</h1>
				<input type="button" value="확인" onclick="pageOut()">
			</div>
			<!-- <script type="text/javascript">
				alert("${sessionScope.mvo.name}님 로그인 ok!");
				location.href = "${pageContext.request.contextPath}/index.jsp";
			</script> -->
		</c:otherwise>
	</c:choose>
</body>
</html>