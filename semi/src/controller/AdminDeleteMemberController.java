package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class AdminDeleteMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("memId");
		MemberDAO.getInstance().adminDeleteMember(id);
		return "DispatcherServlet?command=adminMemberList";
	}

}
