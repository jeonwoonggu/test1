package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReplyDAO;
import model.ReplyVO;

public class UpdateReplyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rememo = request.getParameter("rememo");
		String brdno = request.getParameter("brdno");
		String reno = request.getParameter("reno");
		ReplyVO vo = new ReplyVO(Integer.parseInt(reno), rememo);
		ReplyDAO.getInstance().updateReply(vo);
		return "redirect:DispatcherServlet?command=showContent&no="+brdno;
	}

}
