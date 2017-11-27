/**
 *
 */
package com.sitv.skyshop.service;

import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.dto.Dto;
import com.sitv.skyshop.dto.PageInfo;
import com.sitv.skyshop.dto.SearchParamInfo;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@Transactional
public interface IBaseService<I extends Dto> {
	I getOne(Long id);

	PageInfo<I> search(SearchParamInfo<I> q);

	@Transactional(readOnly = false)
	void deleteOne(Long id);

	@Transactional(readOnly = false)
	void updateOne(I t);

	@Transactional(readOnly = false)
	void createOne(I t);
}
