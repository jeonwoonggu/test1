<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
						<th colspan="5">제목 : ${requestScope.bvo.title} </th>
						<th>글번호 : ${requestScope.bvo.board_no } </th>
					</tr>
					<tr>
						<th colspan="2">작성자 : ${requestScope.bvo.memberVO.nickName}</th>
						<th colspan="2">작성일 :  ${requestScope.bvo.timePosted }</th>
						<th colspan="2">조회수 :  ${requestScope.bvo.hits }</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="6">
						<div style="overflow:auto; width:100%; height:300px;">
							<pre>${requestScope.bvo.content}</pre>
						</div>
						</td>
					</tr>
					<tr>
						<td><img src="${pageContext.request.contextPath }/img/heart.jpg" style="width:20px; height:20px;">&nbsp;${requestScope.bvo.likes}</td>
						<td colspan="4"></td>
						<td align="right"><img src="${pageContext.request.contextPath}/img/listbtn.jpg" style="width:54px; height:30px;">&nbsp;&nbsp;
							<img src="${pageContext.request.contextPath}/img/updatebtn.jpg" style="width:42px; height:30px;">&nbsp;&nbsp;
							<img src="${pageContext.request.contextPath}/img/deletebtn.jpg" style="width:42px; height:30px;">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>