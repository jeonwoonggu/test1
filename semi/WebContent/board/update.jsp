<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function content_update() {
		if (confirm("글을 수정하시겠습니까?")) {
			if($("#category").val()==""){
				alert("카테고리를 선택하세요");
				return false;
			}else{
				document.update_form.submit();
			}
		} else {
			return false;
		}
	}
	function cancel() {
		document.update_form.reset();
	}
	$(document).ready(function(){
		$("#selcate").change(function(){
			$("#category").val($(this).val());
		});
	});
</script>
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<br>
	<form method="post" action="${pageContext.request.contextPath}/DispatcherServlet" name="update_form">
	<input type="hidden"  name="command"  value="updatePosting"></input>
	<input type="hidden"  name="no"  value=${bvo.board_no }>
	<input type="hidden" id="category" name ="category" value=>
		<div class="container">
			<div class="table-responsive">
				<table class="inputForm" style="margin-left: 15%">
					<tbody>
						<tr>
							<td><select id="selcate"
								style="width: 50px; height: 25px; margin-left: 10px;">
									<option value="">------</option>
									<option value="추천">추천</option>
									<option value="비추천">비추천</option>
							</select></td>
							<td>제목</td>
							<td colspan="3"><input type="text" name="title" size="86"
								required="required" style="margin-left: 1%" value="${bvo.title }"></td>
						</tr>
						<tr>
							<td style="height: 35px;">닉네임</td>
								<td>${sessionScope.mvo.nickName }</td>
						</tr>
						<tr>
							<td colspan="4" align="left">&nbsp;&nbsp; <textarea
									cols="100" rows="15" name="content" required="required">${bvo.content }</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center"><img class="action"
								src="${pageContext.request.contextPath }/img/updatebtn.jpg"
								alt="글입력" onclick="content_update()" width="50"> <img
								class="action"
								src="${pageContext.request.contextPath }/img/cancelbtn.jpg"
								onclick="cancel()" width="50"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</html>
