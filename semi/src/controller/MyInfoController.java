package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class MyInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		String id = ((MemberVO) session.getAttribute("mvo")).getMember_Id();
		MemberVO vo=MemberDAO.getInstance().findMyInfo(id);
		session.setAttribute("infoVO", vo);
		return "member/myInfo.jsp";
	}

}
