/**
 *
 */
package com.sitv.skyshop.common.dao;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.common.domain.Address;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;

/**
 * @author zfj20
 * @version 2017年7月25日
 */
@MyBatisDao
public interface IAddressDao extends ICrudDao<Address> {

	Address getBy(@Param("code") String code);

}
