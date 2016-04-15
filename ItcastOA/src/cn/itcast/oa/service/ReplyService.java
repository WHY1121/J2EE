package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface ReplyService extends BaseDao<Reply>{
    /**
     * 根据主题查找回复
     * @param topic
     * @return
     */
	@Deprecated
	List<Reply> findByTopic(Topic topic);
    /**
     * 根据主题分页显示回复
     * @param pageNum
     * @param topic
     * @return
     */
	@Deprecated
	PageBean getPageBean(int pageNum, Topic topic);
	
	

}
