package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportDAO;
import model.ReportVO;

public class AdminReportListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<ReportVO> report = ReportDAO.getInstance().getReportAll();
		request.setAttribute("report", report);
		return "admin/admin_report.jsp";
	}

}
