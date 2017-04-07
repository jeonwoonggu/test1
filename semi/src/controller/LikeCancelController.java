package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.LikeDAO;

public class LikeCancelController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("memberId");
		int no=Integer.parseInt(request.getParameter("no"));
		LikeDAO.getInstance().likeCancel(id, no);
		BoardDAO.getInstance().updateLikesCancel(no);
		int likeNum=BoardDAO.getInstance().getLikesNum(no);
		out.print(likeNum);
		out.close();
		return "AjaxView";
	}
}
