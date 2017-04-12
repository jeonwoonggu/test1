package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ReportDAO {
	private static ReportDAO dao=new ReportDAO();	
	private DataSource dataSource;
	private ReportDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static ReportDAO getInstance(){
		return dao;
	}
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); 
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt,con);
	}	
	public boolean reportIdCheck(String id, int no) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false; 
		try {
			con = getConnection();
			String sql = "select count(*) from alba_report where member_id=? and board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					flag = true;
				}
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return flag;
	}
	
	public void ReportSend(String id, int no,String content) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="insert into alba_report(member_id,board_no,content) values(?,?,?)";
			pstmt=con.prepareStatement(sql);		
			pstmt.setString(1,id);
			pstmt.setInt(2, no);
			pstmt.setString(3, content);
			pstmt.executeUpdate();		
		}finally{
			closeAll(pstmt,con);
		}
	}
	public int getTotalReport(int no) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			con = getConnection();
			String sql = "select count(*) from alba_report where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					result = rs.getInt(1);
				}
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}
	
	
	//관리자 - 신고 글 
		public ArrayList<ReportVO> getReportAll() throws SQLException{
			ArrayList<ReportVO> list = new ArrayList<ReportVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from alba_report";
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					list.add(new ReportVO(rs.getString(1),rs.getString(2),rs.getString(3)));
				}
			} finally {
				closeAll(rs, pstmt, con);

			}
			return list;
		}
}
