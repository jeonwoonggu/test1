package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private DataSource dataSource;

	private MemberDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		closeAll(null, pstmt, con);
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public MemberVO login(String memberId, String password) throws SQLException {
		MemberVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select member_id,password,name,nickname from alba_member where member_id=? and password=? and deletemember='true'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	public String findMemberId(String memberId) throws SQLException {
		String id = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select member_id from alba_member where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return id;
	}

	public String findNickName(String nickName) throws SQLException {
		String nick = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select nickname from alba_member where nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nick = rs.getString(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return nick;
	}

	public void registration(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into alba_member(member_id,name,address,tel,residentnumber,gender,password,nickname)"
					+ " values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMember_Id());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getResidentNumber());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getPassword());
			pstmt.setString(8, vo.getNickName());
			pstmt.executeUpdate();

		} finally {
			closeAll(pstmt, con);
		}
	}

	public MemberVO findMyInfo(String memberId) throws SQLException {
		MemberVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select member_id,name,residentnumber,gender,nickname,tel,address from alba_member where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	public void updateMyInfo(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update alba_member set password=?,residentnumber=?,gender=?,nickname=?,tel=?,address=? where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getResidentNumber());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getNickName());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getMember_Id());
			pstmt.executeUpdate();

		} finally {
			closeAll(pstmt, con);
		}
	}

	public void deleteMember(String memberId, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update alba_member set deletemember='false' where member_id=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
			pstmt.executeUpdate();

		} finally {
			closeAll(pstmt, con);

		}

	}
	
	public MemberVO forgetPassword(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO passVO = null;
		try {
			con = getConnection();
			String sql = "select member_id,password,name,nickname,deletemember from alba_member where member_id=? and name=? and residentnumber=? and tel=? and deletemember='true'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMember_Id());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getResidentNumber());
			pstmt.setString(4, vo.getTel());
			rs = pstmt.executeQuery();
			if(rs.next()){
					passVO = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} finally {
			closeAll(rs, pstmt, con);

		}
		return passVO;
	}
	
	public void changePassword(String memberId, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update alba_member set password=? where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	public String forgetId(String name,String residentNumber) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id=null;
		try {
			con = getConnection();
			String sql = "select member_id,deletemember from alba_member where name=? and residentnumber=? and deletemember='true'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name );
			pstmt.setString(2,residentNumber);
			rs = pstmt.executeQuery();
			if(rs.next()){
					id=rs.getString(1);
			}
		} finally {
			closeAll(rs, pstmt, con);

		}
		return id;
	}



}
