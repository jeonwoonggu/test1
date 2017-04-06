package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class ChangePasswordController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		MemberDAO.getInstance().changePassword(memberId, password);
		return "redirect:member/changepassword_ok.jsp";
	}

}
