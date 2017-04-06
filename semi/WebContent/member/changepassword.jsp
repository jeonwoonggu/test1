<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>
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
	$("#pass1").change(function(){
		if($("#pass1").val()==$("#pass2").val()){
			$("#passwordDuplication").innerHTML = "비밀번호확인 ok";
		} else {
			$("#passwordDuplication").innerHTML = "비밀번호가 맞지않습니다.";
		}
	});
	
	
});
function startChangepass(){
	if($("#passwordDuplication").innerText=="비밀번호가 맞지않습니다."){
		alert("비밀번호 확인해주세요");
		return false;
	}
}

</script>
</head>
<body>
<center>
		<br>
		<br> 
		<a href="${ pageContext.request.contextPath }/index.jsp">
			<img src="${ pageContext.request.contextPath }/img/logo.png">
		</a>
		<form name="changePassForm" action="${ pageContext.request.contextPath }/DispatcherServlet" method="post" onsubmit="return startChangepass()">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="memberId" placeholder="아이디" readonly="readonly" value="${ sessionScope.passVO.member_Id }">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input id="pass1" type="password" name="password" placeholder="비밀번호" required="required">
				</td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td>
					<input id="pass2" type="password" name="passwordCheck" placeholder="비밀번호 확인" required="required">
				</td>
			</tr>
			<tr>
				<td id="passwordDuplication" colspan="2" align="right"></td>
			</tr>
		</table>
		<p>
			<input type="hidden" name="command" value="changePassword">
			<input type="submit" value="비밀번호 변경">
		</p>
		</form>
	</center>
</body>
</html>