package model;

public class ReplyVO {
	private int reply_id; //댓글번호
	private int article_id; // 게시물 번호
	private String member_id; //작성자 아이디
	private String nickname;
	private String reply_date; // 작성일자
	private String description; // 댓글 내용
	private int group_id; // 댓글과 대댓글 하나의 그룹번호
	private int parent_id; // 부모댓글 표시
	private int depth; //몇대손인지
	private int order_id; // 게시물 기준으로 몇번째인지
	
	public ReplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyVO(int reply_id, int article_id, String member_id, String nickname, String reply_date,
			String description, int group_id, int parent_id, int depth, int order_id) {
		super();
		this.reply_id = reply_id;
		this.article_id = article_id;
		this.member_id = member_id;
		this.nickname = nickname;
		this.reply_date = reply_date;
		this.description = description;
		this.group_id = group_id;
		this.parent_id = parent_id;
		this.depth = depth;
		this.order_id = order_id;
	}

	public ReplyVO(int reply_id,String member_id,String nickname, String description, String reply_date, int depth) {
		this.reply_id = reply_id;
		this.member_id = member_id;
		this.nickname = nickname;
		this.description = description;
		this.reply_date = reply_date;
		this.depth = depth;
	}
	public ReplyVO(String member_id,String nickname, int article_id, String description) {
		this.member_id = member_id;
		this.nickname = nickname;
		this.article_id = article_id;
		this.description = description;
	}
	public ReplyVO(int reply_id, String description) {
		this.reply_id = reply_id;
		this.description = description;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "ReplyVO [reply_id=" + reply_id + ", article_id=" + article_id + ", member_id=" + member_id
				+ ", nickname=" + nickname + ", reply_date=" + reply_date + ", description=" + description
				+ ", group_id=" + group_id + ", parent_id=" + parent_id + ", depth=" + depth + ", order_id=" + order_id
				+ "]";
	}
	
	
	
}
