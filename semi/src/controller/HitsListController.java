package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;
import model.ListVO;
import model.PagingBean;

public class HitsListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalCount=BoardDAO.getInstance().getTotalContentCount();
		String pno=request.getParameter("pageNo");
		PagingBean pagingBean=null;
		if(pno==null){
			pagingBean=new PagingBean(totalCount);
		}else{
			pagingBean=new PagingBean(totalCount,Integer.parseInt(pno));
		}
		ArrayList<BoardVO> list=BoardDAO.getInstance().getHitsList(pagingBean);
		ListVO listVO=new ListVO(list,pagingBean);
		request.setAttribute("lvo", listVO);
		request.setAttribute("kindList", "hitsList");
		return "board/list.jsp";
	}

}
