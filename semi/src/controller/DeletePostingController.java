package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;

public class DeletePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		int no=Integer.parseInt(request.getParameter("no"));
		BoardDAO.getInstance().deletePosting(no);
		// 게시물 목록을 보여주기 위해
		// path를 DispatcherServlet?command=list setting하고
		// 리다이렉트 방식으로 이동시킨다. 
		return "redirect:DispatcherServlet?command=list";
	}

}






