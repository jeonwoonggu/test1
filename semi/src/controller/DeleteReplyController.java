package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReplyDAO;

public class DeleteReplyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "index.jsp";
		String rno = request.getParameter("rno");
		String bno = request.getParameter("bno");
		ReplyDAO.getInstance().deleteReply(Integer.parseInt(rno));
		url="redirect:DispatcherServlet?command=showContent&no="+bno;
		return url;
	}

}
