<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table id="test" class="table table-hover">
				<thead>
					<tr>
						<th colspan="5">제목</th>
						<th>글번호</th>
					</tr>
					<tr>
						<th colspan="2">아이디</th>
						<th colspan="2">작성일</th>
						<th colspan="2">조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="6">
						<div style="overflow:auto; width:100%; height:300px;">
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						내용<br>
						</div>
						</td>
					</tr>
					<tr>
						<td><img src="${pageContext.request.contextPath }/img/heart.jpg" style="width:20px; height:20px;">+숫자</td>
						<td colspan="4"></td>
						<td align="right"><img src="${pageContext.request.contextPath}/img/listbtn.jpg" style="width:54px; height:30px;">&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/updatebtn.jpg" style="width:42px; height:30px;">&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/deletebtn.jpg" style="width:42px; height:30px;"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>