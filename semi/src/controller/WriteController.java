package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardVO;
import model.MemberVO;

public class WriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		/*if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}*/
		String category= request.getParameter("catogory");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardVO bvo=new BoardVO();
		bvo.setCategory(category);
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setMemberVO((MemberVO)session.getAttribute("mvo"));		
		BoardDAO.getInstance().posting(bvo);
		String path="redirect:DispatcherServlet?command=showContentNotHit&no="+bvo.getBoard_no();
		return path;
	}
}
