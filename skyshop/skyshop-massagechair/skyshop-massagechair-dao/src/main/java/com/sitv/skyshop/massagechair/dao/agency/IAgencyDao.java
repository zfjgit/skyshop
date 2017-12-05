/**
 *
 */
package com.sitv.skyshop.massagechair.dao.agency;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.dao.IDeleteStatusDao;
import com.sitv.skyshop.dao.MyBatisDao;
import com.sitv.skyshop.massagechair.domain.agency.Agency;

/**
 * @author zfj20 @ 2017年12月5日
 */
@MyBatisDao
public interface IAgencyDao extends ICrudDao<Agency>, IDeleteStatusDao<Agency> {

}
