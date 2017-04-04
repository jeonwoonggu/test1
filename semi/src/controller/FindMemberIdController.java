package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class FindMemberIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String memberId=request.getParameter("memberId");
		String id = MemberDAO.getInstance().findMemberId(memberId);
		if(id==null){
			out.print(true);
		} else {
			out.print(false);
		}
		out.close();
		return "AjaxView";
	}

}
