<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/css/style.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
function logout() {
    var f = confirm("로그아웃하시겠습니까?");
    if (f)
       location.href = "${pageContext.request.contextPath}/DispatcherServlet?command=logout";
 }
</script>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/admin_index.jsp">알바썰 관리자</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
      <li><a href="javascript:logout()">로그아웃</a></li>
      <li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=adminMemberList">회원관리</a></li>
      <li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=adminBoardList">게시물관리</a></li>
      <li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=adminReportList">신고 게시글</a></li>
    </ul>
  </div>
</nav>
