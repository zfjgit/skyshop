/**
 *
 */
package com.sitv.skyshop.dao;

import com.sitv.skyshop.domain.IBaseType;

/**
 * @author zfj20 @ 2017年12月5日
 */
public interface IDeleteStatusDao<T extends IBaseType> {

	void updateDeleteStatus(T t);
}
