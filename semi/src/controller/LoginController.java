package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		//System.out.println(memberId);
		MemberVO mvo = MemberDAO.getInstance().login(memberId, password);
		HttpSession session = request.getSession();
		session.setAttribute("mvo", mvo);
		//System.out.println(mvo);
		return "/member/login_result.jsp";
	}

}
