package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardVO;

public class UpdateViewController implements Controller {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		int no=Integer.parseInt(request.getParameter("no"));
		BoardVO vo = BoardDAO.getInstance().getPostingByNo(no);	
		String board_imgsrc = BoardDAO.getInstance().imgReload(no);
		request.setAttribute("bvo", vo);
		request.setAttribute("imgsrc", board_imgsrc);
		return "/board/update.jsp";
	}
}






