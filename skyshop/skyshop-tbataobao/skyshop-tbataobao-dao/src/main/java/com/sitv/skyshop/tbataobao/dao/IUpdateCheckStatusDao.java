/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.MyBatisDao;

/**
 * @author zfj20 @ 2018年3月24日
 */
@MyBatisDao
public interface IUpdateCheckStatusDao {
	void updateCheckStatus(@Param("id") Long id, @Param("checkStatusCode") String checkStatuCode);

	void batchUpdateCheckStatus(@Param("checkStatusCode") String code, @Param("ids") List<Long> ids);
}
