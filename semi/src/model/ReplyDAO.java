package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ReplyDAO {
	private static ReplyDAO dao=new ReplyDAO();
	private DataSource dataSource;
	private ReplyDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static ReplyDAO getInstance(){		
		return dao;
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) {
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		closeAll(pstmt,con);
	}
	private Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	/**
	 * 새로운 댓글을 입력할 때 
	 * 대댓글 끼리 정렬, 
	 * 댓글 단 순서대로 정렬해서 보여준다
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ReplyVO> getAllReplyList(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		try{
			con=getConnection();
			String sql = "select reply_id,member_id,nickname,description,to_char(reply_date,'YYYY.MM.DD HH24:MI'),depth from alba_reply where article_id=? order by group_id asc,order_id asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReplyVO vo = new ReplyVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				list.add(vo);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/**
	 * 새로운 댓글 작성
	 * @param vo
	 * @throws SQLException
	 */
	public void insertNewReply(ReplyVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into alba_reply");
			sql.append("(reply_id,article_id,member_id,nickname,reply_date,description,group_id)");
			sql.append("values(reply_id_seq.nextval,?,?,?,sysdate,");
			sql.append("?,reply_id_seq.nextval)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, vo.getArticle_id());
			pstmt.setString(2, vo.getMember_id());
			pstmt.setString(3, vo.getNickname());
			pstmt.setString(4, vo.getDescription());
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt, con);
		}
	}
	/**
	 * 댓글 삭제
	 * @param rno
	 * @throws SQLException
	 */
	public void deleteReply(int rno) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			String sql="DELETE FROM alba_reply WHERE reply_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt, con);
		}
	}
	
	/**
	 * 댓글 수정
	 * @param vo
	 * @throws SQLException
	 */
	public void updateReply(ReplyVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			String sql="UPDATE alba_reply SET description=? where reply_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getDescription());
			pstmt.setInt(2, vo.getReply_id());
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt, con);
		}
	}
	
	/**
	 * select b.*, (select count(*) from reboard where idx=b.num) as commentCount from board b
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public int getTotalReplyByNo(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try{
			con=getConnection();
			String sql = "select count(*) from alba_reply where article_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return result;
	}
}
