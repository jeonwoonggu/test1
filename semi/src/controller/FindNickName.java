package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class FindNickName implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String nickName=request.getParameter("nickName");
		String nick = MemberDAO.getInstance().findNickName(nickName);
		//System.out.println(nickName);
		//System.out.println(nick);
		if(nick==null){
			out.print(true);
		} else {
			out.print(false);
		}
		out.close();
		return "AjaxView";
	}

}
