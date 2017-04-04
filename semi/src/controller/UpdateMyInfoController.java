package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class UpdateMyInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId=request.getParameter("memberId");
		String password=request.getParameter("password");
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
		String gender=request.getParameter("gender");
		String nickName=request.getParameter("nickName");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		MemberVO vo=new MemberVO(memberId,password,name,residentNumber,gender,nickName,tel,address);
		MemberDAO.getInstance().updateMyInfo(vo);
		return "redirect:member/myInfo_result.jsp?memberId="+memberId;
	}

}
