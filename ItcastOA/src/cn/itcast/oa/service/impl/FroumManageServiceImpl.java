package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumManageService;

@Service
public class FroumManageServiceImpl extends BaseDaoImpl<Forum> implements ForumManageService{

	@Override
	public void save(Forum entity) {
		//保存的数据库会自动生成id
		getSession().save(entity);
		//使用id做position的值自动增长
		entity.setPosition(entity.getId().intValue());
		//对象为持久化状态，会自动更新到数据库
		
	}

	@Override
	public List<Forum> findAll() {
		
		 return getSession()//
				.createQuery("FROM Forum f ORDER BY f.position ASC")//
				.list();
	}

	@Override
	public void moveDown(Long id) {
		Forum manage=getById(id);
		Forum other=(Forum) getSession()
				.createQuery("FROM Forum f WHERE f.position>? ORDER BY f.position ASC")
				.setFirstResult(0)
				.setMaxResults(1)
				.setParameter(0, manage.getPosition())
				.uniqueResult();
		
		if(other==null){
			return;
		}
		int temp=manage.getPosition();
		manage.setPosition(other.getPosition());
		other.setPosition(temp);
	}

	@Override
	public void moveUp(Long id) {
		//移动的对象
		Forum manage=getById(id);
	    //从数据库查找移动对象的上一个对象
		Forum other=(Forum) getSession()
				.createQuery("FROM Forum f WHERE f.position<? ORDER BY f.position DESC")
				.setFirstResult(0)
				.setMaxResults(1)
				.setParameter(0, manage.getPosition())
				.uniqueResult();
		
		
		
		//最前端的不能上移
		if(other==null){
			return;
			
		}
		//交换位置
		int temp=manage.getPosition();
		manage.setPosition(other.getPosition());
		other.setPosition(temp);
		
		
		
	}

}
