/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sitv.skyshop.service;

import java.lang.reflect.Method;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sitv.skyshop.dao.ICrudDao;
import com.sitv.skyshop.domain.IBaseType;
import com.sitv.skyshop.dto.Dto;

/**
 * Service基类
 */
@Transactional
public abstract class CrudService<D extends ICrudDao<T>, T extends IBaseType, I extends Dto> implements IBaseService<I> {

	private static final Logger log = LoggerFactory.getLogger(CrudService.class);

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
		return dao.get(id);
	}

	/**
	 * 保存数据
	 *
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void create(T entity) {
		try {
			Method m = entity.getClass().getDeclaredMethod("setCreateTime", java.util.Calendar.class);
			m.invoke(entity, new Date(System.currentTimeMillis()));
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
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
		try {
			Method m = entity.getClass().getDeclaredMethod("setUpdateTime", java.util.Calendar.class);
			m.invoke(entity, new Date(System.currentTimeMillis()));
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
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
