<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<%
	String admin_id = request.getParameter("id");
	String admin_pass = request.getParameter("pass");
	boolean b = MemberDAO.getInstance().adminLogin(admin_id, admin_pass);
	
	if(b){
		session.setAttribute("adminOk", admin_id);
%>
	<script>
		alert("관리자님 로그인 되셨습니다");
		location.href="admin_index.jsp";
		
	</script>
<% } else { %>
	<script>
		alert("아이디 및 비밀번호가 올바르지 않습니다.");
		location.href=history.back();
	</script>

<% } %>