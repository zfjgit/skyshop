/**
 *
 */
package com.sitv.skyshop.tbataobao.dao;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.tbataobao.dao.annotation.Mybatis_CheckRemark;
import com.sitv.skyshop.tbataobao.domain.SysChecker;

/**
 * @author zfj20 @ 2018年3月24日
 */
@Mybatis_CheckRemark
public interface ISysCheckerDao extends ICrudDao<SysChecker> {

	SysChecker get(@Param("account") String account, @Param("pwd") String pwd);
}
