package entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable{
	private Long total; //总记录数
	private List rowsList;	   //每页显示的集合
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRowsList() {
		return rowsList;
	}
	public void setRowsList(List rowsList) {
		this.rowsList = rowsList;
	}
	public PageResult(Long total, List rowsList) {
		super();
		this.total = total;
		this.rowsList = rowsList;
	}
	public PageResult() {
		super();
	}
	
	
}
