<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴화면</title>
<style type="text/css">
img{
	align: center;
}
input[type=password], select {
    width: 300px;
    padding: 14px 0;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    text-align: center;
}
input[type=submit] {
	width: 300px;
	background-color: #ffd100;
	color: black;
	padding: 14px 0;
	margin: 0px 0;
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
<script type="text/javascript">
function passwordChecking(){
	if("${sessionScope.mvo.password}"==$("#pass1").val()){
		return true;
	}else{
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
}

	$(document).ready(function(){
		$("#DeleteMember").click(function(){
			var result=confirm("탈퇴하시겠습니까?");
			if(result){
				return true;
			}else{
				return false;
			}
		});//click
	});//ready
</script>
</head>
<body>

<div style="padding:150px; text-align:center;">
<a href="${pageContext.request.contextPath}/index.jsp">
	<img src="${pageContext.request.contextPath}/img/logo.png">
</a>

<form action="${pageContext.request.contextPath}/DispatcherServlet" onsubmit="return passwordChecking()">
<input id="pass1" type="password" name="password" placeholder="비밀번호" required="required" ><br>
<input type="hidden" name="command" value="deleteMember">
<input id="DeleteMember" type="submit" value="회원탈퇴">
</form>
</div>

</body>
</html>











