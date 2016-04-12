package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

public interface TopicService extends BaseDao<Topic>{
    /**
     * 根据板块查询主题列表
     * @param forum
     * @return
     */
	List<Topic> findAll(Forum forum);

}
