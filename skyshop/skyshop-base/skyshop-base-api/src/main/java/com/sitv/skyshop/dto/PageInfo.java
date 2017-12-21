/**
 *
 */
package com.sitv.skyshop.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zfj20
 * @version 2017年7月31日
 */
@Getter
@Setter
@ToString(callSuper = true)
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

	// 是否为第一页
	private boolean isFirstPage = false;
	// 是否为最后一页
	private boolean isLastPage = false;
	// 是否有前一页
	private boolean hasPreviousPage = false;
	// 是否有下一页
	private boolean hasNextPage = false;

	private List<T> datas;

	private BigDecimal sum;

	public PageInfo(List<T> list, int pageNum, int pageSize, int pages, long total) {
		this.datas = list;
		this.current = pageNum;
		this.pages = pages;
		this.pageSize = pageSize;
		this.total = total;

		calcPage();

		judgePageBoudary();
	}

	public PageInfo(List<T> list, int pageNum, int pageSize, int pages, long total, BigDecimal sum) {
		this.datas = list;
		this.current = pageNum;
		this.pages = pages;
		this.pageSize = pageSize;
		this.total = total;
		this.sum = sum;

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
		isLastPage = current == pages || pages == 0;
		;
		hasPreviousPage = current > 1;
		hasNextPage = current < pages;
	}

}
