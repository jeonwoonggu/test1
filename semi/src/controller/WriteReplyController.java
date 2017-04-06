package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberVO;
import model.ReplyDAO;
import model.ReplyVO;

public class WriteReplyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "index.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String no = request.getParameter("brdno"); //게시물 번호
		String memberId=null;
		String nickname=null;
		int articleId;
		String description=null;
		if(mvo == null){
			return url;
		}else{
			memberId = mvo.getMember_Id();
			nickname = mvo.getNickName();
			articleId=Integer.parseInt(no);
			description = request.getParameter("rememo");
			ReplyDAO.getInstance().insertNewReply(new ReplyVO(memberId,nickname,articleId,description));
			url = "redirect:DispatcherServlet?command=showContent&no="+no;
		}
		return url;
	}

}
