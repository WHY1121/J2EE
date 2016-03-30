package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDaoImpl() {
		//通过反射的到T的真实类
		ParameterizedType paType = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class) paType.getActualTypeArguments()[0];
		
		System.out.println("clazz="+clazz.getName());
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

		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> getByIds(Long[] ids) {

		// ���ؿռ���
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				"FROM " + clazz.getName() + " WHERE ids IN(:ids)")
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

}
