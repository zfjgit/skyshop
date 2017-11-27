/**
 * 
 */
package com.sitv.skyshop.dto;

import java.util.List;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
public class PageInfo<T> extends Dto {

	private static final long serialVersionUID = 2339860035706764430L;

	private int current;
	private long total;
	private int pages;
	private int pageSize;
	
	private int prev;
	private int next;
	
	private String sortDir;
	private String sortField;
	
	//是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
	
	private List<T> datas;
	
	/**
	 * @param list
	 * @param pageNum
	 * @param pageSize2
	 * @param pages2
	 * @param total2
	 */
	public PageInfo(List<T> list, int pageNum, int pageSize, int pages, long total) {
		this.datas = list;
		this.current = pageNum;
		this.pages = pages;
		this.pageSize = pageSize;
		this.total = total;
		
		calcPage();
		
		judgePageBoudary();
	}
	
	 /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if (current > 1) {
            prev = current - 1;
        }
        if (current < pages) {
            next = current + 1;
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = current == 1;
        isLastPage = current == pages || pages == 0;;
        hasPreviousPage = current > 1;
        hasNextPage = current < pages;
    }

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> data) {
		this.datas = data;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
}
