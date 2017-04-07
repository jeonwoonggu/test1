package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardVO;

public class UpdatePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		int no=Integer.parseInt(request.getParameter("no"));
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardVO vo=new BoardVO();
		vo.setBoard_no(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setCategory(category);
		BoardDAO.getInstance().updatePosting(vo);			
		String path="redirect:DispatcherServlet?command=showContentNotHit&no="+vo.getBoard_no();
		return path;
	}

}







