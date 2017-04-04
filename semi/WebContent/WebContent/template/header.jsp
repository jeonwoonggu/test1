<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<title>Insert title here</title>
<script type="text/javascript">
function searchForm() {
	
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
   	margin-left:20%;
   	font-size: 20px;
   	vertical-align: middle;
	}
</style>
</head>
<body>
  <div class="page-header">
    <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/img/logo.png" width="200" align="middle"></a>
 	<input type="text" class="searchForm">
	<img src="${pageContext.request.contextPath}/img/searching.jpg" class="searchbtn" width="50px" align="middle" style="margin-bottom: 2px;" onclick="searchForm">
	<a href="${pageContext.request.contextPath}/member/login.jsp" style="vertical-align: bottom; margin-left: 20%"> 로그인 </a>
	<a href="${pageContext.request.contextPath}/member/registration.jsp" style="vertical-align: bottom; margin-left: 10px"> 회원가입 </a>
 </div> 
</body>
</html>