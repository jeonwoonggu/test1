<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
td {
	width: 100px;
}

#year,#month,#day {
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
<script type="text/javascript">
	function passwordChecking(){
		if(document.getElementById("pass1").value==document.getElementById("pass2").value){
			document.getElementById("passwordDuplication").innerHTML = "비밀번호확인 ok";
		} else {
			document.getElementById("passwordDuplication").innerHTML = "비밀번호가 맞지않습니다.";
		}
	}
	
	function startReg(){
		var findPass = document.getElementById("passwordDuplication");
		var gd = document.regForm.gender;
		var findNick = document.getElementById("nickDuplication");
		// alert(findId.innerText);
		// alert(findPass.innerText);
		if(gd.value==""){
			alert("성별을 선택하세요.");
			return false;
		} else if(findPass.innerText=="비밀번호가 맞지않습니다."){
			alert("비밀번호 확인해주세요");
			return false;
		} else if(findNick.innerText=="사용불가합니다."){
			alert("닉네임 중복확인해주세요");
			return false;
		}
		
	}
	function yearChecking(){
		var yr=document.getElementById("year");
		if(yr.value>=1920&&yr.value<=2017){
			
		}else{
			alert("1920~2017년까지 입력가능합니다.");
			yr.value="";
			yr.focus();
		}
	}
	function monthChecking(){
		var mc=document.getElementById("month");
		if(mc.value<1||mc.value>12){
			alert("1~12월까지 입력가능합니다.");
			mc.value="";
			mc.focus();
		}
	}
	function dayChecking(){
		var da=document.getElementById("day");
		if(da.value<1||da.value>31){
			alert("1~31일까지 입력가능합니다.");
			da.value="";
			da.focus();
		}
	}
	function nickNameChecking(){
		var nn = document.getElementById("nickChecking");
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4&&xhr.status==200){
				var nickChecking = JSON.parse(xhr.responseText);
				if(nickChecking==false){
					document.getElementById("nickDuplication").innerHTML = 
						"사용불가합니다.";
				} else {
					document.getElementById("nickDuplication").innerHTML = 
						"사용가능한 닉네임입니다.";
				}
			}
		}
		xhr.open("post","${ pageContext.request.contextPath }/DispatcherServlet");
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xhr.send("command=findNickName&nickName="+nn.value);
	}
</script>
</head>
<body>
	<center>
		<br>
		<br> <a href="${ pageContext.request.contextPath }/index.jsp"><img
			src="${ pageContext.request.contextPath }/img/logo.png"></a>
		<!-- <h1>회원가입</h1> -->
		<!-- align="center"   -->
		<form name="regForm" action="${ pageContext.request.contextPath }/DispatcherServlet" method="post" onsubmit="return startReg()">
		<table>
			<tr>
				<td>아이디</td>
				<td><input id="memberIdCheking" type="text" class="form-control" name="memberId"
					placeholder="아이디" onchange="startAjax()"  readonly="readonly" value="${sessionScope.infoVO.member_Id }"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input id="pass1" type="password" name="password" placeholder="비밀번호" required="required" onchange="passwordChecking()">
				</td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input id="pass2" type="password" name="passwordCheck"
					placeholder="비밀번호 확인" required="required" onchange="passwordChecking()"></td>
			</tr>
			<tr>
			<td id="passwordDuplication" colspan="2" align="right"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" placeholder="이름" readonly="readonly" value="${sessionScope.infoVO.name }"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>
				<input onchange="yearChecking()" id="year" type="text" name="residentYear" placeholder="년도" required="required" value="${sessionScope.infoVO.residentNumber.substring(0,4) }">
				<input onchange="monthChecking()" id="month" type="text" name="residentMonth" placeholder="월" required="required" value="${sessionScope.infoVO.residentNumber.substring(4,6) }">
				<input onchange="dayChecking()" id="day" type="text" name="residentDay" placeholder="일" required="required" value="${sessionScope.infoVO.residentNumber.substring(6,8) }">
				
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
				
				<c:choose>
					<c:when test="${ sessionScope.infoVO.gender=='man'}">
						<input type="radio" name="gender" value="man" onclick="return false;" checked="checked">남 
						<input type="radio" name="gender" value="woman" onclick="return false;" >여
					</c:when>
					<c:otherwise>
						<input type="radio" name="gender" value="man" onclick="return false;" >남 
						<input type="radio" name="gender" value="woman" onclick="return false;" checked="checked">여
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input onchange="nickNameChecking()" id="nickChecking" type="text" name="nickName" placeholder="닉네임" required="required" value="${sessionScope.infoVO.nickName }">
				</td>
			</tr>
			<tr>
			<td id="nickDuplication" colspan="2" align="right"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel" placeholder="전화번호" required="required" value="${sessionScope.infoVO.tel }"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" placeholder="주소" required="required" value="${sessionScope.infoVO.address }">
				</td>
			</tr>

		</table>
		<p>
			<input type="hidden" name="command" value="updateMyInfo">
			<input type="submit" value="수정하기">
		</p>
		</form>
	</center>
</body>
</html>