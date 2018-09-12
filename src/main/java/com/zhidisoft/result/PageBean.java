package com.zhidisoft.result;

public class PageBean {

	private Integer pageNumber; // 页码
	
	private Integer pageSize; // 每页的条目数    ，sql语句的 limit offset,pageSize 
	 
	private Integer offset; // 从结果集中获取当前页的起始索引(起始位置、偏移量)  ，sql语句的 limit offset,pageSize  

	private Integer total; // 总记录数
	
	private Object rows;  // 当前页的记录

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		if(pageNumber<1){
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize<1){
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}

	public Integer getOffset() {
		offset = (pageNumber-1) * pageSize;
		
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageBean [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", offset=" + offset + ", total="
				+ total + ", rows=" + rows + "]";
	}
	
}
