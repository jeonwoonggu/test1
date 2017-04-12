<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
function reportWindow(){
	window.open("${pageContext.request.contextPath}/board/report_popup.jsp?brdno=${requestScope.bvo.board_no }&memid=${sessionScope.mvo.member_Id}","popup",
	"resizable=true,toolbar=no,width=500,height=300,left=300,top=150");
}
// 후기 상세 목록,수정,삭제
function sendList(){
	location.href="${pageContext.request.contextPath}/index.jsp";
}
function deleteBoard(){
	if(confirm("게시글을 삭제하시겠습니까?")){
		location.href="${pageContext.request.contextPath}/DispatcherServlet?command=deletePosting&no=${requestScope.bvo.board_no }";
	}
}
function updateBoard(){
	if(confirm("게시글을 수정하시겠습니까?")){
		location.href="${pageContext.request.contextPath}/DispatcherServlet?command=updateView&no=${requestScope.bvo.board_no }";
	}
}
// 댓글 작성버튼 클릭시 submit
function fn_formSubmit() {
	var f = document.replyWriteForm;
	if($("#rememo").val()==""){
		alert("댓글을 입력해주세요!");
		return;
	}else{
		f.submit();
	}
}

//댓글 수정버튼 클릭시 수정창 나타나게 하기
var updateReno = updateRememo = null;
function fn_replyUpdate(reno){
    var form = document.replyUpdateForm;
    var reply = document.getElementById("reply"+reno);
    var replyDiv = document.getElementById("replyDiv");
    replyDiv.style.display = "";
	    
    if (updateReno) {
        document.body.appendChild(replyDiv);
        var oldReno = document.getElementById("reply"+updateReno);
        oldReno.innerText = updateRememo;
    } 
    
    form.reno.value=reno;
    form.rememo.value = reply.innerText;
    reply.innerText ="";
    reply.appendChild(replyDiv);
    updateReno   = reno;
    updateRememo = form.rememo.value;
    form.rememo.focus(); 
} 

// 댓글 수정버튼->저장 누를때 
function fn_replyUpdateSave(){
    var form = document.replyUpdateForm;
    if (form.rememo.value=="") {
        alert("글 내용을 입력해주세요.");
        form.rememo.focus();
        return;
    }
    form.action="DispatcherServlet";
    form.submit();     
} 
// 댓글 수정하고 취소 누르기
function fn_replyUpdateCancel(){
    var form = document.replyUpdateForm;
    var replyDiv = document.getElementById("replyDiv");
    document.body.appendChild(replyDiv);
    replyDiv.style.display = "none";
    
    var oldReno = document.getElementById("reply"+updateReno);
    oldReno.innerText = updateRememo;
    updateReno = updateRememo = null; 
} 

function fn_replyDelete(reno,brdno){
	if(confirm("삭제하시겠습니까?")){
		location.href ="DispatcherServlet?command=deleteReply&rno="+reno+"&bno="+brdno;
	}else{
		return;
	}
}

//hide #back-top first
$("#back-top").hide();
 
// fade in #back-top
$(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('#back-top').fadeIn();
        } else {
            $('#back-top').fadeOut();
        }
    });
 
    // scroll body to 0px on click
    $('#back-top a').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });
});
//좋아요 눌렀을 때 이벤트
$(document).ready(function(){
	if(${requestScope.likeCheck==true}){
		$( "#like" ).attr("src","${pageContext.request.contextPath }/img/fullheart.jpg");
	}else{
		$( "#like" ).attr("src","${pageContext.request.contextPath }/img/heart.jpg");
	}
	
	$( "#like" ).bind("click",function(){
		var src=($(this).attr("src")==="${pageContext.request.contextPath }/img/heart.jpg")
					? "${pageContext.request.contextPath }/img/fullheart.jpg"
					: "${pageContext.request.contextPath }/img/heart.jpg";
		$(this).attr("src",src);
		if(src=="${pageContext.request.contextPath }/img/fullheart.jpg"){
			$.ajax({
				type:"get",
				url:"DispatcherServlet",
				dataType:"json",
				data:"command=like&no=${bvo.board_no }&memberId=${sessionScope.mvo.member_Id}",
				success:function(data){
					$( "#likeNum" ).text(data);
				}
			});
		}else{
			$.ajax({
			type:"get",
			url:"DispatcherServlet",
			dataType:"json",
			data:"command=likeCancel&no=${bvo.board_no }&memberId=${sessionScope.mvo.member_Id}",
			success:function(data){
				$( "#likeNum" ).text(data);
			}
			});	// ajax
		}// else
	});	// click
});	// ready



// 대댓글
function hideDiv(id){
    var div = document.getElementById(id);
    div.style.display = "none";
    document.body.appendChild(div);
}

function fn_replyReply(reno){
    var form = document.form3;
    var reply = document.getElementById("reply"+reno);
    var replyDia = document.getElementById("replyDialog");
    replyDia.style.display = "";
    
    if (updateReno) {
        fn_replyUpdateCancel();
    } 
    
    form.rememo.value = "";
    form.reparent.value=reno;
    reply.appendChild(replyDia);

} 
function fn_replyReplyCancel(){
    hideDiv("replyDialog");
} 

function fn_replyReplySave(){
    var form = document.form3;
    var param = form.reparent.value
    if (form.rememo.value=="") {
        alert("글 내용을 입력해주세요.");
        form.rememo.focus();
        return;
    }

    form.action="DispatcherServlet";
    form.submit();    
}

</script>
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<div class="container">
		<div class="row">
			<table id="test" class="table">
				<thead>
					<tr>
						<th colspan="5">제목 : ${requestScope.bvo.title}</th>
						<th>글번호 : ${requestScope.bvo.board_no }</th>
					</tr>
					<tr>
						<th colspan="2">작성자 : ${requestScope.bvo.memberVO.nickName}</th>
						<th colspan="2">작성일 : ${requestScope.bvo.timePosted }</th>
						<th colspan="2">조회수 : ${requestScope.bvo.hits }</th>
					</tr>
					<tr>
					<th colspan="2">직종 : ${requestScope.bvo.job}</th>
						<th colspan="2">근무 시작일 : ${requestScope.bvo.startDate }</th>
						<th colspan="2">근무 종료일 : ${requestScope.bvo.endDate }</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="6">
							<c:set var="imgsrc" value="${ requestScope.imgsrc }" />
							<c:if test="${fn:contains(imgsrc,'.')}">
								<img src="${pageContext.request.contextPath}/${ requestScope.imgsrc }"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<div style="overflow-y: auto; width: 100%; min-height:100px">
								<pre>${requestScope.bvo.content}</pre>
							</div>
							<c:if test="${sessionScope.mvo.member_Id !=null}">
								<div><a href="#" onclick="reportWindow()">신고</a></div>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><img id="like" src="" style="width:20px; height:20px;">&nbsp;
						<span id="likeNum">${requestScope.bvo.likes}</span></td>
						<td colspan="4"></td>
						<td align="right"><img
							src="${pageContext.request.contextPath}/img/listbtn.jpg"
							style="width: 42px; height: 30px;" onclick="sendList()">&nbsp;&nbsp;
							<c:if test="${requestScope.bvo.memberVO.member_Id==sessionScope.mvo.member_Id}">
							 <img src="${pageContext.request.contextPath}/img/updatebtn.jpg" style="width: 42px; height: 30px;" onclick="updateBoard()">&nbsp;&nbsp;
							 <img src="${pageContext.request.contextPath}/img/deletebtn.jpg" style="width: 42px; height: 30px;" onclick="deleteBoard()">
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			
			<!-- 댓글창 -->
			<div class="reply_container">
				<c:if test="${sessionScope.mvo != null}">
					<div class="replyList">
						<form name="replyWriteForm" action="DispatcherServlet"
							method="post">
							<input type="hidden" name="parent" value="0">
							<input type="hidden" name="reFlag" value="false">
							<input type="hidden" name="command" value="wrtieReply"> <input
								type="hidden" name="brdno" value="${requestScope.bvo.board_no }">
							<textarea class="reply_field" id="rememo" name="rememo"
								placeholder="댓글을 달아주세요." style="border:solid 3px #ffd100;"></textarea>
							<input type="button" id="writeReplyBtn" value="등록"
								onclick="fn_formSubmit()">
						</form>
					</div>
				</c:if>

				<!--  댓글 리스트  -->
				<div class="box_reply">
					<ul class="cmlist">
						<c:forEach items="${requestScope.replyList }" var="reply">
							<li>
								<div class="h">
							
									<span class="nickspan"> <c:if test="${reply.depth >=1 }">&nbsp;&nbsp;&nbsp;&nbsp;
									 <img class="reply_icon"
												src="${pageContext.request.contextPath}/img/reply_icon.png"
												width="20">
										</c:if>${reply.nickname}</span> <span class="cmdate">${reply.reply_date }</span>
									<span class="recmbtn"> <a href="#"
										onclick="fn_replyReply(${reply.reply_id })"> <img
											class="reply_icon"
											src="${pageContext.request.contextPath}/img/bu_arr.png">
											답글
									</a></span> <span class="cmbtn"><c:if
											test="${sessionScope.mvo.member_Id==reply.member_id}">
											<a href="#"
												onclick="fn_replyDelete(${reply.reply_id },${requestScope.bvo.board_no })">삭제</a>
											<a href="#" onclick="fn_replyUpdate(${reply.reply_id })">수정</a>
										</c:if></span>
								</div>
								<div class="cm_list_box"
									id="reply<c:out value="${reply.reply_id}"/>">
									<c:out value="${reply.description }" />
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<!--  댓글 수정시 나오는 부분 -->
				<div id="replyDiv" style="width: 99%; display: none">
					<form name="replyUpdateForm" action="DispatcherServlet"
						method="post">
						<input type="hidden" name="brdno"
							value="${requestScope.bvo.board_no } " /> <input type="hidden"
							name="reno"> <input type="hidden" name="command"
							value="updateReply">
						<textarea class="reply_field" name="rememo" rows="3" cols="60"
							maxlength="500"></textarea>
						<a href="#" onclick="fn_replyUpdateSave()">저장</a> <a href="#"
							onclick="fn_replyUpdateCancel()">취소</a>
					</form>
				</div>
			</div>
			<!-- 대댓글 창 -->
			<div id="replyDialog" style="width: 99%; display: none">
				<form name="form3" action="board6ReplySave" method="post">
				<input type="hidden" name="command" value="wrtieReply">
				<input type="hidden" name="reFlag" value="true">
					<input type="hidden" name="brdno"
						value="<c:out value="${requestScope.bvo.board_no }"/>"> <input
						type="hidden" name="reno"> <input type="hidden"
						name="reparent">
					<textarea class="reply_field" name="rememo" rows="3" cols="60" maxlength="500" style="border:solid 3px #ffd100;
					background-color: #FAFAAA;margin-left:10px;"></textarea>
					<a href="#" onclick="fn_replyReplySave()">저장</a> <a href="#"
						onclick="fn_replyReplyCancel()">취소</a>
				</form>
			</div>

			<!-- reply container -->
			<p id="back-top">
				<a href="#top"> <img class="top_btn"
					src="${pageContext.request.contextPath}/img/top_btn.png">
				</a>
			</p>

		</div>
		<!-- row -->

	</div>
	<!-- container -->


</body>
</html>