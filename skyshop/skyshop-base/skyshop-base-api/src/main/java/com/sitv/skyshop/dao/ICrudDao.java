package com.sitv.skyshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sitv.skyshop.domain.IBaseType;
import com.sitv.skyshop.dto.SearchParamInfo;

/**
 * @ClassName: ICrudDao
 * @Description: DAO支持类实现
 * @param <T>
 */
public interface ICrudDao<T extends IBaseType> {

	/**
	 * 获取单条数据
	 *
	 * @param id
	 * @return
	 */
	public T get(@Param("id") Long id);

	/**
	 * 获取单条数据
	 *
	 * @param entity
	 * @return
	 */
	public T get(T entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 *
	 * @param entity
	 * @return
	 */
	public List<T> findBy(T entity);

	/**
	 * 搜索
	 *
	 * @param q
	 *            分页、排序、过滤
	 * @return
	 */
	public List<T> search(SearchParamInfo<?> q);

	/**
	 * 查询所有数据列表
	 *
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 插入数据
	 *
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 更新数据
	 *
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 删除数据
	 *
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	public int delete(@Param("id") Long id);

	/**
	 * 删除数据
	 *
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

}