package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportDAO;

public class BoardReportController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		String reason = request.getParameter("reason");
		String id = request.getParameter("memId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		boolean reportCheck = ReportDAO.getInstance().reportIdCheck(id,boardNo);
		if(!reportCheck){// 신고 처음일때
			ReportDAO.getInstance().ReportSend(id,boardNo, reason);
			url = "board/report_result.jsp";
		}else{ // 이미 신고했을때
			url = "board/report_fail.jsp";
		}
		return url;
	}

}
