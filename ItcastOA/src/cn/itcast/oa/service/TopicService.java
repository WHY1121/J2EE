package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.HqlHelper;

public interface TopicService extends BaseDao<Topic>{
    /**
     * 根据板块查询主题列表
     * @param forum
     * @return
     */
	@Deprecated
	List<Topic> findByForum(Forum forum);
    /**
     * 根据板块查询主题列表
     * @param pageNum
     * @param forum
     * @return
     */
    @Deprecated
	PageBean getPageBean(int pageNum, Forum forum);
	

}
