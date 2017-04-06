package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class ForgetPasswordController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("memberId");
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
		String tel=request.getParameter("tel");
		MemberVO vo = new MemberVO(memberId,name,Integer.parseInt(residentNumber),tel);
		//System.out.println(vo);
		MemberVO passVO = MemberDAO.getInstance().forgetPassword(vo);
		//System.out.println(passVO);
		if (passVO==null){
			return "redirect:member/forgetpassword_fail.jsp";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("passVO", passVO);
			return "member/changepassword.jsp";
		}
	}

}
