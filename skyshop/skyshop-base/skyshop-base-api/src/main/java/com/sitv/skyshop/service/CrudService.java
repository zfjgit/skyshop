/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sitv.skyshop.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.domain.DomainObject;
import com.sitv.skyshop.domain.IBaseType;
import com.sitv.skyshop.domain.ICheckCodeType;
import com.sitv.skyshop.dto.Dto;

import lombok.extern.slf4j.Slf4j;

/**
 * Service基类
 */
@Slf4j
@Transactional
public abstract class CrudService<D extends ICrudDao<T>, T extends IBaseType, I extends Dto> implements IBaseService<I> {

	/**
	 * 持久层对象
	 */
	protected D dao;

	/**
	 * 获取单条数据
	 *
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		T t = dao.get(id);
		if (t instanceof ICheckCodeType) {
			ICheckCodeType checkCodeType = (ICheckCodeType) t;
			if (!checkCodeType.verifyCheckCode()) {
				log.error("CHECK码检验失败：" + checkCodeType.getClass().getName() + "=" + checkCodeType.getCheckCode());
			}
		}
		return t;
	}

	/**
	 * 保存数据
	 *
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void create(T entity) {
		if (entity instanceof DomainObject) {
			((DomainObject) entity).setCreateTime(Calendar.getInstance());
		}
		dao.insert(entity);
	}

	/**
	 * 更新
	 *
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void update(T entity) {
		if (entity instanceof DomainObject) {
			((DomainObject) entity).setUpdateTime(Calendar.getInstance());
		}
		dao.update(entity);
	}

	/**
	 * 删除数据
	 *
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		dao.delete(id);
	}

	public void deleteOne(Long id) {
		delete(id);
	}

	@Autowired
	public void setDao(D dao) {
		this.dao = dao;
	}
}
