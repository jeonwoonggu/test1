package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LikeDAO;

public class LikesIdCheckController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		int no=Integer.parseInt(request.getParameter("no"));
		boolean flag=LikeDAO.getInstance().likesIdCheck(id, no);
		out.print(flag);
		out.close();
		return "AjaxView";
	}
}
