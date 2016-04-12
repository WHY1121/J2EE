package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;

public interface ForumManageService extends BaseDao<Forum>{
    /**
     * 下移
     * @param id
     */
	void moveDown(Long id);
    /**
     * 上移
     * @param id
     */
	void moveUp(Long id);

	
	
}
