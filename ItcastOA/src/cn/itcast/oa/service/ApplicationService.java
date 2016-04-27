package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Application;

public interface ApplicationService extends BaseDao<Application>{
    /**
     * 提交申请
     * @param application
     */
	void submit(Application application);

}
