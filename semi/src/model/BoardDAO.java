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
	 * @param pageNo
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
			sql.append("SELECT b.no,b.category,b.title,b.time_posted,b.hits,b.ghits,b.id,m.name FROM(");
			sql.append("SELECT row_number() over(order by no desc) as rnum,no,category,title,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,ghits,id FROM ");
			sql.append("board_login_inst");
			sql.append(") b,board_member m where b.id=m.id and rnum between ? and ? ");
			sql.append(" order by no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs=pstmt.executeQuery();	
			//목록에서 게시물 content는 필요없으므로 null로 setting
			//select no,title,time_posted,hits,id,name
			while(rs.next()){		
				BoardVO bvo=new BoardVO();
				bvo.setNo(rs.getInt(1));
				bvo.setCategory(rs.getString(2));
				bvo.setTitle(rs.getString(3));
				bvo.setTimePosted(rs.getString(4));
				bvo.setHits(rs.getInt(5));
				bvo.setGhits(rs.getInt(6));
				MemberVO mvo=new MemberVO();
				mvo.setId(rs.getString(7));
				mvo.setName(rs.getString(8));
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
			String sql="select count(*) from board_login_inst";
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
     * @param no
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
			StringBuilder sql=new StringBuilder();
			sql.append("select b.title,to_char(b.time_posted,'YYYY.MM.DD  HH24:MI:SS') as time_posted");
			sql.append(",b.content,b.hits,b.id,m.name");
			sql.append(" from board_login_inst b,board_member m");
			sql.append(" where b.id=m.id and b.no=?");		
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
		
			if(rs.next()){
				bvo=new BoardVO();
				bvo.setNo(no);
				bvo.setTitle(rs.getString("title"));
				bvo.setContent(rs.getString("content"));				
				bvo.setHits(rs.getInt("hits"));
				bvo.setTimePosted(rs.getString("time_posted"));
				MemberVO mvo=new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				bvo.setMemberVO(mvo);
			}
			//System.out.println("dao getContent:"+bvo);
		}finally{
			closeAll(rs,pstmt,con);
		}
		return bvo;
	}
	
	/**
	 * 조회수 증가 
	 * @param no
	 * @throws SQLException
	 */
	public void updateHit(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="update board_login_inst set hits=hits+1 where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);	
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
			//insert into board_login_inst(no,title,content,id,time_posted) values(board_login_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql=new StringBuilder();
			sql.append("insert into board_login_inst(no,title,content,id,time_posted)");
			sql.append(" values(board_login_inst_seq.nextval,?,?,?,sysdate)");			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMemberVO().getId());
			pstmt.executeUpdate();			
			pstmt.close();
			pstmt=con.prepareStatement("select board_login_inst_seq.currval from dual");
			rs=pstmt.executeQuery();
			if(rs.next())
			vo.setNo(rs.getInt(1));			
		}finally{
			closeAll(rs,pstmt,con);
		}
	}	

	/**
	 * 글번호에 해당하는 게시물을 삭제하는 메서드
	 * @param no
	 * @throws SQLException
	 */
	public void deletePosting(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			pstmt=con.prepareStatement("delete from board_login_inst where no=?");
			pstmt.setInt(1, no);		
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
			pstmt=con.prepareStatement("update board_login_inst set title=?,content=? where no=?");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
}



















