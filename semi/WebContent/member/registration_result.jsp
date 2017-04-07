<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입결과화면</title>
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
function pageOut(){
	location.href="${pageContext.request.contextPath}/index.jsp";
} 
function keypress(){
	if(event.keyCode==13){
		location.href = "${pageContext.request.contextPath}/index.jsp";
	}
}
</script>
</head>
<body onkeypress="keypress()">
<div style="padding:150px; text-align:center;">
<a href="${pageContext.request.contextPath}/index.jsp">
	<img src="${pageContext.request.contextPath}/img/logo.png">
</a>
<h1>
 ${param.memberId}님<br> 
 회원가입을 축하합니다.
</h1>
<input type="button" value="확인" onclick="pageOut()">
</div>
</body>
</html>











