/**
 * 
 */
package com.sitv.skyshop.common.dao;

import com.sitv.skyshop.common.domain.Configuration;
import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.MyBatisDao;

/**
 * 
 * @author zfj20
 * @version 2017年8月5日
 */
@MyBatisDao
public interface IConfigurationDao extends ICrudDao<Configuration> {

}
