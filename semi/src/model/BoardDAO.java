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
			sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.nickName,m.deletemember FROM(");
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
				if(rs.getString(9).equals("true")){
					mvo.setNickName(rs.getString(8));					
				} else {
					mvo.setNickName("탈퇴회원");
				}
				bvo.setMemberVO(mvo);
				int replycount = this.getReplycount_dao(bvo.getBoard_no());
				bvo.setReplycount(replycount);
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
   public BoardVO getPostingByNo(int board_no) throws SQLException{
      BoardVO bvo=null;
      Connection con=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      try{
         con=getConnection();
         StringBuilder sql=new StringBuilder();
         sql.append("select b.title, to_char(b.time_posted,'YYYY.MM.DD  HH24:MI:SS') as time_posted, ");
         sql.append("b.content, b.hits, b.likes, b.member_id, m.name, m.nickname, m.deletemember, b.job, b.startdate, b.enddate ");
         sql.append("from alba_board b, alba_member m ");
         sql.append("where b.member_id=m.member_id and b.board_no=?");      
         pstmt=con.prepareStatement(sql.toString());
         pstmt.setInt(1, board_no);
         rs=pstmt.executeQuery();      
         if(rs.next()){
            bvo=new BoardVO();
            bvo.setBoard_no(board_no);
            bvo.setTitle(rs.getString("title"));
            bvo.setContent(rs.getString("content"));               
            bvo.setHits(rs.getInt("hits"));
            bvo.setLikes(rs.getInt("likes"));
            bvo.setTimePosted(rs.getString("time_posted"));
            bvo.setJob(rs.getString("job"));
            bvo.setStartDate(rs.getString("startdate"));
            bvo.setEndDate(rs.getString("enddate"));
            MemberVO mvo=new MemberVO();
            mvo.setMember_Id(rs.getString("member_id"));
            mvo.setName(rs.getString("name"));
            if(rs.getString(9).equals("true")){
				mvo.setNickName(rs.getString("nickname"));					
			} else {
				mvo.setNickName("탈퇴회원");
			}
            bvo.setMemberVO(mvo);
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
			sql.append("insert into alba_board(board_no,category,title,content,member_id, job, startdate, enddate, time_posted)");
			sql.append(" values(board_no_seq.nextval,?,?,?,?,?,?,?,sysdate)");			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMemberVO().getMember_Id());
			pstmt.setString(5, vo.getJob());
			pstmt.setString(6, vo.getStartDate());
			pstmt.setString(7, vo.getEndDate());
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
	public void deletePosting(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			pstmt=con.prepareStatement("delete from alba_board where board_no=?");
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
			pstmt=con.prepareStatement("update alba_board set title=?,content=?, category=? where board_no=?");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getCategory());
			pstmt.setInt(4, vo.getBoard_no());	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	public ArrayList<BoardVO> getCategoryList(PagingBean pagingBean, String cate) throws SQLException {
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.name FROM(");
			sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
			sql.append("alba_board");
			sql.append(") b,alba_member m where b.member_id=m.member_id and category=? and rnum between ? and ? ");
			sql.append(" order by board_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, cate);
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
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
	public ArrayList<BoardVO> getHitsList(PagingBean pagingBean) throws SQLException {
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.name FROM(");
			sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
			sql.append("alba_board");
			sql.append(") b,alba_member m where b.member_id=m.member_id and rnum between ? and ? ");
			sql.append(" order by b.hits desc");
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
	public ArrayList<BoardVO> getSympathyList(PagingBean pagingBean) throws SQLException {
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.name FROM(");
			sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
			sql.append("alba_board");
			sql.append(") b,alba_member m where b.member_id=m.member_id and rnum between ? and ? ");
			sql.append(" order by b.likes desc");
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
	public ArrayList<BoardVO> getSearchPostingList(PagingBean pagingBean, String word, String opt) throws SQLException{
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			if(opt.equals("번호")){
			sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.nickName,m.deletemember FROM(");
			sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
			sql.append("alba_board");
			sql.append(") b,alba_member m where b.member_id=m.member_id and board_no LIKE ? and rnum between ? and ? ");
			sql.append(" order by board_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + word + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			}else if(opt.equals("카테고리")){
				sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.nickName,m.deletemember FROM(");
				sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
				sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
				sql.append("alba_board");
				sql.append(") b,alba_member m where b.member_id=m.member_id and category LIKE ? and rnum between ? and ? ");
				sql.append(" order by board_no desc");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, word);
				pstmt.setInt(2, pagingBean.getStartRowNumber());
				pstmt.setInt(3, pagingBean.getEndRowNumber());	
			}else if(opt.equals("제목")){
				sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.nickName,m.deletemember FROM(");
				sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
				sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
				sql.append("alba_board");
				sql.append(") b,alba_member m where b.member_id=m.member_id and title LIKE ? and rnum between ? and ? ");
				sql.append(" order by board_no desc");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, pagingBean.getStartRowNumber());
				pstmt.setInt(3, pagingBean.getEndRowNumber());	
			}else if(opt.equals("작성자")){
				sql.append("SELECT b.board_no,b.category,b.title,b.time_posted,b.hits,b.likes,b.member_id,m.nickName,m.deletemember FROM(");
				sql.append("SELECT row_number() over(order by board_no desc) as rnum,board_no,category,title,");
				sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,likes,member_id FROM ");
				sql.append("alba_board");
				sql.append(") b,alba_member m where b.member_id=m.member_id and nickName LIKE ? and rnum between ? and ? ");
				sql.append(" order by board_no desc");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, pagingBean.getStartRowNumber());
				pstmt.setInt(3, pagingBean.getEndRowNumber());	
			}
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
				if(rs.getString(9).equals("true")){
					mvo.setNickName(rs.getString(8));					
				} else {
					mvo.setNickName("탈퇴회원");
				}
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
	 * 좋아요수 증가 
	 * @param board_no
	 * @throws SQLException
	 */
	public void updateLikes(int board_no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="update alba_board set likes=likes+1 where board_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	/**
	 * 좋아요수 감소 
	 * @param board_no
	 * @throws SQLException
	 */
	public void updateLikesCancel(int board_no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="update alba_board set likes=likes-1 where board_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	public int getLikesNum(int board_no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int likes=0;
		try{
			con=getConnection(); 
			String sql="select likes from alba_board where board_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();	
			if(rs.next()){		
				likes=rs.getInt(1);
			}		
		}finally{
			closeAll(rs,pstmt,con);
		}
		return likes;
	}
	public int getReplycount_dao(int board_no) throws SQLException {//리스트 표시 시 댓글 수 받아오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			String sql = "select r.reply_count from(select article_id, count(*) as reply_count from alba_reply group by article_id)r, alba_board b where r.article_id = b.board_no and b.board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
}
