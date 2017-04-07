package model;

public class BoardVO {
	private int board_no;
	private String category;
	private String title;
	private String content;
	private String timePosted;
	private int hits;
	private int likes;
	private int replycount;
	private String job;
	private String startDate;
	private String endDate;
	private MemberVO memberVO;
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BoardVO(String category, String title, String content) {
		super();
		this.category = category;
		this.title = title;
		this.content = content;
	}

	public BoardVO(int board_no, String category, String title, String content, String timePosted, int hits, int likes,
			int replycount, String job, String startDate, String endDate, MemberVO memberVO) {
		super();
		this.board_no = board_no;
		this.category = category;
		this.title = title;
		this.content = content;
		this.timePosted = timePosted;
		this.hits = hits;
		this.likes = likes;
		this.job=job;
		this.startDate=startDate;
		this.endDate=endDate;
		this.memberVO = memberVO;
		this.replycount = replycount;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", timePosted=" + timePosted + ", hits=" + hits + ", likes=" + likes + ", memberVO=" + memberVO + "]";
	}
	
}