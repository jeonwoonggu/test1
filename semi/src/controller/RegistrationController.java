package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class RegistrationController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId=request.getParameter("memberId");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String residentNumber="";
		residentNumber+=request.getParameter("residentYear");
		residentNumber+=request.getParameter("residentMonth");
		residentNumber+=request.getParameter("residentDay");
		String gender=request.getParameter("gender");
		String nickName=request.getParameter("nickName");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		MemberVO vo=new MemberVO(memberId,password,name,residentNumber,gender,nickName,tel,address);	
		//System.out.println(vo);
		MemberDAO.getInstance().registration(vo);
		return "redirect:member/registration_result.jsp?memberId="+memberId;
	}

}