package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.ForumManage;

public interface ForumManageService extends BaseDao<ForumManage>{
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
