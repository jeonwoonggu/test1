package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class ForgetIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name=request.getParameter("name");
		String residentNumber="";
		residentNumber+=request.getParameter("residentYear");
		if(request.getParameter("residentMonth").length()==1){
			residentNumber+=0+request.getParameter("residentMonth");
		}else{
			residentNumber+=request.getParameter("residentMonth");
		}
		if(request.getParameter("residentDay").length()==1){
			residentNumber+=0+request.getParameter("residentDay");
		}else{
			residentNumber+=request.getParameter("residentDay");
		}
		String id = MemberDAO.getInstance().forgetId(name, residentNumber);
		if(id==null){
			return "redirect:member/forgetpassword_fail.jsp";
		}else{
			request.setAttribute("memberId", id);
			return "member/forgetId_ok.jsp";
		}
	}

}
