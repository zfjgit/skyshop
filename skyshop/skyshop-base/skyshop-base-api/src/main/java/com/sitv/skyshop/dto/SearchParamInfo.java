/**
 *
 */
package com.sitv.skyshop.dto;

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
public class SearchParamInfo<T extends Dto> extends Dto {

	private static final long serialVersionUID = -2099359897306716873L;

	private T param;

	private int page;
	private int pageSize;

	private String sortField;
	private String sortDir;

}
