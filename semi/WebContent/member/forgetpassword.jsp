<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
<style type="text/css">
td {
	width: 100px;
}

#year, #month, #day {
	width: 90px;
}

input[type=text], input[type=password], select {
	width: 290px;
	padding: 8px 0;
	margin: 0px 0;
	display: inline-block;
	align: center;
}

input[type=submit] {
	width: 400px;
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
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#year").change(function(){
		if($("#year").val() < 1920 || $("#year").val() > 2017){
			alert("1920~2017년까지 입력가능합니다.");
			$("#year").val("");
			$("#year").focus();
		}
	});
	$("#month").change(function(){
		if($("#year").val() < 1 || $("#month").val() > 12){
			alert("1~12월까지 입력가능합니다.");
			$("#month").val("");
			$("#month").focus();
		}
	});
	$("#day").change(function(){
		if($("#day").val() < 1 || $("#day").val() > 31){
			alert("1~31일까지 입력가능합니다.");
			$("#day").val("");
			$("#day").focus();
		}
	});
});
</script>
</head>
<body>
	<center>
		<br> <br> 
		<a href="${ pageContext.request.contextPath }/index.jsp">
			<img src="${ pageContext.request.contextPath }/img/logo.png">
		</a>
		<form name="forgetpasswordForm" action="${ pageContext.request.contextPath }/DispatcherServlet"
			method="post" onsubmit="return startPass()">
			<table>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="memberId" placeholder="아이디" required="required">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name" placeholder="이름" required="required">
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<input id="year" type="text" name="residentYear" placeholder="년도" required="required">
						<input id="month" type="text" name="residentMonth" placeholder="월" required="required">
						<input id="day" type="text" name="residentDay" placeholder="일" required="required">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input type="text" name="tel" placeholder="전화번호" required="required">
					</td>
				</tr>
			</table>
			<p>
				<input type="hidden" name="command" value="forgetpassword">
				<input type="submit" value="비밀번호찾기">
			</p>
		</form>
	</center>
</body>
</html>