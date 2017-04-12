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
		var startDate = $("#startdate").val();
        var startDateArr = startDate.split('-');
        var endDate = $("#enddate").val();
        var endDateArr = endDate.split('-');
        var startDateComp = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
        var endDateComp = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
        
		if (confirm("글을 등록하시겠습니까?")) {
			if($("#category").val()==""){
				alert("카테고리를 선택하세요");
				return false;
			}else if($("#startdate").val()==""){
				alert("근무 시작일을 선택하세요");
				return false;
			}else if($("#job").val()==""){
				alert("직종을 선택하세요");
				return false;
			}else if($("#enddate").val()==""){
				alert("근무 종료일을 선택하세요");
				return false;
			}else if($("#content").val()==""){
				alert("내용을 입력하세요");
				return false;
			}else if(startDateComp.getTime() > endDateComp.getTime()){
				alert("근무시작일과 근무종료일을 확인해주세요");
				return false;
			}else{
				document.write_form.submit();
			}			
		} else {
			return false;
		}
	}
	function cancel() {
		document.write_form.reset();
		//history.back();  -- 취소하면 이전 페이지로???
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
	<form action="${pageContext.request.contextPath }/DispatcherServlet?command=write"
		method="post" name="write_form" enctype="multipart/form-data">
		<input type="hidden" name="command" value="write">
		<input type="hidden" name="id" value="${sessionScope.mvo.member_Id }">
		<input type="hidden" id="category" name ="category" value=>
		<div class="container">
			<div class="table-responsive">
				<table class="inputForm" style="margin-left: 15%">
					<tbody>
						<tr>
							<td>
								<select id="selcate" style="width: 50px; height: 25px; margin-left: 10px;">
										<option value="">------</option>
										<option value="추천">추천</option>
										<option value="비추천">비추천</option>
								</select>
							</td>
							<td>&nbsp;&nbsp;제목</td>
							<td colspan="2">
								&nbsp;&nbsp;<input type="text" name="title" size="84" required="required" style="margin-left: 1%">
							</td>
						</tr>
						<tr>
							<td style="height: 35px;" colspan="5">&nbsp;&nbsp;&nbsp;닉네임: ${sessionScope.mvo.nickName }</td>
						</tr>
						<tr>
							<td colspan="5">&nbsp;&nbsp;&nbsp;업종&nbsp;&nbsp;&nbsp;&nbsp;<select name="job" id="job">
										<option value="">------</option>
										<option value="매장관리">매장관리</option>
										<option value="서빙주방">서빙주방</option>
										<option value="서비스">서비스</option>
										<option value="생산,기능,운전,배달">생산,기능,운전,배달</option>
										<option value="사무회계">사무회계</option>
										<option value="IT, 디자인">IT, 디자인</option>
										<option value="상담영업">상담영업</option>
										<option value="강사교육">강사교육</option>
								</select>
							&nbsp;&nbsp;&nbsp;근무 시작일&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" name="startdate" id="startdate">
							&nbsp;&nbsp;&nbsp;근무 종료일&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" name="enddate" id="enddate"></td>
						</tr>
						<tr>
							<td colspan="4" align="left">&nbsp;&nbsp; 
 									<input type="file" name="m_file">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="left">&nbsp;&nbsp; 
								<textarea cols="100" rows="15" name="content" required="required" id="content"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center"><img class="action"
								src="${pageContext.request.contextPath }/img/writebtn.jpg"
								alt="글입력" onclick="content_submit()" width="50"> <img
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
