package cn.itcast.oa.base;

import java.util.List;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.HqlHelper;

public interface BaseDao<T> {
	/**
	 * 保存
	 * @param entity
	 */
	void save(T entity);
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 修改
	 * @param entity
	 */
	void update(T entity);
	/**
	 * Id查询对象
	 * @param id
	 * @return
	 */
	T getById(Long id);
	/**
	 * ids 获取一组对象	
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);
	
	/**
	 * 获取对象集合
	 * @param ids
	 * @return
	 */
	List<T> findAll();
	/**
	 * 公共的分页方法
	 * @param pageNum
	 * @param hql
	 * @param parameters
	 * @return
	 */
	@Deprecated
	PageBean getPageBean(int pageNum, String hql, Object[] parameters);
	
	/**
	 * 采用hqlhelper工具类分页
	 * @param pageNum
	 * @param hqlHelper
	 * @return
	 */
	PageBean getPageBean(int pageNum, HqlHelper hqlHelper);

}
