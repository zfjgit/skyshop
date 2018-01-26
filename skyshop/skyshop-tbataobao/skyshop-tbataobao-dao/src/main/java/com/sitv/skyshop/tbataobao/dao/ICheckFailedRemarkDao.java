/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.tbataobao.dao.annotation.Mybatis_CheckRemark;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark;

/**
 * @author zfj20 @ 2018年3月23日
 */
@Mybatis_CheckRemark
public interface ICheckFailedRemarkDao extends ICrudDao<CheckFailedRemark> {

	CheckFailedRemark getBy(@Param("subjectId") Long subjectId, @Param("subjectTypeCode") Integer subjectTypeCode);

	void inserts(@Param("list") List<CheckFailedRemark> list);
}
