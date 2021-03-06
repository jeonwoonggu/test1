package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;
import model.LikeDAO;
import model.ReplyDAO;
import model.ReplyVO;

public class ShowContentNotHitController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		String id=request.getParameter("memberId");
		// 개별 게시물 조회
		BoardVO vo = BoardDAO.getInstance().getPostingByNo(no);
		request.setAttribute("bvo", vo);
		// 댓글 리스트 받아오기
		ArrayList<ReplyVO> replyList = null;
		replyList = ReplyDAO.getInstance().getAllReplyList(no);
		// 공감 여부 확인
		boolean flag = LikeDAO.getInstance().likesIdCheck(id, no);
		String board_imgsrc = BoardDAO.getInstance().imgReload(no);
		request.setAttribute("replyList", replyList);
		request.setAttribute("likeCheck", flag);
		request.setAttribute("imgsrc", board_imgsrc);
		return "board/detail.jsp";
	}
}
