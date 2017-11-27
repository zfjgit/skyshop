/**
 *
 */
package com.sitv.skyshop.dto;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
public class SearchParamInfo<T extends Dto> extends Dto {

	private static final long serialVersionUID = -2099359897306716873L;

	private T param;

	private int page;
	private int pageSize;

	private String sortField;
	private String sortDir;

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String orderField) {
		this.sortField = orderField;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String orderDir) {
		this.sortDir = orderDir;
	}

	public String toString() {
		return "SearchParamInfo [param=" + param + ", page=" + page + ", pageSize=" + pageSize + ", sortField=" + sortField + ", sortDir=" + sortDir + "]";
	}
}
