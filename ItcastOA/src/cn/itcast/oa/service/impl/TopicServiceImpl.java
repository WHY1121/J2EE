package cn.itcast.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;

@Service
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService{

	@Override
	public List<Topic> findByForum(Forum forum) {
		
		return getSession()
				.createQuery("FROM Topic t WHERE t.forum=? ORDER BY (CASE WHEN t.type=2 then 2 ELSE 0 END) DESC,t.createTime DESC")
				.setParameter(0, forum)
				.list();
	}

	@Override
	public void save(Topic entity) {
		//默认值，可以不写
//		entity.setType(entity.TYPR_NORMAL);
//		entity.setReplyCount(0);
//		entity.setLastReply(null);
		entity.setLastUpdateTime(new Date());
		getSession().save(entity);
		//维护关联关系的特殊字段
		Forum forum=entity.getForum();
		forum.setArticleCount(forum.getArticleCount()+1);
		forum.setTopicCount(forum.getTopicCount()+1);
		forum.setLastTopic(entity);
		getSession().update(forum);
	}
	
	

	
}
