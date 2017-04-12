package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

public class AdminBoardDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] arrIdx =request.getParameter("idx").toString().split(",");
		for (int i=0; i<arrIdx.length; i++) {
//		    testMapper.delete(Integer.parseInt(arrIdx[i]));
			System.out.println(arrIdx[i]);
			BoardDAO.getInstance().deletePosting(Integer.parseInt(arrIdx[i]));
		}
		return "redirect:DispatcherServlet?command=adminBoardList";
	}

}
