<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>Insert title here</title>
<script type="text/javascript">
	function form_submit() {
		var f = document.reportForm;
		var radio = document.reportForm.reason;
		if(radio.value==""){
			alert("신고 사유를 선택해주세요!");
			return;
		}
		f.submit();
	}

</script>
</head>
<body>
<h3>신고하기</h3>
<div>
<h5>사유 선택</h5>
<span>여러 사유에 해당되는 경우, 대표적인 사유 1개를 선택해 주세요.</span><br><br>
<form name="reportForm" action="${pageContext.request.contextPath}/DispatcherServlet">
	<input type="hidden" name="command" value="boardReport">
	<input type="hidden" name="boardNo" value="<%= request.getParameter("brdno")%>">
	<input type="hidden" name="memId" value="<%= request.getParameter("memid")%>">
	<input type="radio" name="reason" value="부적절한홍보게시물">부적절한 홍보 게시물<br>
	<input type="radio" name="reason" value="음란성또는청소년에게부적합한내용">음란성 또는 청소년에게 부적합한 내용<br>
	<input type="radio" name="reason" value="기타">기타<br>
	<input type="button" class="popup_btn" value="신고하기" onclick="form_submit()"> 
	<input type="button" class="popup_btn" value="취소" onclick='javascript:self.close()'>
</form>
</div>
</body>
</html>