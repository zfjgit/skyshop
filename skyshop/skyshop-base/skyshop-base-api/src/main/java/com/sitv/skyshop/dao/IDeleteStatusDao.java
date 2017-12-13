/**
 *
 */
package com.sitv.skyshop.dao;

import com.sitv.skyshop.domain.IBaseType;

/**
 * 逻辑删除
 * 
 * @author zfj20 @ 2017年12月5日
 */
public interface IDeleteStatusDao<T extends IBaseType> {

	void updateDeleteStatus(T t);
}
