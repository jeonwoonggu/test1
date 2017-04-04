package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class BoardDAO {
	private static BoardDAO dao=new BoardDAO();	
	private DataSource dataSource;
	private BoardDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static BoardDAO getInstance(){
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
	 * 페이지 번호에 해당하는 게시물 목록 리스트를 반환하는 메서드 
	 * @param pageboard_no
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getPostingList(PagingBean pagingBean) throws SQLException{
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.nickName FROM(");
			sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
			sql.append("alba_board");
			sql.append(") b,alba_member m where b.member_id=m.member_id and rnum between ? and ? ");
			sql.append(" order by board_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs=pstmt.executeQuery();	
			//목록에서 게시물 content는 필요없으므로 null로 setting
			//select board_no,title,time_posted,hits,id,name
			while(rs.next()){		
				BoardVO bvo=new BoardVO();
				bvo.setBoard_no(rs.getInt(1));
				bvo.setCategory(rs.getString(2));
				bvo.setTitle(rs.getString(3));
				bvo.setTimePosted(rs.getString(4));
				bvo.setHits(rs.getInt(5));
				bvo.setLikes(rs.getInt(6));
				MemberVO mvo=new MemberVO();
				mvo.setMember_Id(rs.getString(7));
				mvo.setNickName(rs.getString(8));
				bvo.setMemberVO(mvo);
				list.add(bvo);			
			}		
			//System.out.println(list);
		}finally{
			closeAll(rs,pstmt,con);
		}
		return list;
	}
	/**
	 * 전체 게시물 수를 조회하는 메서드(페이징 처리를 위해 사용)
	 * @return
	 * @throws SQLException
	 */
	public int getTotalContentCount() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=-1;
		try{
			con=getConnection(); 
			String sql="select count(*) from alba_board";
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return count;
	}
	
    /**
     * Sequence 글번호로 게시물을 검색하는 메서드 
     * @param board_no
     * @return
     * @throws SQLException
     */
	public BoardVO getPostingByNo(int no) throws SQLException{
		BoardVO bvo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			String sql="select b.category, b.title, b.content, b.time_posted, b.hits, b.likes, m.nickname "
					+ "from alba_board b, alba_member m where m.member_id=b.member_id and board_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bvo=new BoardVO();
				bvo.setBoard_no(no);
				bvo.setTitle(rs.getString("title"));
				bvo.setCategory(rs.getString("category"));
				bvo.setContent(rs.getString("content"));				
				bvo.setTimePosted(rs.getString("time_posted"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setLikes(rs.getInt("likes"));
				MemberVO mvo=new MemberVO();
				mvo.setNickName(rs.getString("nickname"));
				bvo.setMemberVO(mvo);
				System.out.println(bvo.toString());
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return bvo;
	}
	
	/**
	 * 조회수 증가 
	 * @param board_no
	 * @throws SQLException
	 */
	public void updateHit(int board_no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="update alba_board set hits=hits+1 where board_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 게시물 등록 메서드  
	 * 게시물 등록 후 생성된 시퀀스를 BoardVO에 setting 한다. 
	 * @param vo
	 * @throws SQLException
	 */
	public void posting(BoardVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			//insert into alba_board(board_no,title,content,id,time_posted) values(board_no_seq.nextval,?,?,?,sysdate)
			StringBuilder sql=new StringBuilder();
			sql.append("insert into alba_board(board_no,category,title,content,member_id,time_posted)");
			sql.append(" values(board_no_seq.nextval,?,?,?,?,sysdate)");			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMemberVO().getMember_Id());
			pstmt.executeUpdate();			
			pstmt.close();
			pstmt=con.prepareStatement("select board_no_seq.currval from dual");
			rs=pstmt.executeQuery();
			if(rs.next())
			vo.setBoard_no(rs.getInt(1));	
		}finally{
			closeAll(rs,pstmt,con);
		}
	}	

	/**
	 * 글번호에 해당하는 게시물을 삭제하는 메서드
	 * @param board_no
	 * @throws SQLException
	 */
	public void deletePosting(int board_no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			pstmt=con.prepareStatement("delete from alba_board where board_no=?");
			pstmt.setInt(1, board_no);		
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 게시물 정보 업데이트하는 메서드 
	 * @param vo
	 * @throws SQLException
	 */
	public void updatePosting(BoardVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			pstmt=con.prepareStatement("update alba_board set title=?,content=? where board_no=?");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getBoard_no());	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
}



















