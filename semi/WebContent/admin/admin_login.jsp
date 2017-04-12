<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/css/style.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>admin-login</title>
<script type="text/javascript">
	function check() {
		if(frm.id.value == "" || frm.pass.value == "") {
			alert("자료를 입력하시오");
			return;
		} else {
			frm.submit();
		}
	}
	function logout() {
    var f = confirm("로그아웃하시겠습니까?");
    if (f)
       location.href = "${pageContext.request.contextPath}/DispatcherServlet?command=logout";
 }
</script>
<style type="text/css">
form,tr,td{
text-align: center;
}
td{
margin:0 auto;
}
input{
width:300px;
}
#box{
text-align: center;
margin-top:200px;
margin-left:600px;
}
</style>
</head>
<body>
<div id="box">
	<form action="admin_login_action.jsp" name="frm" method="post">
		<table>
		<%
			String admin = (String)session.getAttribute("adminOk");
			if(admin !=null){
		%>
			<tr>
				<td>
					<script>
						window.opener.location.href="admin_index.jsp";
					</script>
					 <img src="${pageContext.request.contextPath}/img/logo.png" width="200" align="middle" style="margin-left: 5%"><br>
					관리자 로그인 중입니다<br/>
					[<a href="javascript:logout()">로그아웃</a>]
					[<a href="${pageContext.request.contextPath}/admin/admin_index.jsp">관리자 페이지 이동</a>]
				</td>				
			</tr>
		<%
			} else {
		%>
			<tr><td colspan="2"><h3>관리자 로그인 페이지</h3></td></tr>
			<tr><td><input type="text"  class="form-control" name="id" placeholder="아이디"/></td></tr>
			<tr><td><input type="password"  class="form-control" name="pass" placeholder="비밀번호"/></td></tr>
			<tr>
				<td>
					<a href="#" onclick="check()">로그인</a>
				</td>
			</tr>

		<%
			}
		%>
		</table>
	</form>
	</div>
</body>
</html>