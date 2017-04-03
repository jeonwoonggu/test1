package model;

import java.util.ArrayList;
/**
 * 리스트 화면에 보여질 게시물 리스트와 페이징 정보 객체를 저장하는 클래스 
 * @author kosta-00-kangsa-001
 *
 */
public class ListVO {
	private ArrayList<BoardVO> list;
	private PagingBean pagingBean;
	public ListVO() {
		super();
	}
	public ListVO(ArrayList<BoardVO> list, PagingBean pagingBean) {
		super();
		this.list = list;
		this.pagingBean = pagingBean;
	}
	public ArrayList<BoardVO> getList() {
		return list;
	}
	public void setList(ArrayList<BoardVO> list) {
		this.list = list;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	@Override
	public String toString() {
		return "ListVO [list=" + list + ", pagingBean=" + pagingBean + "]";
	}
	
}
