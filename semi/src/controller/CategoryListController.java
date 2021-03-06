package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;
import model.ListVO;
import model.PagingBean;

public class CategoryListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalCount = BoardDAO.getInstance().getTotalContentCount();
		String pno = request.getParameter("pageNo");
		String cate = request.getParameter("cate");
		PagingBean pagingBean = null;
		if (pno == null) {
			pagingBean = new PagingBean(totalCount);
		} else {
			pagingBean = new PagingBean(totalCount, Integer.parseInt(pno));
		}
		ArrayList<BoardVO> list = BoardDAO.getInstance().getCategoryList(pagingBean, cate);
		ListVO listVO = new ListVO(list, pagingBean);
		System.out.println(cate);
		request.setAttribute("lvo", listVO);
		request.setAttribute("kindList", "categoryList");
		request.setAttribute("cate", cate);
		return "board/list.jsp";
	}

}
