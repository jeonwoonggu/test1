package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;
import model.ListVO;
import model.PagingBean;

public class SearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//int totalCount=BoardDAO.getInstance().getTotalContentCount();
		String word = request.getParameter("word");
		String pno=request.getParameter("pageNo");
		String opt=request.getParameter("opt");
		int searchCount=BoardDAO.getInstance().getSearchCount(word,opt);
		//System.out.println(searchCount);
		PagingBean pagingBean=null;
		if(pno==null){
			pagingBean=new PagingBean(searchCount);
		}else{
			pagingBean=new PagingBean(searchCount,Integer.parseInt(pno));
		}
		ArrayList<BoardVO> list=BoardDAO.getInstance().getSearchPostingList(pagingBean,word,opt);
		ListVO listVO=new ListVO(list,pagingBean);
		request.setAttribute("svo", listVO);
		return "board/search.jsp";
	}

}
