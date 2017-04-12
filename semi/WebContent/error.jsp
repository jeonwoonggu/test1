<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#home").click(function(){
		location.href = "${pageContext.request.contextPath}/index.jsp";
	});
});
</script>
<style type="text/css">
img{
	width:250px; .
	height:100px;
   
}

input[type=button] {
    width: 300px;
    background-color: #ffd100;
    color: black;
    padding: 14px 0;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    align: center;
   
}

input[type=button]:hover {
    background-color: #FFE87E;
}
div {
	
	background-color: #FFEB46;
	border: 2px solid;
	border-radius: 40px;
	color:black;
	margin:90px;
	margin-left: 27%;
	padding:14px;
	width:600px;
	height:250px:
}
</style>
</head>
<body>
	<div>
	<center>
<a href="${pageContext.request.contextPath}/index.jsp">
	<img src="${pageContext.request.contextPath}/img/logo1.png" >
</a>
</center>
<h2>죄송합니다.</h2><h3>요청하신 페이지를 찾을 수 없습니다.</h3>
요청하신 페이지를 찾을 수가 업습니다.
<br>찾으시려는 페이지는 주소를 잘못 입력하였거나
<br>페이지 주소의 변경 또는 삭제 등의 이유로 페이지를 찾을 수 없습니다.
<br>입력하신 페이지의 주소와 경로가 정확한지 한번더 확인 후 이용하시기 바랍니다.
<br><center><input id="home" type="button" value="홈으로 이동"></center>
</div>
</body>
</html>