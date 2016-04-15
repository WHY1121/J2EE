package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.util.HqlHelper;

@SuppressWarnings("unchecked")
//@Transactional注解可以被继承，即对子类也有效
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDaoImpl() {
		//通过反射的到T的真实类
		ParameterizedType paType = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class) paType.getActualTypeArguments()[0];
		
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		Object object = getById(id);
		getSession().delete(object);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);

	}

	@Override
	public T getById(Long id) {
		if(id==null){
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> getByIds(Long[] ids) {

		// ���ؿռ���
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				"FROM " + clazz.getName() + " WHERE id IN(:ids)")
				.setParameterList("ids", ids).list();
	}

	@Override
	public List<T> findAll() {

		return getSession().createQuery("FROM " + clazz.getName()).list();
	}

	/**
	 * ��ȡsession
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public PageBean getPageBean(int pageNum, String hql, Object[] parameters) {
		System.out.println("/////////////////////////===================公共分页");
		int pageSize = Configuration.getPageSize();
		//创建query对象
		Query listQuery = (Query) getSession().createQuery(hql);
		//遍历查询参数
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				listQuery.setParameter(i, parameters[i]);

			}
		}
		//设置分页参数
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		//执行查询
		List recordList=listQuery.list();
		//========================
		//查询记录数量
	
		Query countQuery =getSession().createQuery("SELECT COUNT(*) "+hql.substring(0,hql.indexOf("order")));
		
		if (parameters != null && parameters.length > 0) {
			for (int i = 0; i < parameters.length; i++) {
				countQuery.setParameter(i, parameters[i]);

			}
		}
		Long recordCount=(Long) countQuery.uniqueResult();
		
		return new PageBean(pageNum, pageSize, recordList, recordCount.intValue());
	}

	/**
	 * 分页最终版
	 */
	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		int pageSize = Configuration.getPageSize();
		List<Object> parameters=hqlHelper.getParameters();
		//创建query对象
		Query listQuery = (Query) getSession().createQuery(hqlHelper.getQueryListHql());
		//遍历查询参数
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				listQuery.setParameter(i, parameters.get(i));

			}
		}
		//设置分页参数
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		//执行查询
		List recordList=listQuery.list();
		//========================
		//查询记录数量
	
		Query countQuery =getSession().createQuery(hqlHelper.getQueryCountHql());
		
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		Long recordCount=(Long) countQuery.uniqueResult();
		
		return new PageBean(pageNum, pageSize, recordList, recordCount.intValue());
	}

}
