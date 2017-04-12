package model;

public class ReportVO {
	private String memberId;
	private String boardNo;
	private String content;
	public ReportVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportVO(String memberId, String boardNo, String content) {
		super();
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.content = content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ReportVO [memberId=" + memberId + ", boardNo=" + boardNo + ", content=" + content + "]";
	}

	
}
