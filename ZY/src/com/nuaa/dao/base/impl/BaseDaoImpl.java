package com.nuaa.dao.base.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.jdbc.core.JdbcTemplate;

import com.nuaa.dao.base.inter.BaseDaoInter;
public  class BaseDaoImpl<T,ID extends Serializable > implements BaseDaoInter<T,ID>{
	@PersistenceContext
	protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@Resource
	private JdbcTemplate jdbcTemplate;//@Resource注解使得按照名字jdbcTemplate去配置文件applicationContext.xml
									  //中来查找相应的bean
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	/**
	 * 保存一个实体
	 */
	public void save(T entity) {
		
		em.persist(entity);
	}
	@Override
	/**
	 * 删除一个实体
	 */
	public void delete(T entity) {
		em.remove(entity);
		
	}
	@Override
	/**
	 * 根据ID删除一个实体
	 */
	public void delete(Class<T> entityClass, ID id) {
		em.remove(em.getReference(entityClass, id));
	}
	@Override
	/**
	 * 根据数组对象批量删除实体
	 */
	public void delete(Class<T> entity, ID[] ids) {
		for(Object id : ids){
			em.remove(em.getReference(entity, id));
		}
	}
	@Override
	/**
	 * 更新一个实体
	 */
	public void update(T entity) {
		em.merge(entity);
	}
	@Override
	/**
	 * 根据SQL语句更新实体
	 */
	public int update(String sql) {
		Query query = em.createNativeQuery(sql);
		return query.executeUpdate();
	}

	@Override
	/**
	 * 根据ID查找实体
	 */
	public T find(Class<T> entity, ID id) {
		return em.find(entity, id);
	}
	@Override
	/**
	 * 根据SQl语句返回一个List的Map集合（List<Map<String,Object>>）
	 */
	public List<Map<String, Object>> searchForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
	@Override
	public long count(Class<T> entity) {
		return 0;
	}	
	/**
	 * 根据SQL语句分页查询实体
	 */
	@SuppressWarnings("unchecked")
	public List<T> search(Class<T> entityClass, String sql, int firstindex, int maxresult) {
		Query query = em.createNativeQuery(sql, entityClass);
		List<T> result = new ArrayList<T>();
		if (firstindex > -1 && maxresult > -1) {
			query.setFirstResult(firstindex);
			query.setMaxResults(maxresult);
		}
		result = (List<T>) query.getResultList();
		return result;
	}
	/*** MSSQL 数据库******根据SQL语句分页查询返回Map集合 (适用于SQL2005及以后版本)****测试通过
		 */
		 public List<Map<String, Object>> searchForMap(String sql,
		 int firstIndex, int maxResult, String orderColumn) {
		 if (!sql.isEmpty()) {
		 String listsql = "select top " + (maxResult)
		 + " o.* from (select row_number() over(order by "
		 + orderColumn + " ) as rownumber,* from (" + sql
		 + ") as oo) as o where rownumber>" + firstIndex
		 +" order by "+orderColumn;
		 //System.out.println("sql:" + listsql);
		 return this.jdbcTemplate.queryForList(listsql);
		 }
		 return null;
		 }
	/**
	 * 根据SQL语句查询实体
	 */
	public List<T> search(Class<T> entityClass, String sql) {
		return search(entityClass, sql, -1, -1);
	}
	/**
	 * 根据SQL约束统计数量
	 */
	public long count(String sql) {
		//Query query = em.createNativeQuery(sql);
		//return query.getSingleResult() == null ? 0 : (Long.valueOf(query.getSingleResult().toString()));
		return jdbcTemplate.queryForInt("select count(*) from ( "+sql+" ) as x");
	}
}
