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
		String reFlag = request.getParameter("reFlag");
		int parent = 0;
		if(reFlag.equals("true")){
			int reparent = Integer.parseInt(request.getParameter("reparent"));
			parent = reparent;
		}else{ 
			parent = Integer.parseInt(request.getParameter("parent"));
		}

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
			ReplyDAO.getInstance().insertNewReply(new ReplyVO(memberId,nickname,articleId,description,parent));
			url = "redirect:DispatcherServlet?command=showContentNotHit&no="+no+"&memberId="+mvo.getMember_Id();
		}
		return url;
	}

}
