package cn.itcast.oa.base;

import java.util.List;

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
	

}
