package model;

public class LikeVO {
	private String memberId;
	private String boardNo;
	public LikeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeVO(String memberId, String boardNo) {
		super();
		this.memberId = memberId;
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "LikeVO [memberId=" + memberId + ", boardNo=" + boardNo + "]";
	}
}
