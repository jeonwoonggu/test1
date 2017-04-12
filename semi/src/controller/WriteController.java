package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDAO;
import model.BoardVO;
import model.MemberVO;

public class WriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String savePath = "C:\\java-kosta\\semi-workspace\\AlbaSsul\\AlbaSsul\\WebContent\\img\\upload";

		int sizeLimit = 1024*1024*15;
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		String category= multi.getParameter("category");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String job = multi.getParameter("job");
		String startDate = multi.getParameter("startdate");
		String endDate = multi.getParameter("enddate");
		BoardVO bvo=new BoardVO();
		bvo.setCategory(category);
		bvo.setTitle(title);
		bvo.setContent(content);
		bvo.setJob(job);
		bvo.setStartDate(startDate);
		bvo.setEndDate(endDate);
		bvo.setMemberVO((MemberVO)session.getAttribute("mvo"));		
		BoardDAO.getInstance().posting(bvo);
		
		int img_no = bvo.getBoard_no();
		//System.out.println("img_no");
		String fileName = multi.getFilesystemName("m_file");
		String m_fileFullPath = savePath + "\\" + fileName;	
		//System.out.println(fileName);
		//System.out.println(m_fileFullPath);
		String board_imgsrc = "img/upload/"+fileName;
		BoardDAO.getInstance().imgPosting(img_no, board_imgsrc);
		
		String path="redirect:DispatcherServlet?command=showContentNotHit&no="+bvo.getBoard_no();
		return path;
	}
}
