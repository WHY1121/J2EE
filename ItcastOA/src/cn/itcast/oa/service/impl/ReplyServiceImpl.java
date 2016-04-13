package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
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

}
