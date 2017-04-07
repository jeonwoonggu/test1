<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

td {
	width: 90px;
	align: center;
	height: 50px;
	
}

#year,#month,#day {
	width: 90px;
}

input[type=text], select {
	width: 290px;
	padding: 8px 0;
	margin: 0px 0;
	display: inline-block;
	align: center;
	  border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    
    
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#year").change(function(){
		if($("#year").val() < 1920 || $("#year").val() > 2017){
			alert("1920~2017년까지 입력가능합니다.");
			$("#year").val("");
			$("#year").focus();
		}//if
	});//change
	$("#month").change(function(){
		if($("#year").val() < 1 || $("#month").val() > 12){
			alert("1~12월까지 입력가능합니다.");
			$("#month").val("");
			$("#month").focus();
		}//if
	});//change
	$("#day").change(function(){
		if($("#day").val() < 1 || $("#day").val() > 31){
			alert("1~31일까지 입력가능합니다.");
			$("#day").val("");
			$("#day").focus();
		}//if
	});//change
});//ready
</script>

</head>
<body style="padding:100px; text-align:center;">
	<center>
		<br>
		<br> <a href="${ pageContext.request.contextPath }/index.jsp"><img
			src="${ pageContext.request.contextPath }/img/logo.png"></a>
		
		<form name="forgetIdForm" action="${ pageContext.request.contextPath }/DispatcherServlet" method="post" onsubmit="return startReg()">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" placeholder="이름" required="required"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>
				<input id="year" type="text" name="residentYear" placeholder="년도" required="required">
				<input id="month" type="text" name="residentMonth" placeholder="월" required="required">
				<input id="day" type="text" name="residentDay" placeholder="일" required="required">
				
				</td>
			</tr>
		</table>
		<br>
		<p>
			<input type="hidden" name="command" value="forgetId">
			<input type="submit" value="아이디찾기">
		</p>
		</form>
	</center>
</body>
</html>