/**
 *
 */
package com.sitv.skyshop.service;

import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.dto.Dto;

/**
 * @author zfj20 @ 2017年12月5日
 */
public interface IDeleteStatusService<I extends Dto> {

	@Transactional(readOnly = false)
	void updateDeleteStatus(I t);
}
