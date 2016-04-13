package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface ReplyService extends BaseDao<Reply>{
    /**
     * 根据主题查找回复
     * @param topic
     * @return
     */
	List<Reply> findByTopic(Topic topic);

}
