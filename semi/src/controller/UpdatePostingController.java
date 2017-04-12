package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDAO;
import model.BoardVO;

public class UpdatePostingController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println(request);
		String savePath = "C:\\java-kosta\\semi-workspace\\AlbaSsul\\AlbaSsul\\WebContent\\img\\upload";
		int sizeLimit = 1024*1024*15;
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		int no=Integer.parseInt(multi.getParameter("no"));
		String category = multi.getParameter("category");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		BoardVO vo=new BoardVO();
		vo.setBoard_no(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setCategory(category);
		BoardDAO.getInstance().updatePosting(vo);
		
		String fileName = multi.getFilesystemName("m_file");
		String m_fileFullPath = savePath + "\\" + fileName;
		//System.out.println(fileName);
		//System.out.println(m_fileFullPath);
		String board_imgsrc = "img/upload/"+fileName;
		//System.out.println(fileName);
		if(fileName!=null){
			BoardDAO.getInstance().imgUpdate(no, board_imgsrc);
		}
		String path="redirect:DispatcherServlet?command=showContentNotHit&no="+vo.getBoard_no();
		return path;
	}

}







