<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function searchForm() {

	}
	function logout() {
		var f = confirm("로그아웃하시겠습니까?");
		if (f)
			location.href = "${pageContext.request.contextPath}/DispatcherServlet?command=logout";
	}
</script>
<style type="text/css">
.searchForm {
	height: 40px;
	width: 300px;
	border-color: #FFDB45;
	border-width: 3px;
	border-style: solid;
	text-align: center;
	margin-left: 20%;
	font-size: 20px;
	vertical-align: middle;
}

.navbar-nav  li a:hover {
      background-color: #ffd100 !important;
  }
#myNavbar1,#myNavbar2{
	
}
</style>
</head>
<body>
	<div class="page-header">
		<a href="${pageContext.request.contextPath}/index.jsp"><img
			src="${pageContext.request.contextPath}/img/logo.png" width="200"
			align="middle"></a> <input type="text" class="searchForm">
		<img src="${pageContext.request.contextPath}/img/searching.jpg"
			class="searchbtn" width="50px" align="middle"
			style="margin-bottom: 2px;" onclick="searchForm">
		<c:choose>
			<c:when test="${sessionScope.mvo==null}">
				<div class="collapse navbar-collapse" id="myNavbar1">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${pageContext.request.contextPath}/member/login.jsp">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/member/registration.jsp">회원가입</a></li>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<div class="collapse navbar-collapse" id="myNavbar2">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=myInfo">${sessionScope.mvo.name}님</a></li>
						<li><a href="javascript:logout()">로그아웃</a></li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>