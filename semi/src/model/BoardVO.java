package model;

public class BoardVO {
	private int no;
	private String category;
	private String title;
	private String content;
	private String timePosted;
	private int hits;
	private int ghits;
	private MemberVO memberVO;
	public BoardVO() {
		super();		
	}	
	
	public BoardVO(int no, String category, String title, String content, String timePosted, int hits, int ghits,
			MemberVO memberVO) {
		super();
		this.no = no;
		this.category = category;
		this.title = title;
		this.content = content;
		this.timePosted = timePosted;
		this.hits = hits;
		this.ghits = ghits;
		this.memberVO = memberVO;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getGhits() {
		return ghits;
	}

	public void setGhits(int ghits) {
		this.ghits = ghits;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content="
				+ content + ", hits=" + hits + ", timePosted=" + timePosted
				+ ", memberVO=" + memberVO + "]";
	}
	
}
