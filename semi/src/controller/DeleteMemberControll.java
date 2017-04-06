package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class DeleteMemberControll implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO vo=(MemberVO) session.getAttribute("mvo");
		String memberId=vo.getMember_Id();
		String password=request.getParameter("password");
		MemberDAO.getInstance().deleteMember(memberId,password);
		if(session!=null)
			session.invalidate();
		return "redirect:index.jsp";
	}

}
