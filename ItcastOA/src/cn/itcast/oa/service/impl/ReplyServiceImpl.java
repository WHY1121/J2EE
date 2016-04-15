package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;


@Service
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService{

	

	@Override
	public List<Reply> findByTopic(Topic topic) {
		
		return getSession()//
				.createQuery("FROM Reply r WHERE r.topic=? order by createTime ASC")//
				.setParameter(0, topic)
				.list();
	}

	@Override
	public void save(Reply entity) {
		getSession().save(entity);
		//维护特殊的关联关系
		Topic topic=entity.getTopic();
		Forum forum=topic.getForum();
		forum.setArticleCount(forum.getArticleCount()+1);
		topic.setLastReply(entity);
		topic.setReplyCount(topic.getReplyCount()+1);
		topic.setLastUpdateTime(entity.getCreateTime());
		
		
		
		getSession().update(forum);
		getSession().update(topic);
	}

	@Override
	public PageBean getPageBean(int pageNum, Topic topic) {
		
		
		int pageSize=Configuration.getPageSize();
		List<Reply> recordList=getSession()//
				.createQuery("FROM Reply r WHERE r.topic=? order by createTime ASC")//
				.setParameter(0, topic)//
				.setFirstResult((pageNum-1)*pageSize)//
				.setMaxResults(pageSize)//
				.list();
		
		Long count=(Long) getSession()//
				.createQuery("SELECT COUNT(*) FROM Reply r WHERE r.topic=?")//
				.setParameter(0, topic)//
				.uniqueResult();
		
		
				
		return new PageBean(pageNum, pageSize, recordList, count.intValue());
	}
	

}
