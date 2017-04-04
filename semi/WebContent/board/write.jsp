<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function content_submit() {
		if (confirm("글을 등록하시겠습니까?")) {
			document.write_form.submit(); // 자바스크립트를 이용하여 form의 submit 실행
		} else {
			return false;
		}
	}
	function cancel() {
		document.write_form.reset();
	}
	function categorycheck() {
		 i=document.write_form.selcate.selectedIndex // 선택항목의 인덱스 번호
		 var category=document.write_form.selcate.options[i].value // 선택항목 value
		 document.write_form.category.value=category
	}
</script>
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<br>
	<form action="${pageContext.request.contextPath }/DispatcherServlet"
		method="post" name="write_form">
		<input type="hidden" name="command" value="write"> 
		<input type="hidden" name="id" value="${sessionScope.mvo.id }">
		<input type="hidden" name="category" value="category">
		<div class="container">
			<div class="table-responsive">
				<table class="inputForm" style="margin-left:15%">
					<tbody>
						<tr>
							<td><select name="selcate" style="width:50px; height:25px; margin-left:10px;" onchange="categorycheck" >
									<option value="">------</option>
									<option value="추천">추천</option>
									<option value="비추천">비추천</option>
							</select></td>
							<td>제목</td>
							<td colspan="3"><input type="text" name="title" size="86"
								required="required" style="margin-left: 1%"></td>
						</tr>
						<tr>
							<td style="height: 35px;">이름</td>
							<td>${sessionScope.mvo.name }</td>
						</tr>
						<tr>
							<td colspan="4" align="left">&nbsp;&nbsp; <textarea
									cols="100" rows="15" name="content" required="required"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
							<img class="action" src="${pageContext.request.contextPath }/img/writebtn.jpg" alt="글입력" onclick="content_submit()" width="50">
							<img	class="action" src="${pageContext.request.contextPath }/img/cancelbtn.jpg" 	onclick="cancel()" width="50">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</html>
