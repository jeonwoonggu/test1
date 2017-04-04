<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
img{
	align: center;
}
input[type=text],input[type=password], select {
    width: 400px;
    padding: 14px 0;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    text-align: center;
}

input[type=submit] {
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div style="padding:150px; text-align:center;">
<a href="${pageContext.request.contextPath}/index.jsp">
	<img src="${pageContext.request.contextPath}/img/logo.png">
</a>
<form action="${pageContext.request.contextPath}/DispatcherServlet">
	<input type="text" name="memberId" placeholder="아이디" required="required"><br>
	<input type="password" name="password" placeholder="비밀번호" required="required"><br>
	<input type="hidden" name="command" value="login">
	<input type="submit" value="로그인" >
</form>
</div>

</body>
</html>