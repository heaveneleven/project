package com.nuaa.dao.base.inter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoInter<T,ID extends Serializable> {
	/**
	 * 保存一个实体
	 * @param entity
	 */
	public void save(T entity);	
	/**
	 * 删除一个实体
	 * @param entity
	 */
	public void delete(T entity);
	/**
	 * 根据ID删除实体
	 * @param entityClass
	 * @param id
	 */
	public void delete(Class<T> entityClass,ID id);
	/**
	 * 根据ID集合批量删除实体
	 * @param entity
	 * @param ids
	 */
	public void delete(Class<T> entity,ID[] ids);
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 根据SQL语句更新实体
	 */
	public int update(String sql);
	
	/**
	 * 根据id查询实体
	 * @param entity
	 * @param id
	 * @return
	 */
	public T find(Class<T> entity,ID id);
	/**
	 * 根据SQL语句查询 返回Map集合
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> searchForMap(String sql);
	/**
	 * 根据SQL语句分页查询返回Map集合
	 * @param sql
	 * @param minIndex
	 * @param maxIndex
	 * @return
	 */
	public List<Map<String,Object>> searchForMap(String sql,int minIndex,int maxIndex,String orderColumn);
	/**
	 * 分页查询主要方法
	 * 
	 */
	public List<T> search(Class<T> entityClass, String sql, int firstindex, int maxresult);
	/**
	 * 统计实体数量
	 * @param entity
	 * @return
	 */
	public long count(Class<T> entity);
	/**
	 * 根据sql获取记录总数
	 * @param sql
	 * @return
	 */
	public long count(String sql);
}
