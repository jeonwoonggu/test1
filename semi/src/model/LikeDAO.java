package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class LikeDAO {
	private static LikeDAO dao=new LikeDAO();	
	private DataSource dataSource;
	private LikeDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static LikeDAO getInstance(){
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
	/**
	 * 좋아요 눌렀는지 안눌렀는지 확인하는 메서드 
	 * @param id
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public boolean likesIdCheck(String id, int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;	// false면 좋아요 안했음
		try{
			con=getConnection(); 
			String sql="select count(*) from alba_likes where member_id=? and board_no=?";
			pstmt=con.prepareStatement(sql);		
			pstmt.setString(1,id);
			pstmt.setInt(2, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)>0){
					flag=true;
				}
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return flag;
	}
	/**
	 * 좋아요
	 * @param id
	 * @param no
	 * @throws SQLException
	 */
	public void likeCount(String id, int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="insert into alba_likes(member_id, board_no) values(?,?)";
			pstmt=con.prepareStatement(sql);		
			pstmt.setString(1,id);
			pstmt.setInt(2, no);
			pstmt.executeUpdate();		
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 좋아요 취소
	 * @param id
	 * @param no
	 * @throws SQLException
	 */
	public void likeCancel(String id, int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="delete from alba_likes where member_id=? and board_no=?";
			pstmt=con.prepareStatement(sql);		
			pstmt.setString(1,id);
			pstmt.setInt(2, no);
			pstmt.executeUpdate();		
		}finally{
			closeAll(pstmt,con);
		}
	}
}
