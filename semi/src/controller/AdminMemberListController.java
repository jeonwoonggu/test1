package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class AdminMemberListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<MemberVO> list= MemberDAO.getInstance().getMemberAll();
		request.setAttribute("memlist", list);
		return "admin/membermanager.jsp";
	}

}
