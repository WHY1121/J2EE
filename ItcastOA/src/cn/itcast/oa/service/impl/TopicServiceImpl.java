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
	public List<Topic> findAll(Forum forum) {
		
		return getSession()
				.createQuery("FROM Topic t WHERE t.forum=? ORDER BY (CASE WHEN t.type=2 then 2 ELSE 0 END) DESC,t.createDate DESC")
				.setParameter(0, forum)
				.list();
	}

	@Override
	public void save(Topic entity) {
		entity.setCreateDate(new Date());
		entity.setType(0);
		entity.setReplyCount(0);
	}
	
	

	
}
