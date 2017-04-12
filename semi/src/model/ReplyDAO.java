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
			String sql = "select reply_id,member_id,nickname,description,to_char(reply_date,'YYYY.MM.DD HH24:MI'),depth,parent_id from alba_reply where article_id=? order by group_id asc,order_id asc";
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
	
	public ReplyVO getParentInfo(int prno) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ReplyVO vo = null;
		try{
			con=getConnection();
			String sql = "SELECT article_id, depth+1 depth, order_id+1 order_id  FROM alba_reply WHERE reply_id= ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, prno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				vo = new ReplyVO(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	
	public int getNextReplyId() throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try{
			con=getConnection();
			String sql = "select reply_id_seq.nextval from dual";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return result;
	}
	
	public int getParentsParentId(int prno) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try{
			con=getConnection();
			String sql = "select parent_id from alba_reply where reply_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, prno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return result;
	}
	
	
	public void insertNewReply(ReplyVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into alba_reply");
			sql.append("(reply_id,article_id,member_id,nickname,reply_date,description,group_id,parent_id,depth,order_id)");
			sql.append("values(?,?,?,?,sysdate,");
			sql.append("?,?,?,?,?)");
			ReplyVO pvo = null;
			int parent = vo.getParent_id();
			int reno = getNextReplyId();
			int group = 1;
			int depth,order_id;
			if(parent ==0){ // 글에 댓글 달때 
				group = reno;
				depth = 0;
				order_id= 1;
			}else{ // 대댓글 달기
				group = parent;
				pvo = getParentInfo(parent);
				depth = pvo.getDepth();
				order_id = pvo.getOrder_id();
				if(getParentsParentId(parent) != 0){
					parent = getParentsParentId(parent);
					group = parent;
				}
			}
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, reno);
			pstmt.setInt(2, vo.getArticle_id());
			pstmt.setString(3, vo.getMember_id());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, vo.getDescription());
			pstmt.setInt(6, group);
			pstmt.setInt(7, parent);
			pstmt.setInt(8, depth);
			pstmt.setInt(9, order_id);
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
